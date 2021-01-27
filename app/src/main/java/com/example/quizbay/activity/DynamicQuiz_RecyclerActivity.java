package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.adapter.DynamicQuizListAdapter;
import com.example.quizbay.adapter.LeaderboardByQuizAdapter;
import com.example.quizbay.adapter.OverallLeaderboardAdapter;
import com.example.quizbay.builder.Quizbay_IAPi_GetActiveDynamicQuiz;
import com.example.quizbay.builder.Quizbay_IAPi_Leaderboard;
import com.example.quizbay.builder.RetrofitBuilder;
import com.example.quizbay.builder.RetrofitBuilder2;
import com.example.quizbay.model.DynamicQuizNamesListModel;
import com.example.quizbay.model.LeaderboardByQuizModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DynamicQuiz_RecyclerActivity extends AppCompatActivity {

    ArrayList<DynamicQuizNamesListModel> mList = new ArrayList<>();

    RecyclerView recyclerView;
    DynamicQuizListAdapter recyclerViewAdapter;
    //Toast.makeText(DynamicQuiz_RecyclerActivity.this, "hello", Toast.LENGTH_SHORT).show();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_quiz__recycler);
        recyclerView = findViewById(R.id.rv_dynamic_quiz_recycler);

        Toast.makeText(DynamicQuiz_RecyclerActivity.this,"hello",Toast.LENGTH_SHORT).show();
       /*
        DynamicQuizNamesListModel dynamicQuizNamesListModel = new DynamicQuizNamesListModel("Dynamic Quiz 1");
        DynamicQuizNamesListModel dynamicQuizNamesListModel2 = new DynamicQuizNamesListModel("Dynamic Quiz 2");
        DynamicQuizNamesListModel dynamicQuizNamesListModel3 = new DynamicQuizNamesListModel("Dynamic Quiz 3");
        mList.add(dynamicQuizNamesListModel);
        mList.add(dynamicQuizNamesListModel2);
        mList.add(dynamicQuizNamesListModel3);

        recyclerViewAdapter = new DynamicQuizListAdapter(mList, DynamicQuiz_RecyclerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(DynamicQuiz_RecyclerActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
        */

        Retrofit retrofit = RetrofitBuilder.getInstance();
        Quizbay_IAPi_GetActiveDynamicQuiz iMyAPIInterface = retrofit.create(Quizbay_IAPi_GetActiveDynamicQuiz.class);

        Call<List<DynamicQuizNamesListModel>> APICall =  iMyAPIInterface.getactivedynamicquizzes();
        // Toast.makeText(DynamicQuiz_RecyclerActivity.this, String.valueOf(mList.size()), Toast.LENGTH_SHORT).show();
        APICall.enqueue(new Callback<List<DynamicQuizNamesListModel>>() {
            //Toast.makeText(DynamicQuiz_RecyclerActivity.this, String.valueOf(mList.size()), Toast.LENGTH_SHORT).show();
            @Override
            public void onResponse(Call<List<DynamicQuizNamesListModel>> call, Response<List<DynamicQuizNamesListModel>> response) {
                mList.addAll(response.body());

                Toast.makeText(DynamicQuiz_RecyclerActivity.this, String.valueOf(response.body().size()), Toast.LENGTH_SHORT).show();
                recyclerViewAdapter = new DynamicQuizListAdapter(mList, DynamicQuiz_RecyclerActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(DynamicQuiz_RecyclerActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<DynamicQuizNamesListModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(DynamicQuiz_RecyclerActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(DynamicQuiz_RecyclerActivity.this, String.valueOf(mList.size()), Toast.LENGTH_SHORT).show();
    }
}