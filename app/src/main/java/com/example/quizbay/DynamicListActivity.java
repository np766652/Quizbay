package com.example.quizbay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.quizbay.activity.DynamicQuizActivity1;
import com.example.quizbay.activity.DynamicQuizActivity2;
import com.example.quizbay.activity.DynamicQuizActivity3;
import com.example.quizbay.activity.DynamicQuizActivity4;
import com.example.quizbay.activity.DynamicQuizActivity5;

public class DynamicListActivity extends AppCompatActivity {


    private CardView cv_dynamic1;
    private CardView cv_dynamic2;
    private CardView cv_dynamic3;
    private CardView cv_dynamic4;
    private CardView cv_dynamic5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list);

        cv_dynamic1 =findViewById(R.id.cv_quizbay_dynamic1);
        cv_dynamic2 =findViewById(R.id.cv_quizbay_dynamic2);
        cv_dynamic3 =findViewById(R.id.cv_quizbay_dynamic3);
        cv_dynamic4 =findViewById(R.id.cv_quizbay_dynamic4);
        cv_dynamic5 =findViewById(R.id.cv_quizbay_dynamic5);

        cv_dynamic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicListActivity.this, DynamicQuizActivity1.class);
                startActivity(intent);
            }
        });

        cv_dynamic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicListActivity.this, DynamicQuizActivity2.class);
                startActivity(intent);
            }
        });

        cv_dynamic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicListActivity.this, DynamicQuizActivity3.class);
                startActivity(intent);
            }
        });

        cv_dynamic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicListActivity.this, DynamicQuizActivity4.class);
                startActivity(intent);
            }
        });

        cv_dynamic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicListActivity.this, DynamicQuizActivity5.class);
                startActivity(intent);
            }
        });


    }
}