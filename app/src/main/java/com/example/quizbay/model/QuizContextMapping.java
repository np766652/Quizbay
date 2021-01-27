package com.example.quizbay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

public class QuizContextMapping implements Serializable {

    @SerializedName("quizContext")
   private HashMap<String,String> quizContext;

    public HashMap<String, String> getQuizContext() {
        return quizContext;
    }

    public void setQuizContext(HashMap<String, String> quizContext) {
        this.quizContext = quizContext;
    }



    @Override
    public String toString() {
        return "QuizContextMapping{" +
                "quizContext=" + quizContext +
                '}';
    }
}
