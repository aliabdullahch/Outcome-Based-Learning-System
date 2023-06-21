package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class M12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m12);

        TextView recipeTextView = findViewById(R.id.recipe_text_view);
        Intent intent = getIntent();
        String recipe = intent.getStringExtra("recipe");
        recipeTextView.setText(recipe);
    }
}