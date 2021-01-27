package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizDetails {

	@SerializedName("mostAnsweredQuestionId")
	private int mostAnsweredQuestionId;

	@SerializedName("masterId")
	private String masterId;

	@SerializedName("noOfQuestions")
	private int noOfQuestions;

	@SerializedName("quizId")
	private int quizId;

	@SerializedName("quizName")
	private String quizName;

	@SerializedName("quizType")
	private boolean quizType;

	@SerializedName("startTime")
	private String startTime;

	@SerializedName("endTime")
	private String endTime;

	@SerializedName("category")
	private String category;

	@SerializedName("quizStatus")
	private int quizStatus;

    @Override
    public String toString() {
        return "QuizDetails{" +
                "mostAnsweredQuestionId=" + mostAnsweredQuestionId +
                ", masterId='" + masterId + '\'' +
                ", noOfQuestions=" + noOfQuestions +
                ", quizId=" + quizId +
                ", quizName='" + quizName + '\'' +
                ", quizType=" + quizType +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", category='" + category + '\'' +
                ", quizStatus=" + quizStatus +
                ", listQuestion=" + listQuestion +
                '}';
    }

    public List<Questions> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<Questions> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @SerializedName("questions")
    private List<Questions> listQuestion;

	public void setMostAnsweredQuestionId(int mostAnsweredQuestionId){
		this.mostAnsweredQuestionId = mostAnsweredQuestionId;
	}

	public int getMostAnsweredQuestionId(){
		return mostAnsweredQuestionId;
	}

	public void setMasterId(String masterId){
		this.masterId = masterId;
	}

	public String getMasterId(){
		return masterId;
	}

	public void setNoOfQuestions(int noOfQuestions){
		this.noOfQuestions = noOfQuestions;
	}

	public int getNoOfQuestions(){
		return noOfQuestions;
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

	public void setQuizType(boolean quizType){
		this.quizType = quizType;
	}

	public boolean isQuizType(){
		return quizType;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setQuizStatus(int quizStatus){
		this.quizStatus = quizStatus;
	}

	public int getQuizStatus(){
		return quizStatus;
	}

}