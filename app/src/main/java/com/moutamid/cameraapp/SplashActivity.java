package com.moutamid.cameraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.fxn.stash.Stash;

public class SplashActivity extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Stash.put("email", "moutamid@gmail.com");
                Stash.put("phone", "0123456789");
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
               finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}