package com.prisonpay.app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SalesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StashAdapter stashAdapter;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        recyclerView = findViewById(R.id.recycler_sales);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stashAdapter = new StashAdapter();
        recyclerView.setAdapter(stashAdapter);

        inventoryManager = new InventoryManager();
        inventoryManager.getInventory(products -> {
            if (products != null && !products.isEmpty()) {
                stashAdapter.setProductList(products);
            } else {
                Toast.makeText(this, "No Work to Move 💨", Toast.LENGTH_SHORT).show();
            }
        });

        stashAdapter.setOnItemClickListener(product -> {
            int currentStock = product.getStock();
            if (currentStock > 0) {
                inventoryManager.updateProductStock(product.getId(), currentStock - 1);
                Toast.makeText(this, "Moved a Pack 💼", Toast.LENGTH_SHORT).show();
                product.setStock(currentStock - 1);
                stashAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Outta Stock 😤", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
