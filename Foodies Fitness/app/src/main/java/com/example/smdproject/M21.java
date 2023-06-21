package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class M21 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m21);
        TextView originTextView = findViewById(R.id.origin_text_view);
        ImageView dishImageView = findViewById(R.id.dish_image_view);

        // Get the extras from the Intent
        Intent intent = getIntent();
        String dishName = intent.getStringExtra("dishName");
        String origin = intent.getStringExtra("origin");
        int imageResId = intent.getIntExtra("imageResId", 0);

        // Set the text of the origin TextView and the image of the ImageView
        originTextView.setText("Origin: " + origin);
        dishImageView.setImageResource(imageResId);

    }
}