package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.adapter.OverallLeaderboardAdapter;
import com.example.quizbay.builder.Quizbay_IApi_Home_Leaderboard;
import com.example.quizbay.builder.RetrofitBuilder2;
import com.example.quizbay.model.LeaderboardModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OverallLeaderboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OverallLeaderboardAdapter recyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_leaderboard);

        recyclerView = findViewById(R.id.rv_lb);



        ArrayList<LeaderboardModel> mList = new ArrayList<>();

        Retrofit retrofit = RetrofitBuilder2.getInstance();
        Quizbay_IApi_Home_Leaderboard iMyAPIInterface = retrofit.create(Quizbay_IApi_Home_Leaderboard.class);

        Call<List<LeaderboardModel>> APICall =  iMyAPIInterface.getOverallLeaderBoard();
        APICall.enqueue(new Callback<List<LeaderboardModel>>() {
            @Override
            public void onResponse(Call<List<LeaderboardModel>> call, Response<List<LeaderboardModel>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OverallLeaderboardActivity.this, "server returned data", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(OverallLeaderboardActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                }
                mList.addAll(response.body());
                TextView textView = findViewById(R.id.tv_leaderboard_winner_name);
                String response1 = mList.get(0).getUserName();
                textView.setText(response1);


                Log.d("r", "onresponse");
                Toast.makeText(OverallLeaderboardActivity.this, String.valueOf(response.body().size()), Toast.LENGTH_SHORT).show();
                recyclerViewAdapter = new OverallLeaderboardAdapter(mList, OverallLeaderboardActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(OverallLeaderboardActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<LeaderboardModel>> call, Throwable t) {
                Toast.makeText(OverallLeaderboardActivity.this, "failed", Toast.LENGTH_SHORT).show();

                Log.d("result", t.getMessage());
            }

        });





    }
}