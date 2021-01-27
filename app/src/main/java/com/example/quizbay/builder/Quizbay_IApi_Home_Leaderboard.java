package com.example.quizbay.builder;

import com.example.quizbay.model.LeaderboardModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Quizbay_IApi_Home_Leaderboard {
    @GET("quizbay/leaderboard/overallleaderboard")
    Call<List<LeaderboardModel>> getOverallLeaderBoard();
}
