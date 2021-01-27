package com.example.quizbay.builder;

import com.example.quizbay.model.QuizListNewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPostApi {

    @GET("quizbay/contest/getActiveQuizzes/{category}")
    Call<List<QuizListNewModel>> getQuizList(@Path("category") String categoryName);
}
