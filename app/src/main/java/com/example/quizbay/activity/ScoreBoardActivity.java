package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizbay.R;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        findViewById(R.id.ib_score_home_page).setOnClickListener(v -> {
            Intent intent=new Intent(this,QuizbayHomeActivity.class);
            startActivity(intent);
        });
    }
}