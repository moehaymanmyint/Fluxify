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

    // Declare the duration of the splash screen in milliseconds
    public static int SPLASH_TIMER = 3000;

    // Declare UI elements and animations
    ImageView logoImg;
    TextView logoName, appName, tagline;
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Initialize UI elements
        logoImg = findViewById(R.id.logoImg);
        logoName = findViewById(R.id.logoName);
        appName = findViewById(R.id.appName);
        tagline = findViewById(R.id.tagline);

        // Load animations
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        // Set animations to UI elements
        logoImg.setAnimation(sideAnim);
        logoName.setAnimation(bottomAnim);
        appName.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);

        // Use Handler to delay launching the main screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainScreen.class);
                // Start the activity and finish the splash screen
                startActivities(new Intent[]{intent});
                finish();
            }
        },SPLASH_TIMER);
    }
}