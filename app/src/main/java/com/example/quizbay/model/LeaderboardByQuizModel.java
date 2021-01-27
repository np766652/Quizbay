package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

public class LeaderboardByQuizModel {
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

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void setQuizId(int quizId){
        this.quizId = quizId;
    }

    public int getQuizId(){
        return quizId;
    }

    public void setQuizName(String quizName){
        this.quizName = quizName;
    }

    public String getQuizName(){
        return quizName;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return rank;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }
}
