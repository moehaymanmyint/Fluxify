package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    public static int SPLASH_TIMER = 3000;

    ImageView logoImg;
    TextView logoName, appName, tagline;
    Animation sideAnim, bottomAnim;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        logoImg = findViewById(R.id.logoImg);
        logoName = findViewById(R.id.logoName);
        appName = findViewById(R.id.appName);
        tagline = findViewById(R.id.tagline);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        logoImg.setAnimation(sideAnim);
        logoName.setAnimation(bottomAnim);
        appName.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainScreen.class);
                startActivities(new Intent[]{intent});
                finish();
            }
        },SPLASH_TIMER);
    }
}