package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScoreDynamic implements Serializable {

    @SerializedName(value = "userId")
    private String userId;

    @SerializedName(value = "quizId")
    private int quizId;

    @SerializedName(value = "score")
    private int score;

    @SerializedName(value = "timeTaken")
    private double timeTaken;

    @SerializedName(value = "questionId")
    private int questionId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "ScoreDynamic{" +
                "userId='" + userId + '\'' +
                ", quizId=" + quizId +
                ", score=" + score +
                ", timeTaken=" + timeTaken +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }
}
