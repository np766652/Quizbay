package com.example.quizbay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.builder.IQuizBayStatic;
import com.example.quizbay.builder.RetrofitBuilderQuestion;
import com.example.quizbay.model.UserScore;
import com.google.gson.Gson;
import com.example.quizbay.activity.QuizBay_Question_Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuizStartActivity extends AppCompatActivity {

    private String quizId;
    private String userId;
    private Button btn_start_quiz;
    private UserScore userScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);
        btn_start_quiz = findViewById(R.id.btn_quizbay_static_quiz_start);
        quizId = getIntent().getStringExtra("QuizId");
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("qb_UserId","");
        btn_start_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

//        findViewById(R.id.ic_quizbay_header_home).setOnClickListener(v -> {
//            Intent intentHome= new Intent(this,QuizbayHomeActivity.class);
//            startActivity(intentHome);
//        });
   }

    public void startQuiz()
    {
        Retrofit retrofit = RetrofitBuilderQuestion.getInstance();
        IQuizBayStatic iQuizBayStatic = retrofit.create(IQuizBayStatic.class);
        Call<UserScore> userScoreCall = iQuizBayStatic.getState(Integer.parseInt(quizId),"11");
        Callback<UserScore> callback = new Callback<UserScore>() {
            @Override
            public void onResponse(Call<UserScore> call, Response<UserScore> response) {

                Log.d("InResponse", "onResponse: "+response.message());
                userScore = response.body();

                Log.d("UserScore", "onResponse: "+userScore.toString());
                if (userScore.getQuizStatus())
                {
                    Toast.makeText(QuizStartActivity.this, "You have already attempted this Quiz", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizStartActivity.this,CategoryActivity.class);
                    intent.putExtra("QuizId",Integer.parseInt(quizId));
                    startActivity(intent);
                }
                else
                {
                    SharedPreferences  sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String scoreJson = gson.toJson(userScore);
                    editor.putString(String.valueOf(userScore.getQuizId()), scoreJson);
                    editor.commit();
                    Intent intent = new Intent(QuizStartActivity.this,QuizBay_Question_Activity.class);
                    intent.putExtra("QuizId",String.valueOf(userScore.getQuizId()));
                    finish();
                    startActivity(intent);
                }

            }
            @Override
            public void onFailure(Call<UserScore> call, Throwable t) {
                Log.d("OnQuizStart", "onFailure: "+t.getMessage());
            }
        };
        userScoreCall.enqueue(callback);

    }
}