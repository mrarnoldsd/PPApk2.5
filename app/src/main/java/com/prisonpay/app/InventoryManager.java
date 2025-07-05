package com.prisonpay.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static final String TAG = "InventoryManager";
    private final DatabaseReference database;

    public interface InventoryCallback {
        void onInventoryLoaded(List<Product> products);
    }

    public InventoryManager() {
        database = FirebaseDatabase.getInstance().getReference("stash_house");
    }

    public void addProduct(Product product) {
        String key = database.push().getKey();
        if (key != null) {
            product.setId(key);
            database.child(key).setValue(product)
                .addOnSuccessListener(unused -> Log.d(TAG, "New Work on Deck 🔥"))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to move product: ", e));
        }
    }

    public void getInventory(final InventoryCallback callback) {
        database.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Product> productList = new ArrayList<>();
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product);
                    }
                }
                callback.onInventoryLoaded(productList);
            } else {
                Log.e(TAG, "Failed to fetch stash house 📦", task.getException());
            }
        });
    }

    public void updateProductStock(String productId, int newStock) {
        database.child(productId).child("stock").setValue(newStock)
            .addOnSuccessListener(unused -> Log.d(TAG, "Left in the Safe updated 🔐"))
            .addOnFailureListener(e -> Log.e(TAG, "Failed to update stock: ", e));
    }
}
