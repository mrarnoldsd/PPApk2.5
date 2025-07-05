package com.prisonpay.app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SalesActivity extends AppCompatActivity {

    private RecyclerView salesRecyclerView;
    private StashAdapter stashAdapter;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        salesRecyclerView = findViewById(R.id.recycler_sales);
        salesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        stashAdapter = new StashAdapter();
        inventoryManager = new InventoryManager();

        salesRecyclerView.setAdapter(stashAdapter);

        inventoryManager.getInventory(products -> {
            if (products != null && !products.isEmpty()) {
                stashAdapter.setProductList(products);
            } else {
                Toast.makeText(this, "No Work to Move! 📦", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle selling when clicking a product
        stashAdapter.setOnItemClickListener(product -> {
            if (product.getStock() > 0) {
                int newStock = product.getStock() - 1;
                inventoryManager.updateProductStock(product.getId(), newStock);
                Toast.makeText(this, "You Moved that Pack 💼💸", Toast.LENGTH_SHORT).show();
                product.setStock(newStock);
                stashAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Ain’t Nothing Left in the Safe 🔒", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
