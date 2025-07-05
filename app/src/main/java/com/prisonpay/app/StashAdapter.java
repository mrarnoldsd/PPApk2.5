package com.prisonpay.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StashAdapter extends RecyclerView.Adapter<StashAdapter.StashViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setProductList(List<Product> list) {
        this.productList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pack, parent, false);
        return new StashViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StashViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText("💼 " + product.getName());
        holder.price.setText("💸 $" + product.getPrice());
        holder.stock.setText("🔐 Left in the Safe: " + product.getStock());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class StashViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, stock;

        public StashViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pack_name);
            price = itemView.findViewById(R.id.pack_price);
            stock = itemView.findViewById(R.id.pack_stock);
        }
    }
}
