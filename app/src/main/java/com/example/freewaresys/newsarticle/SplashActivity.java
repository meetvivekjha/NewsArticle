package com.example.freewaresys.newsarticle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private static int splash_time_out=3000;
    Animation anim_slide_down;
    TextView splash_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_tv= (TextView) findViewById(R.id.splash_tv);

        anim_slide_down= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        anim_slide_down.reset();
        splash_tv.clearAnimation();
        splash_tv.startAnimation(anim_slide_down);
    }

    @Override
    protected void onStart() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        },splash_time_out);
        super.onStart();
    }
}
