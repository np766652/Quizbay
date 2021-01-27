package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class QuestionDynamic {

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

    @SerializedName("quizId")
    private  int quizId;

    @SerializedName("timeStamp")
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "QuestionDynamic{" +
                "questionId=" + questionId +
                ", questionSrc='" + questionSrc + '\'' +
                ", options=" + options +
                ", answerType='" + answerType + '\'' +
                ", count=" + count +
                ", questionName='" + questionName + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", correctAnswer=" + correctAnswer +
                ", questionType='" + questionType + '\'' +
                ", timeRequired=" + timeRequired +
                ", quizId=" + quizId +
                ", timeStamp=" + date +
                '}';
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionSrc() {
        return questionSrc;
    }

    public void setQuestionSrc(String questionSrc) {
        this.questionSrc = questionSrc;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Long timeRequired) {
        this.timeRequired = timeRequired;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
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
