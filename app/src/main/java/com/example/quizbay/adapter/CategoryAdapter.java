package com.example.quizbay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;
import com.example.quizbay.activity.QuizListActivity;
import com.example.quizbay.model.CategoryModel;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private final Context context;
    private final List<CategoryModel> categoryModelList;


    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        this.context = context;

    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel=categoryModelList.get(position);
        holder.setCategoryName(categoryModel.getCategoryName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuizListActivity.class);
            intent.putExtra("name", categoryModel.getCategoryName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }


     static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView categoryName;
        private final View categoryView;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             categoryView=itemView;
             categoryName=itemView.findViewById(R.id.tv_quizbay_category_type);
         }

         private void setCategoryName(String categoryName){
             this.categoryName.setText(categoryName);
         }
     }
}
