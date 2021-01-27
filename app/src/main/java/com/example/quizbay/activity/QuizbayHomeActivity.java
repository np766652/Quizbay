package com.example.quizbay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizbay.DynamicListActivity;
import com.example.quizbay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class QuizbayHomeActivity extends AppCompatActivity {

    private String userId;
    private String userName;
    private TextView tv_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizbay_home);
        tv_userName = findViewById(R.id.tv_quizbay_home_username);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("qb_userId","1");
        userName = sharedPreferences.getString("qb_userName","Nikunj");
        Log.d("userId", "onCreateHome: "+userName);
        tv_userName.setText(userName);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("TAG", msg);
                        Toast.makeText(QuizbayHomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("TAG", msg);
                        Toast.makeText(QuizbayHomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("TAG", msg);
                        Toast.makeText(QuizbayHomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });




        findViewById(R.id.tv_quizbay_play_button).setOnClickListener(v -> {
            Intent intentPlay = new Intent(this,CategoryActivity.class);
            startActivity(intentPlay);
        });

        findViewById(R.id.ib_quizbay_leaderboard).setOnClickListener(v -> {
            Intent intentLeaderboard = new Intent(this,OverallLeaderboardActivity.class);
            startActivity(intentLeaderboard);
        });

        findViewById(R.id.ib_quizbay_profile).setOnClickListener(v -> {
            Intent intentProfile = new Intent(this,QuizbayProfileActivity.class);
            startActivity(intentProfile);
        });

        findViewById(R.id.btn_dynamiclist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizbayHomeActivity.this,DynamicQuiz_RecyclerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.ib_quizbay_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizbayHomeActivity.this, DynamicListActivity.class);
                startActivity(intent);
            }
        });



    }
}