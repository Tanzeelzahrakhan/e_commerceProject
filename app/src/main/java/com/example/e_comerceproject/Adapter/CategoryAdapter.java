package com.example.e_comerceproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_comerceproject.Model.Category;
import com.example.e_comerceproject.R;
import com.example.e_comerceproject.databinding.ItemCategariesBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    @NonNull

    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(@NonNull Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categaries,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category=categories.get(position);
        holder.binding.label.setText(Html.fromHtml(category.getName()));
        Glide.with(context)
                .load(category.getIcon())
                .into((ImageView) holder.binding.image);
        holder.binding.image.setBackgroundColor(Color.parseColor(category.getColor()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public  class CategoryViewHolder extends RecyclerView.ViewHolder {

        ItemCategariesBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCategariesBinding.bind(itemView);
        }
    }}