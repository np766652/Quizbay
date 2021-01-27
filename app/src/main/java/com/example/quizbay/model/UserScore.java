package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserScore implements Serializable {

	@SerializedName("score")
	private int score;

	@SerializedName("timeTaken")
	private double timeTaken;

	@SerializedName("quizId")
	private int quizId;

	@SerializedName("correctCount")
	private int correctCount;

	@SerializedName("numberOfQuestions")
	private int numberOfQuestions;

	@SerializedName("currentQuestionCount")
	private int currentQuestionCount;

	@SerializedName("skippedQuestions")
	private int skippedQuestions;

	@SerializedName("userId")
	private String userId;

	@SerializedName("quizStatus")
	private boolean quizStatus;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setTimeTaken(double timeTaken){
		this.timeTaken = timeTaken;
	}

	public double getTimeTaken(){
		return timeTaken;
	}

	public void setQuizId(int quizId){
		this.quizId = quizId;
	}

	public int getQuizId(){
		return quizId;
	}

	public void setCorrectCount(int correctCount){
		this.correctCount = correctCount;
	}

	public int getCorrectCount(){
		return correctCount;
	}

	public void setNumberOfQuestions(int numberOfQuestions){
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getNumberOfQuestions(){
		return numberOfQuestions;
	}

	public void setCurrentQuestionCount(int currentQuestionCount){
		this.currentQuestionCount = currentQuestionCount;
	}

	public int getCurrentQuestionCount(){
		return currentQuestionCount;
	}

	public void setSkippedQuestions(int skippedQuestions){
		this.skippedQuestions = skippedQuestions;
	}

	public int getSkippedQuestions(){
		return skippedQuestions;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setQuizStatus(boolean quizStatus){
		this.quizStatus = quizStatus;
	}

	public boolean getQuizStatus(){
		return quizStatus;
	}

	@Override
 	public String toString(){
		return 
			"UserScore{" + 
			"score = '" + score + '\'' + 
			",timeTaken = '" + timeTaken + '\'' + 
			",quizId = '" + quizId + '\'' + 
			",correctCount = '" + correctCount + '\'' + 
			",numberOfQuestions = '" + numberOfQuestions + '\'' + 
			",currentQuestionCount = '" + currentQuestionCount + '\'' + 
			",skippedQuestions = '" + skippedQuestions + '\'' + 
			",userId = '" + userId + '\'' + 
			",quizStatus = '" + quizStatus + '\'' + 
			"}";
		}
}