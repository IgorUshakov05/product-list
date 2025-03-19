package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView listView;
    ArrayList<String> productList;
    ArrayAdapter<String> adapter;
    int selectedProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        productList = new ArrayList<>();

        loadProducts();

        // Register for context menu
        registerForContextMenu(listView);
    }

    private void loadProducts() {
        Cursor cursor = db.getAllProducts();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Нет товаров", Toast.LENGTH_SHORT).show();
            return;
        }

        productList.clear(); // Clear the list before adding new products

        while (cursor.moveToNext()) {
            String product = cursor.getString(1) + " - " + cursor.getDouble(2) + " руб.\n" + cursor.getString(3);
            productList.add(product);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listView.setAdapter(adapter);
    }

    // Handle long click to show the context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, android.view.View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Set context menu options
        menu.setHeaderTitle("Выберите действие");
        menu.add(0, 1, 0, "Редактировать");
        menu.add(0, 2, 0, "Удалить");

        // Get the selected product id
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedProductId = info.position;
    }

    // Handle context menu item clicks
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (selectedProductId >= 0) {
            Cursor cursor = db.getAllProducts();
            cursor.moveToPosition(selectedProductId);
            final int productId = cursor.getInt(0); // Get the product's ID

            switch (item.getItemId()) {
                case 1: // Edit product
                    Intent intent = new Intent(ProductListActivity.this, EditProductActivity.class);
                    intent.putExtra("productId", productId);
                    startActivity(intent);
                    return true;
                case 2: // Delete product
                    new AlertDialog.Builder(this)
                            .setMessage("Вы уверены, что хотите удалить этот товар?")
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteProduct(productId); // Delete product from the database
                                    loadProducts(); // Reload the product list
                                    Toast.makeText(ProductListActivity.this, "Товар удален", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Нет", null)
                            .show();
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }
        return super.onContextItemSelected(item);
    }
}
