package com.example.winrah.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winrah.databinding.RowProductBinding;
import com.example.winrah.filters.ProductFilter;
import com.example.winrah.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> implements Filterable {

    private Context context;

    private ProductFilter filter;
    public ArrayList<Product> list, filtered;

    private RowProductBinding binding;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.list = products;
        this.filtered = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowProductBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = list.get(position);

        holder.referenceTV.setText(product.getReference());
        holder.sectionTV.setText(product.getSection().getName());
        holder.priceTV.setText(product.getPrice());
        holder.boxColorTV.setText(product.getBoxColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new ProductFilter(filtered, this);
        }
        return filter;
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView referenceTV, sectionTV, priceTV, boxColorTV;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            referenceTV = binding.referenceTV;
            sectionTV = binding.sectionTV;
            priceTV = binding.priceTV;
            boxColorTV = binding.boxColorTV;
        }
    }
}
