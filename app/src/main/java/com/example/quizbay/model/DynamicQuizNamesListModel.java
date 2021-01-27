package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DynamicQuizNamesListModel {

    @SerializedName("mostAnsweredQuestionId")
    private int mostAnsweredQuestionId;

    @SerializedName("masterId")
    private String masterId;

    @SerializedName("noOfQuestions")
    private int noOfQuestions;

    @SerializedName("quizName")
    private String quizName;

    @SerializedName("quizType")
    private Boolean quizType;

    @SerializedName("questions")
    private List<QuestionsItem> questions;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("category")
    private String category;

    @SerializedName("quizStatus")
    private int quizStatus;

    @SerializedName("quizId")
    private int quizId;


    @Override
    public String toString() {
        return "DynamicQuizNamesListModel{" +
                "mostAnsweredQuestionId=" + mostAnsweredQuestionId +
                ", masterId='" + masterId + '\'' +
                ", noOfQuestions=" + noOfQuestions +
                ", quizName='" + quizName + '\'' +
                ", quizType=" + quizType +
                ", questions=" + questions +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", category='" + category + '\'' +
                ", quizStatus=" + quizStatus +
                ", quizId=" + quizId +
                '}';
    }

    public int getMostAnsweredQuestionId() {
        return mostAnsweredQuestionId;
    }

    public void setMostAnsweredQuestionId(int mostAnsweredQuestionId) {
        this.mostAnsweredQuestionId = mostAnsweredQuestionId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Boolean getQuizType() {
        return quizType;
    }

    public void setQuizType(Boolean quizType) {
        this.quizType = quizType;
    }

    public List<QuestionsItem> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsItem> questions) {
        this.questions = questions;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(int quizStatus) {
        this.quizStatus = quizStatus;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
