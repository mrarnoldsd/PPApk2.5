package com.prisonpay.app;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class StashHouseActivity extends AppCompatActivity {

    private ListView stashListView;
    private InventoryManager inventoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stash_house);

        stashListView = findViewById(R.id.stash_list_view);
        inventoryManager = new InventoryManager();

        // Fetch inventory from Firebase
        inventoryManager.getInventory(products -> {
            if (products != null && !products.isEmpty()) {
                StashAdapter adapter = new StashAdapter(this, products);
                stashListView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Ain't no packs in the stash yet 📭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
