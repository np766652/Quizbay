package com.example.quizbay.builder;

import com.example.quizbay.model.DynamicQuizNamesListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Quizbay_IAPi_GetActiveDynamicQuiz {
    @GET("quizbay/contest/getactivedynamicquizzes")
    Call<List<DynamicQuizNamesListModel>> getactivedynamicquizzes();
}
