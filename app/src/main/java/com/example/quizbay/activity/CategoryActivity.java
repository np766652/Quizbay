package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizbay.R;
import com.example.quizbay.adapter.CategoryAdapter;
import com.example.quizbay.builder.IPostApi;
import com.example.quizbay.builder.RetrofitBuilder;
import com.example.quizbay.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);


        List<CategoryModel> categoryModelsList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_quizbay_category_list);

        generateCategoryModel(categoryModelsList);

                CategoryAdapter recyclerViewAdapter = new CategoryAdapter(this, categoryModelsList);
                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
    }


    public void generateCategoryModel(List<CategoryModel> categoryModelList){
        categoryModelList.add(new CategoryModel("sports"));
        categoryModelList.add(new CategoryModel("phani"));
        categoryModelList.add(new CategoryModel("currentaffairs"));
        categoryModelList.add(new CategoryModel("aptitude"));
        categoryModelList.add(new CategoryModel("movies"));
        categoryModelList.add(new CategoryModel("science"));
        categoryModelList.add(new CategoryModel("history"));
    }

}