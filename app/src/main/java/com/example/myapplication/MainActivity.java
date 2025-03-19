package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText editName, editPrice, editDescription;
    Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString();
            double price = Double.parseDouble(editPrice.getText().toString());
            String description = editDescription.getText().toString();

            if (db.addProduct(name, price, description)) {
                Toast.makeText(MainActivity.this, "Товар добавлен", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editPrice.setText("");
                editDescription.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Ошибка добавления", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProductListActivity.class)));
    }
}