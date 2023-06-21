package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smdproject.Models.CentralData;
import com.example.smdproject.Models.MyRecipie;
import com.squareup.picasso.Picasso;

public class Detials_Activity extends AppCompatActivity {
MyRecipie myRecipie;
TextView name;
ImageView img;
TextView recipie;
TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detials);
        Intent mIntent = getIntent();
        int index_Value = mIntent.getIntExtra("index", 0);
        String from=mIntent.getStringExtra("from");
        if(from.equals("my"))
        {
            myRecipie= CentralData.my_recipies.get(index_Value);
        }else
        {
            myRecipie= CentralData.shared_recipies.get(index_Value);
        }

        name=findViewById(R.id.rn);
        img=findViewById(R.id.ri);
        recipie=findViewById(R.id.rm);
        ingredients=findViewById(R.id.rin);
        name.setText(myRecipie.name);
        Picasso.get().load(myRecipie.image).into(img);
        recipie.setText(myRecipie.recipie);
        ingredients.setText(myRecipie.ingredients);
    }
}