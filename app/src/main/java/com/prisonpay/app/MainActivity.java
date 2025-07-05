package com.prisonpay.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView stashRecyclerView;
    private StashAdapter stashAdapter;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stashRecyclerView = findViewById(R.id.recycler_stash);
        stashRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stashAdapter = new StashAdapter();
        stashRecyclerView.setAdapter(stashAdapter);

        inventoryManager = new InventoryManager();

        fetchStashHouse();
    }

    private void fetchStashHouse() {
        inventoryManager.getInventory(products -> {
            if (products != null && !products.isEmpty()) {
                stashAdapter.setProductList(products);
            } else {
                Toast.makeText(this, "Ain’t no Work in the Stash House 🧱", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
