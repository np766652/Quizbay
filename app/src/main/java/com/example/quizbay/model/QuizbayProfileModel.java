package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

public class QuizbayProfileModel {

    @SerializedName("score")
    private int score;

    @SerializedName("quizId")
    private int quizId;

    @SerializedName("quizName")
    private String quizName;

    @SerializedName("rank")
    private int rank;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private String userId;

    public int getScore(){
        return score;
    }

    public int getQuizId(){
        return quizId;
    }

    public String getQuizName(){
        return quizName;
    }

    public int getRank(){
        return rank;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserId(){
        return userId;
    }
}
