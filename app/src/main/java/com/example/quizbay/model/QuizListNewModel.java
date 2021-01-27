package com.example.quizbay.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuizListNewModel{

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

	public void setQuestions(List<QuestionsItem> questions){
		this.questions = questions;
	}

	public List<QuestionsItem> getQuestions(){
		return questions;
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

	@Override
 	public String toString(){
		return 
			"QuizListNewModel{" + 
			"mostAnsweredQuestionId = '" + mostAnsweredQuestionId + '\'' + 
			",masterId = '" + masterId + '\'' + 
			",noOfQuestions = '" + noOfQuestions + '\'' + 
			",quizId = '" + quizId + '\'' + 
			",quizName = '" + quizName + '\'' + 
			",quizType = '" + quizType + '\'' + 
			",questions = '" + questions + '\'' + 
			",startTime = '" + startTime + '\'' + 
			",endTime = '" + endTime + '\'' + 
			",category = '" + category + '\'' + 
			",quizStatus = '" + quizStatus + '\'' + 
			"}";
		}
}