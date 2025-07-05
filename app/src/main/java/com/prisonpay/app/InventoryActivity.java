package com.prisonpay.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private ListView productListView;
    private ArrayAdapter<String> adapter;
    private List<String> productNames = new ArrayList<>();
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        productListView = findViewById(R.id.product_list);
        Button addProductBtn = findViewById(R.id.btn_add_product);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productNames);
        productListView.setAdapter(adapter);

        inventoryManager = new InventoryManager();

        loadInventory();

        addProductBtn.setOnClickListener(v -> {
            // For now, let's just simulate adding a sample product
            Product sample = new Product("Sample Pack", 10.0, 25.0, 50);
            inventoryManager.addProduct(sample);
            Toast.makeText(this, "New pack moved to the stash 🔥", Toast.LENGTH_SHORT).show();
            loadInventory();
        });
    }

    private void loadInventory() {
        inventoryManager.getInventory(products -> {
            productNames.clear();
            for (Product p : products) {
                productNames.add(p.getName() + " - Left in Safe: " + p.getStock());
            }
            adapter.notifyDataSetChanged();
        });
    }
}
