package com.example.quizbay.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.example.quizbay.builder.RetrofitBuilderLeaderBoard;
import com.example.quizbay.builder.RetrofitBuilderQuestion;
import com.example.quizbay.model.LeaderBoard;
import com.example.quizbay.model.Questions;
import com.example.quizbay.model.UserScore;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuizBay_Question_Activity extends AppCompatActivity {

    private static SimpleExoPlayer player;
    private int score;
    private boolean isClicked=false;
    UserScore userScore = new UserScore();
    private int diffLevel;
    private Long timeRequired;
    private LeaderBoard leaderBoard;
    private String quizId;
    private int skipCount;
    private boolean status;
    private int correctAnswerCount;
    TextView tv_showtime;
    private double timetaken;
    private UserScore userScoreprev;
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
    private CountDownTimer countdownTime;

    public static SimpleExoPlayer getPlayer(Context context) {
        if (player == null) {
            player = new SimpleExoPlayer.Builder(context).build();
        }
        player.clearMediaItems();
        return player;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_bay__question);
        Date currentTime = Calendar.getInstance().getTime();

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





//        initializePlayer();
       cv_OptionA.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               countdownTime.cancel();
               if (!isClicked) {
                   isClicked=true;
                   btn_Skip.setEnabled(false);
                   Date clickTime = Calendar.getInstance().getTime();
                   timetaken = clickTime.getTime() - currentTime.getTime();
                   userScore.setTimeTaken(timetaken);
                   if (correctAnswer.contains("A")) {
                       correctAnswerCount += 1;
                       userScore.setCorrectCount(correctAnswerCount);
                       score += diffLevel;
                       userScore.setScore(score);
                       cv_OptionA.setCardBackgroundColor(Color.rgb(0, 255, 0));

                   } else {
                       userScore.setCorrectCount(correctAnswerCount);
                       userScore.setScore(score);
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

                   if (status == false)
                       setNextPage();
                   else
                       setLeaderBoard();

               }
           }
       });

        cv_OptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownTime.cancel();
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timetaken = clickTime.getTime() - currentTime.getTime();
                    userScore.setTimeTaken(timetaken);
                    if (correctAnswer.contains("B")) {
                        correctAnswerCount += 1;
                        userScore.setCorrectCount(correctAnswerCount);
                        score += diffLevel;
                        userScore.setScore(score);
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

                    if (status == false)
                        setNextPage();
                    else
                        setLeaderBoard();

                }
            }
        });


        cv_OptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownTime.cancel();
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timetaken = clickTime.getTime() - currentTime.getTime();
                    userScore.setTimeTaken(timetaken);
                    if (correctAnswer.contains("C")) {
                        correctAnswerCount += 1;
                        userScore.setCorrectCount(correctAnswerCount);
                        score += diffLevel;
                        userScore.setScore(score);
                        cv_OptionC.setCardBackgroundColor(Color.rgb(0, 255, 0));
                    } else {
                        userScore.setCorrectCount(correctAnswerCount);
                        userScore.setScore(score);
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

                    if (status == false)
                        setNextPage();
                    else

                        setLeaderBoard();
                }
            }
        });

        cv_OptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownTime.cancel();
                if (!isClicked) {
                    isClicked = true;
                    Date clickTime = Calendar.getInstance().getTime();
                    timetaken = clickTime.getTime() - currentTime.getTime();
                    userScore.setTimeTaken(timetaken);
                    if (correctAnswer.contains("D")) {
                        correctAnswerCount += 1;
                        userScore.setCorrectCount(correctAnswerCount);
                        score += diffLevel;
                        userScore.setScore(score);
                        cv_OptionD.setCardBackgroundColor(Color.rgb(0, 255, 0));
                    } else {
                        userScore.setCorrectCount(correctAnswerCount);
                        userScore.setScore(score);
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

                    if (status == false)
                        setNextPage();
                    else
                        setLeaderBoard();
                }
            }
        });


                btn_Skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isClicked) {
                            isClicked=true;
                            countdownTime.cancel();
                            Date clickTime = Calendar.getInstance().getTime();
                            timetaken = clickTime.getTime() - currentTime.getTime();
                            userScore.setTimeTaken(timetaken);
                            userScore.setCorrectCount(correctAnswerCount);
                            skipCount++;
                            userScore.setSkippedQuestions(skipCount);
                            userScore.setScore(score);
                            setNextPage();
                        }
//                        if(skipCount<=3){
//                            setNextPage();
//                        Toast.makeText(com.example.quizbay.activity.QuizBay_Question_Activity.this, "Skipped", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(com.example.quizbay.activity.QuizBay_Question_Activity.this, "Skip Limit Reached", Toast.LENGTH_SHORT).show();
//                            setLeaderBoard();
//                        }
                    }
                });

                btn_endQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isClicked) {
                            isClicked=true;
                            countdownTime.cancel();
                            status = true;
                            setLeaderBoard();
                        }

                    }
                });

    }

    private void setLeaderBoard()
    {

        userScore.setUserId(userScoreprev.getUserId());
        userScore.setQuizId(userScoreprev.getQuizId());
        userScore.setNumberOfQuestions(userScoreprev.getNumberOfQuestions());
        userScore.setScore(score);
        userScore.setTimeTaken(timetaken);
        userScore.setQuizStatus(status);
        userScore.setCorrectCount(correctAnswerCount);
        Log.d("Status", "setLeaderBoard: "+userScore.getQuizStatus());
        userScore.setSkippedQuestions(skipCount);
        userScore.setCurrentQuestionCount(userScoreprev.getCurrentQuestionCount()+1);
        Retrofit retrofit = RetrofitBuilderQuestion.getInstance();
        IQuizBayStatic iQuizBayStatic = retrofit.create(IQuizBayStatic.class);
        Log.d("UserScore", "setLeaderBoard: "+userScore.toString());
        Call<LeaderBoard> leaderBoardCall = iQuizBayStatic.endquiz(userScore);

        Callback<LeaderBoard> callback = new Callback<LeaderBoard>() {
            @Override
            public void onResponse(Call<LeaderBoard> call, Response<LeaderBoard> response) {
                leaderBoard = response.body();
                Retrofit retrofitLeaderBoard = RetrofitBuilderLeaderBoard.getInstance();
                IQuizBayStatic iQuizBayStaticLeader = retrofitLeaderBoard.create(IQuizBayStatic.class) ;

                Call<Void> voidCall = iQuizBayStaticLeader.saveLeaderBoard(leaderBoard);
                Callback<Void> callbackLeaderBoard = new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("LeaderBoardResponse", "onResponse: "+response.message());

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("LeaderBoard", "onResponse: "+t.getMessage());
                    }
                };
                voidCall.enqueue(callbackLeaderBoard);
                Log.d("EndQuiz", "onResponse: "+leaderBoard.toString());
            }

            @Override
            public void onFailure(Call<LeaderBoard> call, Throwable t) {
                Log.d("EndQuizFail", "onFailure: "+t.getMessage());
            }
        };
        leaderBoardCall.enqueue(callback);



        Intent intent = new Intent(com.example.quizbay.activity.QuizBay_Question_Activity.this,QuizbayHomeActivity.class);

        startActivity(intent);


    }
    private void setNextPage()
    {

        userScore.setUserId(userScoreprev.getUserId());
        userScore.setQuizId(userScoreprev.getQuizId());
        userScore.setNumberOfQuestions(userScoreprev.getNumberOfQuestions());
        userScore.setQuizStatus(status);
        userScore.setSkippedQuestions(skipCount);
        userScore.setCurrentQuestionCount(userScoreprev.getCurrentQuestionCount()+1);
        userScore.setCorrectCount(correctAnswerCount);
        userScore.setScore(score);
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String scoreJson = gson.toJson(userScore);


        //change QuizId to Variable
        editor.putString(quizId, scoreJson);
        editor.commit();


        Intent intent = new Intent(com.example.quizbay.activity.QuizBay_Question_Activity.this, com.example.quizbay.activity.QuizBay_Question_Activity.class);
        intent.putExtra("QuizId",quizId);
        Log.d("finalQuiz", "run: "+quizId);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                releasePlayer();
                startActivity(intent);

            }
        }, 2000);
    }

//
    private void setView()
    {
        SharedPreferences  sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        quizId = getIntent().getStringExtra("QuizId");
        //change QuizId to Variable
        Log.d("quizId", "setView: "+quizId);
        String json = sharedPreferences.getString(quizId,"a");

        Log.d("json", "setView: " + json);
        userScoreprev =  gson.fromJson(json, UserScore.class);
        Log.d("questionElse", "setView: "+userScoreprev.getNumberOfQuestions());
        Log.d("questionElse", "setView: "+userScoreprev.getNumberOfQuestions());
        score=userScoreprev.getScore();
        correctAnswerCount=userScoreprev.getCorrectCount();
        skipCount=userScoreprev.getSkippedQuestions();
        tv_userScore.setText(String.valueOf(score));
        if(userScoreprev.getSkippedQuestions()>2)
            btn_Skip.setVisibility(View.GONE);
        if (userScoreprev.getCurrentQuestionCount()+1==userScoreprev.getNumberOfQuestions())
        {
            btn_Skip.setVisibility(View.GONE);
            status=true;
        }

        Retrofit retrofit = RetrofitBuilderQuestion.getInstance();
        IQuizBayStatic iQuizBayStatic = retrofit.create(IQuizBayStatic.class);

        Call<Questions> questionCall = iQuizBayStatic.getquestion(userScoreprev);
        Log.d("APi" , "Called");
        Callback<Questions> callback = new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {

                Log.d("APi", "onResponse: " + response.body().getQuestionName());
                Toast.makeText(com.example.quizbay.activity.QuizBay_Question_Activity.this, "hjsdffsdj", Toast.LENGTH_SHORT).show();
                Questions question = response.body();
                timeRequired=question.getTimeRequired();
                Log.d("Time", "onResponse: "+question.toString());
                diffLevel=question.getDifficultyLevel();
                tv_optionA.setText(question.getOptionA());
                tv_optionB.setText(question.getOptionB());
                tv_optionC.setText(question.getOptionC());
                tv_optionD.setText(question.getOptionD());
                tv_question.setText(question.getQuestionName());
                correctAnswer = question.getCorrectAnswer();
                countdownTime= new CountDownTimer(timeRequired, 1000) {

                    public void onTick(long millisUntilFinished) {
                        tv_showtime.setText( String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        Date clickTime = Calendar.getInstance().getTime();
                        timetaken = timeRequired;
                        userScore.setTimeTaken(timetaken);
                        userScore.setSkippedQuestions(skipCount);
                        setNextPage();
//                        skipCount++;

//                        if(skipCount<=3)
//                            setNextPage();
//                        else
//                        {
//                            Toast.makeText(com.example.quizbay.activity.QuizBay_Question_Activity.this, "Skip Limit Reached", Toast.LENGTH_SHORT).show();
//                            setLeaderBoard();
//                        }
                    }
                }.start();

                Log.d("APi", "Answer: "+correctAnswer.get(0));
                if (question.getQuestionType().equals("text"))
                {
                    iv_questionImage.setVisibility(View.GONE);
                    playerView.setVisibility(View.GONE);
                }
                else if (question.getQuestionType().equals("image"))
                {

                    playerView.setVisibility(View.GONE);
                    Glide.with(com.example.quizbay.activity.QuizBay_Question_Activity.this)
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




            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                Log.d("APi", "onFailure: " + t.getMessage());
                Toast.makeText(com.example.quizbay.activity.QuizBay_Question_Activity.this, "Crashes", Toast.LENGTH_SHORT).show();
            }
        };
        questionCall.enqueue(callback);


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
        player= getPlayer(com.example.quizbay.activity.QuizBay_Question_Activity.this);
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

//
//    @Override
//    public void onPause() {
//        super.onPause();
//        player.setPlayWhenReady(false);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        player.setPlayWhenReady(true);
//    }

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