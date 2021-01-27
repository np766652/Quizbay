package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.adapter.PastQuizesAdapter;
import com.example.quizbay.adapter.PastQuizesQuotesAdapter;
import com.example.quizbay.builder.Quizbay_IApi_ProfileActivity2;
import com.example.quizbay.builder.RetrofitBuilder2;
import com.example.quizbay.model.QuizbayProfileModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PastQuizesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    PastQuizesAdapter recyclerViewAdapter;
    PastQuizesQuotesAdapter recyclerViewAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quizes);

        findViewById(R.id.iv_pastquizes_home).setOnClickListener(View -> {
            Intent intent = new Intent(this,QuizbayHomeActivity.class);
            startActivity(intent);
        });


        String userid = getIntent().getStringExtra("userId");
        recyclerView = findViewById(R.id.rv_quizbay_pastquizes);
        recyclerView1 = findViewById(R.id.rv_pastquizes_quotes);
        ArrayList<QuizbayProfileModel> mList = new ArrayList<>();

        Retrofit retrofit = RetrofitBuilder2.getInstance();
        Quizbay_IApi_ProfileActivity2 iMyAPIInterface = retrofit.create(Quizbay_IApi_ProfileActivity2.class);

        Call<List<QuizbayProfileModel>> APICall =  iMyAPIInterface.getLeaderBoardByUserId(userid);
        APICall.enqueue(new Callback<List<QuizbayProfileModel>>() {
            @Override
            public void onResponse(Call<List<QuizbayProfileModel>> call, retrofit2.Response<List<QuizbayProfileModel>> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(PastQuizesActivity.this, "server returned data", Toast.LENGTH_SHORT).show();
                    mList.addAll(response.body());
                }

                else {
                    Toast.makeText(PastQuizesActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                }


                Log.d("r", "onresponse");
                Toast.makeText(PastQuizesActivity.this, String.valueOf(response.body().size()), Toast.LENGTH_SHORT).show();
                recyclerViewAdapter = new PastQuizesAdapter(mList, PastQuizesActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(PastQuizesActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<QuizbayProfileModel>> call, Throwable t) {
                Toast.makeText(PastQuizesActivity.this, "failed", Toast.LENGTH_SHORT).show();

                Log.d("result", t.getMessage());
            }

        });




        ArrayList<String> sList = new ArrayList<>();
        String s1 = "The past can hurt. But the way I see it, you can either run from it, or learn from it.";
        String s2 = "The past is behind, learn from it.The future is ahead, prepare for it.The present is here, live it.";
        String s3 = "Those who cannot remember the past are condemned to repeat it.";
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);

        recyclerViewAdapter1 = new PastQuizesQuotesAdapter(sList, PastQuizesActivity.this);
        recyclerView1.setLayoutManager(new LinearLayoutManager(PastQuizesActivity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setAdapter(recyclerViewAdapter1);





    }
}