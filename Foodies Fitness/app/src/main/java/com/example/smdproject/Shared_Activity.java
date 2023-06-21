package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.smdproject.Adapters.SharedRecipieAdapter;
import com.example.smdproject.Models.CentralData;
import com.example.smdproject.Models.MyRecipie;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Shared_Activity extends AppCompatActivity {
    ArrayList<MyRecipie> shared_recipe=new ArrayList<MyRecipie>();
    RecyclerView recyclerView;
    SharedRecipieAdapter sharedRecipieAdapter;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.shared_button);
        recyclerView=findViewById(R.id.shared_recycle);
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference(CentralData.getEmail());
        DatabaseReference shared_recipe_ref=dbref.child("Shared");
        adView=findViewById(R.id.ad1_id);
        MobileAds.initialize(this);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        shared_recipe_ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                MyRecipie mr=(snapshot.getValue(MyRecipie.class));
                mr.print();
                shared_recipe.add(mr);
                CentralData.shared_recipies.add(mr);
                sharedRecipieAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sharedRecipieAdapter=new SharedRecipieAdapter(Shared_Activity.this,shared_recipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sharedRecipieAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.explore_button:
                    {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    }
                    case R.id.my_recipes_button:
                    {
                        startActivity(new Intent(getApplicationContext(),My_Recipies_Screen.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    case R.id.shared_button:
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
    }
}