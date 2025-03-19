package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class EditProductActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText editName, editPrice, editDescription;
    Button btnUpdate;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        db = new DatabaseHelper(this);
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);
        btnUpdate = findViewById(R.id.btnUpdate);

        productId = getIntent().getIntExtra("productId", -1); // Get product ID from the intent

        // Load product details
        loadProductDetails();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                double price = Double.parseDouble(editPrice.getText().toString());
                String description = editDescription.getText().toString();

                if (db.updateProduct(productId, name, price, description)) {
                    Toast.makeText(EditProductActivity.this, "Товар обновлен", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditProductActivity.this, "Ошибка обновления", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadProductDetails() {
        // Query the product by id
        Cursor cursor = db.getProductById(productId);
        if (cursor.moveToFirst()) {
            editName.setText(cursor.getString(1)); // Name
            editPrice.setText(String.valueOf(cursor.getDouble(2))); // Price
            editDescription.setText(cursor.getString(3)); // Description
        }
    }
}
