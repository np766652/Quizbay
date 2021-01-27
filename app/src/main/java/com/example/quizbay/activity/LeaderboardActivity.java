package com.example.quizbay.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbay.R;
import com.example.quizbay.adapter.LeaderboardByQuizAdapter;
import com.example.quizbay.builder.Quizbay_IAPi_Leaderboard;
import com.example.quizbay.builder.RetrofitBuilder2;
import com.example.quizbay.model.LeaderboardByQuizModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LeaderboardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LeaderboardByQuizAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_leaderboard);

        recyclerView = findViewById(R.id.rv_lb);


        ArrayList<LeaderboardByQuizModel> mList = new ArrayList<>();

        findViewById(R.id.iv_trophy).setOnClickListener(View ->
                Toast.makeText(LeaderboardActivity.this,"List is available",Toast.LENGTH_LONG).show());

        int quiz_id =getIntent().getIntExtra("quizId",0);


        Retrofit retrofit = RetrofitBuilder2.getInstance();
        Quizbay_IAPi_Leaderboard iMyAPIInterface = retrofit.create(Quizbay_IAPi_Leaderboard.class);

        Call<List<LeaderboardByQuizModel>> APICall =  iMyAPIInterface.getLeaderBoardByQuizId(quiz_id);

        APICall.enqueue(new Callback<List<LeaderboardByQuizModel>>() {

            @Override
            public void onResponse(Call<List<LeaderboardByQuizModel>> call, Response<List<LeaderboardByQuizModel>> response) {
                mList.addAll(response.body());
                TextView textView = findViewById(R.id.tv_leaderboard_winner_name);
                String response1 = mList.get(0).getUserName();
                textView.setText(response1);
                Toast.makeText(LeaderboardActivity.this, String.valueOf(response.body().size()), Toast.LENGTH_SHORT).show();
                recyclerViewAdapter = new LeaderboardByQuizAdapter(mList, LeaderboardActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(LeaderboardActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<LeaderboardByQuizModel>> call, Throwable t) {
                Toast.makeText(LeaderboardActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
