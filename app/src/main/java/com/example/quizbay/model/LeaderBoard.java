package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LeaderBoard implements Serializable {

    @SerializedName(value = "userId")
    private String userId;

    @SerializedName(value = "quizId")
    private int quizId;

    @SerializedName(value = "userName")
    private String userName;

    @SerializedName(value = "score")
    private int score;

   @SerializedName(value = "quizName")
    private String quizName;

   @SerializedName(value = "rank")
    private int rank;


    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getQuizName() {
        return quizName;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "LeaderBoard{" +
                "userId='" + userId + '\'' +
                ", quizId=" + quizId +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", quizName='" + quizName + '\'' +
                ", rank=" + rank +
                '}';
    }
}
