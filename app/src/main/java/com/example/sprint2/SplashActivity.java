package com.example.sprint2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private ImageView imageView;
    private Animation blink;
    private int intervalsplash = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.news_icon);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        imageView.startAnimation(blink);

        // start activity after some amount of time
        new Handler().postDelayed(() ->
                startActivity(new Intent(getApplicationContext()
                        ,MainActivity.class)), Long.parseLong(String.valueOf(intervalsplash)));
        //new Handler().postDelayed(() ->
        //startActivity(new Intent(getApplicationContext(),
        //MainMenu.class)), Long.parseLong(String.valueOf(intervalsplash)));
    }


    public void onAnimationStart(Animation animation) {
        //TODO:
    }


    public void onAnimationEnd(Animation animation) {
        //TODO:
    }


    public void onAnimationRepeat(Animation animation) {
        //TODO:
    }
}
