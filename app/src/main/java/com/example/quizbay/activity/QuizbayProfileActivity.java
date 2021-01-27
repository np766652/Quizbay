package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.builder.Quizbay_IAPi_Leaderboard;
import com.example.quizbay.builder.Quizbay_IApi_ProfileActivity2;

import com.example.quizbay.builder.RetrofitBuilder2;
import com.example.quizbay.model.ProfileModel;
import com.example.quizbay.model.QuizbayProfileModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class QuizbayProfileActivity extends AppCompatActivity {

    TextView textView;
    int total_score=0;
   String userId;
    ArrayList<QuizbayProfileModel> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizbay_profile);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("qb_userId","1");
        textView= findViewById(R.id.tv_score);

        findViewById(R.id.btn_profile_overall_leaderboard).setOnClickListener(View -> {
            Intent intent = new Intent(this,OverallLeaderboardActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.ib_profile_home).setOnClickListener(v -> {
            Intent intentHome = new Intent(this,QuizbayHomeActivity.class);
            startActivity(intentHome);
        });

        findViewById(R.id.bt_profile_pastquizes).setOnClickListener(View -> {
            Intent intent =new Intent(this,PastQuizesActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
        });

        Retrofit retrofit = RetrofitBuilder2.getInstance();
        Quizbay_IAPi_Leaderboard iMyAPIInterface = retrofit.create(Quizbay_IAPi_Leaderboard.class);

        Call<ProfileModel> APICall =  iMyAPIInterface.getProfileModel(userId);
        APICall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, retrofit2.Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(QuizbayProfileActivity.this, "server returned data", Toast.LENGTH_SHORT).show();

                   ProfileModel profileModel = response.body();
                   total_score=profileModel.getScore();
                    textView.setText(String.valueOf(total_score));

                    TextView textView2 = findViewById(R.id.tv_quizbay_profile_username);
                    Log.d("UserName", "onResponse: "+response.body());
                    textView2.setText(profileModel.getUserName());
                    if(total_score>=1000) {
                        ImageView imageView = findViewById(R.id.iv_profile_user_level);
                        imageView.setBackgroundResource(R.drawable.gold_level);
                    }

                    else if(total_score>=500) {
                        ImageView imageView = findViewById(R.id.iv_profile_user_level);
                        imageView.setBackgroundResource(R.drawable.silver_level);
                    }

                    else  {
                        ImageView imageView = findViewById(R.id.iv_profile_user_level);
                        imageView.setBackgroundResource(R.drawable.bronze_level);
                    }
                }

                else {
                    Toast.makeText(QuizbayProfileActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                }

                Log.d("r", "onresponse");
                Toast.makeText(QuizbayProfileActivity.this, String.valueOf(response.message()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(QuizbayProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();

                Log.d("result", t.getMessage());
            }

        });

        Toast.makeText(QuizbayProfileActivity.this, String.valueOf(total_score), Toast.LENGTH_SHORT).show();


    }
}