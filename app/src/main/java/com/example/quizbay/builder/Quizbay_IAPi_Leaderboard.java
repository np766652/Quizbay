package com.example.quizbay.builder;

import com.example.quizbay.model.LeaderboardByQuizModel;
import com.example.quizbay.model.ProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Quizbay_IAPi_Leaderboard {
    @GET("quizbay/leaderboard/quiz/{quizId}")
    Call<List<LeaderboardByQuizModel>> getLeaderBoardByQuizId(@Path("quizId") int quizId);

    @GET("quizbay/leaderboard/leaderboardbyuserid/{userId}")
    Call<ProfileModel> getProfileModel(@Path("userId") String userId);
}
