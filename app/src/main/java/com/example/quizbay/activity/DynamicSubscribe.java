package com.example.quizbay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizbay.R;
import com.example.quizbay.model.QuizContextMapping;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.HashMap;

public class DynamicSubscribe extends AppCompatActivity {

    private String quizId;
    private Button btn_subscribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_subscribe);

        quizId=getIntent().getStringExtra("QuizId");
        btn_subscribe = findViewById(R.id.btn_quizbay_dynamic_subsribe);

        btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                QuizContextMapping quizContextMapping = new QuizContextMapping();
                Gson gson = new Gson();
                boolean flag=false;
               String hashmapString = sharedPreferences.getString("qb_dynamicHash","empty");
                Log.d("hashmap", "onClick: "+hashmapString);
               if(hashmapString.equals("empty"))
               {
                   Log.d("before", "onClick: "+hashmapString);
                   HashMap<String,String> hashMap = new HashMap<String, String>();
                   hashMap.put(quizId,"DynamicQuizActivity1.class");
                   quizContextMapping.setQuizContext(hashMap);
                   Log.d("Subscribed", "onClick: 6");
                   flag=true;
                 String jsonString= gson.toJson(quizContextMapping);
                 editor.putString("qb_dynamicHash",jsonString);
                   editor.commit();
               }
               else
               {
                   QuizContextMapping quizContextMappings = gson.fromJson(hashmapString,QuizContextMapping.class);
                   if (!quizContextMappings.getQuizContext().containsValue("DynamicQuizActivity1.class"))
                   {
                       Log.d("Subscribed", "onClick: 1");
                       flag=true;
                       quizContextMappings.getQuizContext().put(quizId,"DynamicQuizActivity1.class");
                   }else if (!quizContextMappings.getQuizContext().containsValue("DynamicQuizActivity2.class"))
                   {
                       Log.d("Subscribed", "onClick: 2");
                       flag=true;
                       quizContextMappings.getQuizContext().put(quizId,"DynamicQuizActivity2.class");
                   }else if (!quizContextMappings.getQuizContext().containsValue("DynamicQuizActivity3.class"))
                   {
                       Log.d("Subscribed", "onClick: 3");
                       flag=true;
                       quizContextMappings.getQuizContext().put(quizId,"DynamicQuizActivity3.class");
                   }else if (!quizContextMappings.getQuizContext().containsValue("DynamicQuizActivity4.class"))
                   {
                       Log.d("Subscribed", "onClick: 4");
                       flag=true;
                       quizContextMappings.getQuizContext().put(quizId,"DynamicQuizActivity4.class");
                   }else if (!quizContextMappings.getQuizContext().containsValue("DynamicQuizActivity5.class"))
                   {
                       Log.d("Subscribed", "onClick: 5");
                       flag=true;
                       quizContextMappings.getQuizContext().put(quizId,"DynamicQuizActivity5.class");
                   }
                   else
                   {
                       Toast.makeText(DynamicSubscribe.this, "You Have Subscription Limit", Toast.LENGTH_SHORT).show();
                   }
                   String jsonString= gson.toJson(quizContextMappings);
                   editor.putString("qb_dynamicHash",jsonString);
                   editor.commit();




               }

               if (flag) {
                   FirebaseMessaging.getInstance().subscribeToTopic(quizId)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {

                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   String msg = getString(R.string.msg_subscribed);
                                   if (!task.isSuccessful()) {
                                       msg = getString(R.string.msg_subscribe_failed);
                                   }
                                   Log.d("TAG", msg);
                                   Toast.makeText(DynamicSubscribe.this, "You Subscribe to Quiz" + quizId, Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(DynamicSubscribe.this, DynamicQuiz_RecyclerActivity.class);
                                   startActivity(intent);
                                   finish();

                               }
                           });
               }

            }
        });

    }
}