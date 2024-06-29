package com.moutamid.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PreviewActivity extends AppCompatActivity {

    private ImageView imageView;
    private Uri imageUri;
    private String mobile, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageView = findViewById(R.id.imageView);
        Button btnAccept = findViewById(R.id.btnAccept);
        Button btnReject = findViewById(R.id.btnReject);

        imageUri = Uri.parse(getIntent().getStringExtra("image_uri"));
        imageView.setImageURI(imageUri);

        mobile = getIntent().getStringExtra("mobile");
        email = getIntent().getStringExtra("email");

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
                startActivity(new Intent(PreviewActivity.this, MainActivity.class));
                finishAffinity();
            }
        });
    }

    private void sendEmail() {
        Stash.put("email", email);
        Stash.put("phone", mobile);
        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        NetworkTask networkTask = new NetworkTask(PreviewActivity.this);
        networkTask.execute(imageUri.getPath());

    }

    private void deletePhoto() {
        File file = new File(imageUri.getPath());
        if (file.exists()) {
            file.delete();
        }
    }
}
