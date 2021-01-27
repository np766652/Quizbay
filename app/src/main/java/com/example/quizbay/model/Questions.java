package com.example.quizbay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Questions {

	@SerializedName("questionId")
	private int questionId;

	@SerializedName("questionSrc")
	private String questionSrc;

	@SerializedName("options")
	private List<String> options;

	@SerializedName("answerType")
	private String answerType;

	@SerializedName("count")
	private int count;

	@SerializedName("questionName")
	private String questionName;

	@SerializedName("difficultyLevel")
	private int difficultyLevel;

	@SerializedName("correctAnswer")
	private List<String> correctAnswer;

	@SerializedName("questionType")
	private String questionType;

	@SerializedName("timeRequired")
	private Long timeRequired;

	public Long getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(Long timeRequired) {
		this.timeRequired = timeRequired;
	}

	public void setQuestionId(int questionId){
		this.questionId = questionId;
	}

	public int getQuestionId(){
		return questionId;
	}

	public void setQuestionSrc(String questionSrc){
		this.questionSrc = questionSrc;
	}

	public String getQuestionSrc(){
		return questionSrc;
	}

	public void setOptions(List<String> options){
		this.options = options;
	}

	public List<String> getOptions(){
		return options;
	}

	public void setAnswerType(String answerType){
		this.answerType = answerType;
	}

	public String getAnswerType(){
		return answerType;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setQuestionName(String questionName){
		this.questionName = questionName;
	}

	public String getQuestionName(){
		return questionName;
	}

	public void setDifficultyLevel(int difficultylevel){
		this.difficultyLevel = difficultylevel;
	}

	public int getDifficultyLevel(){
		return difficultyLevel;
	}

	public void setCorrectAnswer(List<String> correctAnswer){
		this.correctAnswer = correctAnswer;
	}

	public List<String> getCorrectAnswer(){
		return correctAnswer;
	}



	public void setQuestionType(String questionType){
		this.questionType = questionType;
	}

	public String getQuestionType(){
		return questionType;
	}


	@Override
	public String toString() {
		return "Questions{" +
				"questionId=" + questionId +
				", questionSrc='" + questionSrc + '\'' +
				", options=" + options +
				", answerType='" + answerType + '\'' +
				", count=" + count +
				", questionName='" + questionName + '\'' +
				", difficultylevel=" + difficultyLevel +
				", correctAnswer=" + correctAnswer +
				", questionType='" + questionType + '\'' +
				", timeRequired=" + timeRequired +
				'}';
	}

	public String getOptionA()
	{
		return getOptions().get(0);
	}

	public String getOptionB()
	{
		return getOptions().get(1);
	}

	public String getOptionC()
	{
		return getOptions().get(2);
	}

	public String getOptionD()
	{
		return getOptions().get(3);
	}




}