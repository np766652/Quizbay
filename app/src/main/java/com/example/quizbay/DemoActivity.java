package com.example.quizbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizbay.activity.QuizDynamicQuestion;
import com.example.quizbay.activity.QuizbayHomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        Button button = findViewById(R.id.btn_start_dynamic_quiz1);




//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        Log.d("TAG", msg);
//                        Toast.makeText(DemoActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//        FirebaseMessaging.getInstance().subscribeToTopic("all")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = getString(R.string.msg_subscribed);
//                        if (!task.isSuccessful()) {
//                            msg = getString(R.string.msg_subscribe_failed);
//                        }
//                        Log.d("TAG", msg);
//                        Toast.makeText(DemoActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//        FirebaseMessaging.getInstance().subscribeToTopic("all")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = getString(R.string.msg_subscribed);
//                        if (!task.isSuccessful()) {
//                            msg = getString(R.string.msg_subscribe_failed);
//                        }
//                        Log.d("TAG", msg);
//                        Toast.makeText(DemoActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//                    }
//                });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity.this, QuizDynamicQuestion.class);
                startActivity(intent);
            }
        });
    }
}