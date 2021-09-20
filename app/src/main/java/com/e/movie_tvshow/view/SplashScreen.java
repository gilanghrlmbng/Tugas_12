package com.e.movie_tvshow.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.e.movie_tvshow.R;
import com.e.movie_tvshow.view.MainActivity;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.splash);
        lottieAnimationView.animate().translationY(2100).setDuration(1000).setStartDelay(3000);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        },4500L);
    }
}