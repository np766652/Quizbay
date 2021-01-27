package com.example.quizbay.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quizbay.R;
import com.example.quizbay.model.LeaderBoard;
import com.example.quizbay.model.QuestionDynamic;
import com.example.quizbay.model.QuizContextMapping;
import com.example.quizbay.model.ScoreDynamic;
import com.example.quizbay.model.UserScore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DynamicQuizActivity5 extends AppCompatActivity {


    private boolean ans=false;
    private static SimpleExoPlayer player;
    private int score;
    private boolean isClicked=false;
    UserScore userScore = new UserScore();
    private int diffLevel;
    private Long timeRequired;
    private LeaderBoard leaderBoard;

    private int skipCount;
    private boolean status;
    private int correctAnswerCount;
    TextView tv_showtime;
    private double timeTaken;
    private ScoreDynamic scoreDynamic;
    private PlayerView playerView;
    private TextView tv_question;
    private Button btn_endQuiz;
    private Button btn_Skip;
    private TextView tv_optionA;
    private TextView tv_optionB;
    private TextView tv_userScore;
    private TextView tv_optionC;
    private TextView tv_optionD;
    private ImageView iv_questionImage;
    private CardView cv_OptionA;
    private CardView cv_OptionB;
    private CardView cv_OptionC;
    private CardView cv_OptionD;
    List<String> correctAnswer;
    private CardView cv_options;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private String questionString;
    String quizId;
    public static SimpleExoPlayer getPlayer(Context context) {
        if (player == null) {
            player = new SimpleExoPlayer.Builder(context).build();
        }
        player.clearMediaItems();
        return player;
    }
    private String hashmapstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_bay__question);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        hashmapstring = sharedPreferences.getString("qb_dynamicHash","empty");
        if (!hashmapstring.equals("empty"))
        {
            Gson gson = new Gson();
            QuizContextMapping quizContextMapping = gson.fromJson(hashmapstring,QuizContextMapping.class);
            quizId = getKey(quizContextMapping.getQuizContext(),"DynamicQuizActivity5.class");
            Log.d("QuizId", "onCreate: "+quizId);
        }

        setView();

    }


    public String getKey(HashMap<String, String> map, String value) {
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void setView()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        questionString = sharedPreferences.getString(quizId,"0");
        if (!questionString.equals("0"))
        {
            ObjectMapper objectMapper = new ObjectMapper();
            QuestionDynamic question = new QuestionDynamic();
            try {
                question = objectMapper.readValue(questionString, QuestionDynamic.class);
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
            Log.d("onDynamic", "onMessageReceived: "+question.toString());

        }else
        {
            Toast.makeText(this, "No Question Notification", Toast.LENGTH_SHORT).show();
        }
    }

}
