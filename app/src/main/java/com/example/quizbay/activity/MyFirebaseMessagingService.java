package com.example.quizbay.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quizbay.model.Questions;
import com.example.quizbay.model.QuizContextMapping;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.d("FireBase", "onMessageReceived: " + remoteMessage.getData());

        String click_action=remoteMessage.getNotification().getClickAction();

        if(remoteMessage.getData().containsKey("question")) {

            String quizId =remoteMessage.getData().get("question");

            Log.d("FireBaseQuiz", "onMessageReceived: "+quizId);
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(quizId,remoteMessage.getData().get(quizId));
            editor.commit();
//            Map<String,String> data = remoteMessage.getData();
//            String quizId =
//                    String question = data.get("question");
            Log.d("FireBase", "onMessageReceived: " + remoteMessage.getData().get(quizId));
//            SharedPreferences  sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("QuestionId"+1,question);
//            editor.commit();


        }
        else
        {

        }
    }
}
