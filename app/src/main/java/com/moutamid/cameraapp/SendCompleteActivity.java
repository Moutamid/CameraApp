package com.moutamid.cameraapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class SendCompleteActivity extends AppCompatActivity {
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complete);
        email = findViewById(R.id.email);
        email.setText("Email attachment is successfully send on this email: " + Stash.getString("email"));

    }

    public void home(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}