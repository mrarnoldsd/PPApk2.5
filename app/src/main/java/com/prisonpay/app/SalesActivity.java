package com.prisonpay.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SalesActivity extends AppCompatActivity {

    private RecyclerView salesRecyclerView;
    private StashAdapter stashAdapter;
    private InventoryManager inventoryManager;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        salesRecyclerView = findViewById(R.id.recycler_sales);
        salesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stashAdapter = new StashAdapter();
        salesRecyclerView.setAdapter(stashAdapter);

        inventoryManager = new InventoryManager();

        loadStashHouse();

        Button sellButton = findViewById(R.id.btn_sell);
        sellButton.setOnClickListener(v -> {
            if (selectedProduct != null && selectedProduct.getStock() > 0) {
                int updatedStock = selectedProduct.getStock() - 1;
                inventoryManager.updateProductStock(selectedProduct.getId(), updatedStock);
                Toast.makeText(this, "📦 Moved a pack!", Toast.LENGTH_SHORT).show();
                loadStashHouse();
            } else {
                Toast.makeText(this, "⛔ Outta stock!", Toast.LENGTH_SHORT).show();
            }
        });

        stashAdapter.setOnItemClickListener(product -> {
            selectedProduct = product;
            Toast.makeText(this, "Selected: " + product.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void loadStashHouse() {
        inventoryManager.getInventory(products -> {
            if (products != null && !products.isEmpty()) {
                stashAdapter.setProductList(products);
            } else {
                Toast.makeText(this, "Ain’t no Work in the Stash House 🧱", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
