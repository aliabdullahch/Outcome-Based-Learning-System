package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smdproject.Adapters.IngredientAdapter;
import com.example.smdproject.Models.RecipieDetailResponse;
import com.example.smdproject.listeners.RecipieDetailResponseListener;
import com.squareup.picasso.Picasso;

public class Recipie_Details_Screen extends AppCompatActivity {
    TextView meal_name;
    TextView meal_source;
    ImageView meal_image;
    TextView meal_summary;
    RecyclerView recyclerView;
    IngredientAdapter ingredientAdapter;
    int given_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_details_screen);
        Intent intent = getIntent();
        findViews();
        RequestManager requestManager=new RequestManager(this);
        given_id=intent.getIntExtra("recipieId",0);
        requestManager.getRecipieDetail(recipieDetailResponseListener,given_id);
    }

    private void findViews() {
        meal_name=findViewById(R.id.meal_name);
        meal_source=findViewById(R.id.meal_source);
        meal_image=findViewById(R.id.detail_image);
        meal_summary=findViewById(R.id.meal_summary);
        recyclerView=findViewById(R.id.recipieDetailRecyclerView);

    }
    private final RecipieDetailResponseListener recipieDetailResponseListener=new RecipieDetailResponseListener() {
        @Override
        public void didFetch(RecipieDetailResponse response, String message) {
            meal_name.setText(response.title);
            meal_source.setText(response.sourceName);
            Picasso.get().load(response.image).into(meal_image);
            meal_summary.setText(response.summary);
            ingredientAdapter=new IngredientAdapter(Recipie_Details_Screen.this,response.extendedIngredients);
            recyclerView.setAdapter(ingredientAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        }

        @Override
        public void didError(String message) {
            System.out.println("Data not available");

        }
    };
}