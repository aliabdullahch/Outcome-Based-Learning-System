package com.example.smdproject;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdproject.Adapters.RandomRecipieAdapter;
import com.example.smdproject.Models.CentralData;
import com.example.smdproject.Models.RandomRecipieApiResponse;
import com.example.smdproject.listeners.RandomRecipieResponseListener;
import com.example.smdproject.listeners.RecipieClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RequestManager requestManager;
    RecyclerView recyclView;
    RandomRecipieAdapter randomRecipieAdapter;
    Spinner spinner;
    List<String> tags=new ArrayList<String>();
    SearchView searchView;
    String currently_logged_in_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currently_logged_in_email=getIntent().getStringExtra("email");
        System.out.println("******************************This is email fetched from intent"+currently_logged_in_email);
        System.out.println("******************************This is email fetched from data store"+ CentralData.getEmail());
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading....");
        requestManager=new RequestManager(this);
        spinner=findViewById(R.id.spinner_id);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(this,R.array.tags,R.layout.spinner_text);
arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
spinner.setAdapter(arrayAdapter);
spinner.setOnItemSelectedListener(spinnerSelectedListener);
searchView=findViewById(R.id.search_bar_id);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.explore_button);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.shared_button:
                    {
                        startActivity(new Intent(getApplicationContext(),Shared_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    }
                    case R.id.my_recipes_button:
                    {
                        startActivity(new Intent(getApplicationContext(),My_Recipies_Screen.class));
                        return true;
                    }
                    case R.id.explore_button:
                    {
                        return true;
                    }
                    case R.id.health_button:
                    {
                        startActivity(new Intent(getApplicationContext(),M1.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    case R.id.origin_id:
                    {
                        startActivity(new Intent(getApplicationContext(),M2.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                }
                return false;
            }
        });

searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        tags.clear();
        tags.add(query);
        requestManager.getRandomRecipies(randomRecipieResponseListener,tags);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        tags.clear();
        tags.add(newText);
        requestManager.getRandomRecipies(randomRecipieResponseListener,tags);
        return true;
    }
});



    }
    private final RandomRecipieResponseListener randomRecipieResponseListener= new RandomRecipieResponseListener() {
        @Override
        public void didFetch(RandomRecipieApiResponse response, String message) {
            recyclView=findViewById(R.id.random_recipie_recycler);
            recyclView.setHasFixedSize(true);
            recyclView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            randomRecipieAdapter=new RandomRecipieAdapter(MainActivity.this,response.recipes,recipieClickListener);
recyclView.setAdapter(randomRecipieAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            requestManager.getRandomRecipies(randomRecipieResponseListener,tags);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final RecipieClickListener recipieClickListener=new RecipieClickListener() {
        @Override
        public void OnRecipieClick(int id) {
            Intent   intent=new Intent(MainActivity.this,Recipie_Details_Screen.class);
            intent.putExtra("recipieId",id);
            startActivity(intent);
        }
    };
}