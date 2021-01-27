package com.example.quizbay.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuestionsItem{

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

	@SerializedName("correctAnswer")
	private List<String> correctAnswer;

	@SerializedName("questionType")
	private String questionType;

	@SerializedName("difficultLevel")
	private int difficultLevel;

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

	public void setDifficultLevel(int difficultLevel){
		this.difficultLevel = difficultLevel;
	}

	public int getDifficultLevel(){
		return difficultLevel;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsItem{" + 
			"questionId = '" + questionId + '\'' + 
			",questionSrc = '" + questionSrc + '\'' + 
			",options = '" + options + '\'' + 
			",answerType = '" + answerType + '\'' + 
			",count = '" + count + '\'' + 
			",questionName = '" + questionName + '\'' + 
			",correctAnswer = '" + correctAnswer + '\'' + 
			",questionType = '" + questionType + '\'' + 
			",difficultLevel = '" + difficultLevel + '\'' + 
			"}";
		}
}