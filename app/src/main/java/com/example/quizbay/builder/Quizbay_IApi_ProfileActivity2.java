package com.example.quizbay.builder;

import com.example.quizbay.model.QuizbayProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Quizbay_IApi_ProfileActivity2 {

    @GET("quizbay/leaderboard/user/{userId}")
    Call<List<QuizbayProfileModel>> getLeaderBoardByUserId(@Path("userId") String userId);
}
