package com.example.a2pack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a2pack.R;
import com.example.a2pack.activity.LoginActivity;
import com.example.a2pack.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private static final int DELAY_TIME = 2000;
    TextView textView;
    ImageView imageView;
    Animation side,bottom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.text);
        imageView = findViewById(R.id.logo);

        side = AnimationUtils.loadAnimation(this,R.anim.side_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageView.setAnimation(side);
        textView.setAnimation(bottom);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser firebaseUser = auth.getCurrentUser();
                if (firebaseUser == null){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },DELAY_TIME);

    }
}