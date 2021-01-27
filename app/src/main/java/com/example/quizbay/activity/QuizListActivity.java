package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.adapter.QuizListAdapter;
import com.example.quizbay.builder.IPostApi;
import com.example.quizbay.builder.RetrofitBuilder;
import com.example.quizbay.model.QuizListNewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuizListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);


        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);

        List<QuizListNewModel> mquizList= new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_quizbay_active_quize_list);

        String data = getIntent().getStringExtra("name");
        Log.d("TAG", "onCreate: " + data);
        Call<List<QuizListNewModel>> apiCall = iPostApi.getQuizList(data);
        apiCall.enqueue(new Callback<List<QuizListNewModel>>() {
            @Override
            public void onResponse(Call<List<QuizListNewModel>> call, Response<List<QuizListNewModel>> response) {
                Log.d("TAG", "onCreate: " + response.body().size());
                mquizList.addAll(response.body());

                QuizListAdapter recyclerViewAdapter = new QuizListAdapter(mquizList);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuizListActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<List<QuizListNewModel>> call, Throwable t) {
                Toast.makeText(QuizListActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onQuizClick(QuizListNewModel quizList) {

        Intent intent=new Intent(this, QuizbayHomeActivity.class);
        startActivity(intent);

    }
}