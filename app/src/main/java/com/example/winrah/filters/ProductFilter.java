package com.example.winrah.filters;

import android.widget.Filter;

import com.example.winrah.adapters.ProductAdapter;
import com.example.winrah.models.Product;

import java.util.ArrayList;

public class ProductFilter  extends Filter {

    private ArrayList<Product> filteredList;
    private ProductAdapter adapter;

    public ProductFilter(ArrayList<Product> filteredProducts, ProductAdapter adapter) {
        this.filteredList = filteredProducts;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();

        if (charSequence == null || charSequence.length() == 0) {
            results.count = filteredList.size();
            results.values = filteredList;

            return  results;
        }

        String entry = charSequence.toString().toLowerCase();
        ArrayList<Product> products = (ArrayList<Product>) Product.find(
                Product.class, "reference LIKE ?", "%" + entry + "%"
        );

        results.count = products.size();
        results.values = products;

        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.list = (ArrayList<Product>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
