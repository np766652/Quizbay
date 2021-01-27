package com.example.quizbay.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.quizbay.R;
import com.example.quizbay.builder.IQuizBayStatic;
import com.example.quizbay.builder.RetrofitBuilder;
import com.example.quizbay.builder.RetrofitBuilderQuestion;
import com.example.quizbay.model.LeaderBoard;
import com.example.quizbay.model.QuestionDynamic;
import com.example.quizbay.model.Questions;
import com.example.quizbay.model.QuizContextMapping;
import com.example.quizbay.model.ScoreDynamic;
import com.example.quizbay.model.UserScore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DynamicQuizActivity1 extends AppCompatActivity {


    private boolean ans=false;
    private Date currentTime;
    private Date publishingTime;
    private String userId;
    private static SimpleExoPlayer player;
    private int score;
    private boolean isClicked=false;
    UserScore userScore = new UserScore();
    private int diffLevel;
    private Long timeRequired;
    private LeaderBoard leaderBoard;
   private QuestionDynamic question;
    private int skipCount;
    private boolean status;
    private int correctAnswerCount;
    TextView tv_showtime;
    private double timeTaken;
    private ScoreDynamic scoreDynamic = new ScoreDynamic();
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
        userId=sharedPreferences.getString("qb_userId","1");
        hashmapstring = sharedPreferences.getString("qb_dynamicHash","empty");
        if (!hashmapstring.equals("empty"))
        {
            Gson gson = new Gson();
            QuizContextMapping quizContextMapping = gson.fromJson(hashmapstring,QuizContextMapping.class);
            quizId = getKey(quizContextMapping.getQuizContext(),"DynamicQuizActivity1.class");
            Log.d("QuizId", "onCreate: "+quizId);
        }

         currentTime = Calendar.getInstance().getTime();
        btn_endQuiz=findViewById(R.id.btn_quizbay_static_endquiz);
        btn_Skip=findViewById(R.id.btn_quizbay_static_skip);
        playerView = findViewById(R.id.include_static_question);;
        tv_question = findViewById(R.id.tv_quizbay_static_question);
        tv_optionA  = findViewById(R.id.tv_quizbay_static_optionA);
        tv_optionB  = findViewById(R.id.tv_quizbay_static_optionB);
        tv_optionC  = findViewById(R.id.tv_quizbay_static_optionC);
        tv_optionD  = findViewById(R.id.tv_quizbay_static_optionD);
        iv_questionImage =findViewById(R.id.iv_quizbay_static_image);
        cv_OptionA = findViewById(R.id.cv_quizbay_static_optionA);
        cv_OptionB = findViewById(R.id.cv_quizbay_static_optionB);
        cv_OptionC = findViewById(R.id.cv_quizbay_static_optionC);
        cv_OptionD = findViewById(R.id.cv_quizbay_static_optionD);
        cv_options = findViewById(R.id.cv_quizbay_static_buttons);
        tv_userScore = findViewById(R.id.tv_quizbay_static_score);
        tv_showtime = findViewById(R.id.tv_quizbay_static_time);
        setView();
        cv_OptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked=true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timeTaken = clickTime.getTime() - publishingTime.getTime();
                    scoreDynamic.setTimeTaken(timeTaken);

                    if (correctAnswer.contains("A")) {
                        scoreDynamic.setScore(1);
                        cv_OptionA.setCardBackgroundColor(Color.rgb(0, 255, 0));

                    } else {
//                        userScore.setCorrectCount(correctAnswerCount);
//                        userScore.setScore(score);
                        cv_OptionA.setCardBackgroundColor(Color.rgb(255, 0, 0));
                        if (correctAnswer.contains("B")) {
                            cv_OptionB.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("C")) {
                            cv_OptionC.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("D")) {
                            cv_OptionD.setCardBackgroundColor(Color.rgb(0, 255, 0));

                        }

                    }


                }
                sendScore();
                fastestUser();
            }
        });

        cv_OptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timeTaken = clickTime.getTime() - publishingTime.getTime();
                    scoreDynamic.setTimeTaken(timeTaken);
                    //   userScore.setTimeTaken(timetaken);
                    if (correctAnswer.contains("B")) {
//                        correctAnswerCount += 1;
//                        userScore.setCorrectCount(correctAnswerCount);
//                        score += diffLevel;
//                        userScore.setScore(score);
                        scoreDynamic.setScore(1);
                        cv_OptionB.setCardBackgroundColor(Color.rgb(0, 255, 0));
                    } else {
                        userScore.setCorrectCount(correctAnswerCount);
                        userScore.setScore(score);
                        cv_OptionB.setCardBackgroundColor(Color.rgb(255, 0, 0));
                        if (correctAnswer.contains("A")) {
                            cv_OptionA.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("C")) {
                            cv_OptionC.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("D")) {
                            cv_OptionD.setCardBackgroundColor(Color.rgb(0, 255, 0));

                        }

                    }


                }

                sendScore();
                fastestUser();
            }
        });


        cv_OptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timeTaken = clickTime.getTime() - publishingTime.getTime();
                    scoreDynamic.setTimeTaken(timeTaken);
                    if (correctAnswer.contains("C")) {
//                        correctAnswerCount += 1;
//                        userScore.setCorrectCount(correctAnswerCount);
//                        score += diffLevel;
//                        userScore.setScore(score);
                        scoreDynamic.setScore(1);
                        cv_OptionC.setCardBackgroundColor(Color.rgb(0, 255, 0));
                    } else {
//                        userScore.setCorrectCount(correctAnswerCount);
//                        userScore.setScore(score);
                        cv_OptionC.setCardBackgroundColor(Color.rgb(255, 0, 0));
                        if (correctAnswer.contains("B")) {
                            cv_OptionB.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("A")) {
                            cv_OptionA.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("D")) {
                            cv_OptionD.setCardBackgroundColor(Color.rgb(0, 255, 0));

                        }
                    }

                }

                sendScore();


                fastestUser();
            }
        });

        cv_OptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timeTaken = clickTime.getTime() - publishingTime.getTime();
                    scoreDynamic.setTimeTaken(timeTaken);
                    //  userScore.setTimeTaken(timetaken);
                    if (correctAnswer.contains("D")) {
//                        correctAnswerCount += 1;
//                        userScore.setCorrectCount(correctAnswerCount);
//                        score += diffLevel;
//                        userScore.setScore(score);
                        scoreDynamic.setScore(1);
                        cv_OptionD.setCardBackgroundColor(Color.rgb(0, 255, 0));
                    } else {
//                        userScore.setCorrectCount(correctAnswerCount);
//                        userScore.setScore(score);
                        cv_OptionD.setCardBackgroundColor(Color.rgb(255, 0, 0));
                        if (correctAnswer.contains("B")) {
                            cv_OptionB.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("C")) {
                            cv_OptionC.setCardBackgroundColor(Color.rgb(0, 255, 0));
                        }
                        if (correctAnswer.contains("A")) {
                            cv_OptionA.setCardBackgroundColor(Color.rgb(0, 255, 0));

                        }
                    }


                }

                sendScore();


               fastestUser();
            }
        });


    }

    void fastestUser(){
        Intent intentFastestUser = new Intent(this,Dynamic_Quiz_Fastest_Finger_First_User_Name.class);
        intentFastestUser.putExtra("quizId",quizId);
        startActivity(intentFastestUser);
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
            question = new QuestionDynamic();
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
        publishingTime=question.getDate();
        scoreDynamic.setQuestionId(question.getQuestionId());
        diffLevel=question.getDifficultyLevel();
        tv_optionA.setText(question.getOptionA());
        tv_optionB.setText(question.getOptionB());
        tv_optionC.setText(question.getOptionC());
        tv_optionD.setText(question.getOptionD());
        tv_question.setText(question.getQuestionName());
        correctAnswer = question.getCorrectAnswer();
        timeRequired=question.getTimeRequired();
        tv_userScore.setVisibility(View.GONE);
        // TODO: 26/01/21 Set QuizId and userId
        quizId=String.valueOf(question.getQuizId());
        if (question.getQuestionType().equals("text"))
        {
            iv_questionImage.setVisibility(View.GONE);
            playerView.setVisibility(View.GONE);
        }
        else if (question.getQuestionType().equals("image"))
        {

            playerView.setVisibility(View.GONE);
            Glide.with(com.example.quizbay.activity.DynamicQuizActivity1.this)
                    .load(question.getQuestionSrc())
                    .placeholder(R.drawable.placeholder)
                    .into(iv_questionImage);
        }
        else
        {
            iv_questionImage.setVisibility(View.INVISIBLE);
            if (player!=null)
                initializePlayer1(question.getQuestionSrc());
            else
                initializePlayer(question.getQuestionSrc());
        }


        CountDownTimer countdownTimer= new CountDownTimer(timeRequired+publishingTime.getTime()-currentTime.getTime(), 1000) {

            public void onTick(long millisUntilFinished) {
                tv_showtime.setText( String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                // Intent intent = new Intent(QuizDynamicQuestion.this,WaitingActivity.class);
                score=0;
                scoreDynamic.setTimeTaken(timeRequired);
                sendScore();
                fastestUser();
            }
        }.start();
    }

    public void sendScore()
    {
        scoreDynamic.setQuizId(Integer.parseInt(quizId));
        scoreDynamic.setUserId(userId);
        Retrofit retrofit = RetrofitBuilderQuestion.getInstance();
        IQuizBayStatic iQuizBayStatic = retrofit.create(IQuizBayStatic.class);
        Log.d("abc", "sendScore: "+scoreDynamic.getQuizId());
        Call<Void> voidcall = iQuizBayStatic.sendScore(scoreDynamic);
        Callback<Void> callback = new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("sendScore", "onResponse: "+response.message());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("sendScore", "onFailure: "+t.getMessage());
            }
        };
        voidcall.enqueue(callback);
    }


    private void initializePlayer1(String src) {
        Log.d("APi", "initializePlayer: "+src);

        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(src);
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();

    }



    private void initializePlayer(String src) {
        Log.d("APi", "initializePlayer: "+src);
        player= getPlayer(com.example.quizbay.activity.DynamicQuizActivity1.this);
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(src);
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();

    }



    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }


    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }





}
