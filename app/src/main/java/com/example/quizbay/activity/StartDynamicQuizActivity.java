package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quizbay.R;
import com.example.quizbay.model.QuestionDynamic;
import com.example.quizbay.model.Questions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

public class StartDynamicQuizActivity extends AppCompatActivity {

    private Button btn_start;
    private Button btn_skip;
    private QuestionDynamic questionDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_dynamic_quiz);

        btn_skip=findViewById(R.id.btn_skip_dynamic_quiz);
        btn_start=findViewById(R.id.btn_start_dynamic_quiz);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dynamicQuestionString = getIntent().getStringExtra("getQuestion");
                ObjectMapper objectMapper = new ObjectMapper();
                QuestionDynamic questions = new QuestionDynamic();
                try {
                    questions = objectMapper.readValue(dynamicQuestionString, QuestionDynamic.class);
                } catch (JsonParseException e) {
                    e.printStackTrace();
                    Log.d("Exceptio1", "onMessageReceived: "+e.getMessage());
                } catch (JsonMappingException e) {
                    Log.d("Exceptio2", "onMessageReceived: "+e.getMessage());

                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("Exception3", "onMessageReceived: "+e.getMessage());
                    e.printStackTrace();
                }
                Log.d("FireBase", "onMessageReceived: "+questions.toString());
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits")
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Dynamic"+questions.getQuizId(),"true");
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String dynamicQuestionString = getIntent().getStringExtra("getQuestion");
                ObjectMapper objectMapper = new ObjectMapper();
                QuestionDynamic questions = new QuestionDynamic();
                try {
                    questions = objectMapper.readValue(dynamicQuestionString, QuestionDynamic.class);
                } catch (JsonParseException e) {
                    e.printStackTrace();
                    Log.d("Exceptio1", "onMessageReceived: "+e.getMessage());
                } catch (JsonMappingException e) {
                    Log.d("Exceptio2", "onMessageReceived: "+e.getMessage());

                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("Exception3", "onMessageReceived: "+e.getMessage());
                    e.printStackTrace();
                }
                Log.d("FireBase", "onMessageReceived: "+questions.toString());
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits")
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Dynamic"+questions.getQuizId(),"false");
                editor.commit();
            }
        });


    }
}