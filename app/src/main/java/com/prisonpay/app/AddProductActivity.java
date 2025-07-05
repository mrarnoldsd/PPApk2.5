package com.prisonpay.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    private EditText inputName, inputCost, inputPrice, inputStock;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        inputName = findViewById(R.id.input_name);
        inputCost = findViewById(R.id.input_cost);
        inputPrice = findViewById(R.id.input_price);
        inputStock = findViewById(R.id.input_stock);
        Button submitButton = findViewById(R.id.btn_submit_product);

        inventoryManager = new InventoryManager();

        submitButton.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
            String costStr = inputCost.getText().toString().trim();
            String priceStr = inputPrice.getText().toString().trim();
            String stockStr = inputStock.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(costStr) ||
                TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(stockStr)) {
                Toast.makeText(this, "Fill out all fields 🔍", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double cost = Double.parseDouble(costStr);
                double price = Double.parseDouble(priceStr);
                int stock = Integer.parseInt(stockStr);

                // Pass `null` as the ID — InventoryManager will generate it
                Product newPack = new Product(null, name, cost, price, stock);
                inventoryManager.addProduct(newPack);
                Toast.makeText(this, "New Work on Deck 🚚", Toast.LENGTH_SHORT).show();
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bad numbers, fix that 💥", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
