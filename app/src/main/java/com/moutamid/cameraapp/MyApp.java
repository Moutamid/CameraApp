package com.moutamid.cameraapp;

import android.app.Application;

import com.fxn.stash.Stash;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Stash
        Stash.init(this);

        // You can optionally put default values or other initialization here
    }
}