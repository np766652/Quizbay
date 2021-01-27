package com.example.quizbay.builder;

import com.example.quizbay.model.LeaderBoard;
import com.example.quizbay.model.Questions;
import com.example.quizbay.model.ScoreDynamic;
import com.example.quizbay.model.UserScore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IQuizBayStatic {

    @POST("quizbay/user/continueQuiz")
    Call<Questions> getquestion(@Body UserScore userScore);

    @POST("quizbay/user/endQuiz")
    Call<LeaderBoard> endquiz(@Body UserScore userScore);

    @POST("quizbay/leaderboard/upload")
    Call<Void> saveLeaderBoard(@Body LeaderBoard leaderBoard);

    @GET("quizbay/user/state/{quizId}/{userId}")
    Call<UserScore> getState(@Path("quizId") int quizId,@Path("userId") String userId);

    @POST("quizbay/user/updatescore")
    Call<Void> sendScore(@Body ScoreDynamic scoreDynamic);
}
