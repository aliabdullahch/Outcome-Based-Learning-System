package com.example.smdproject.Models;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CentralData extends Application {
    static String currently_logged_inemail;
    static ArrayList<String> friends=new ArrayList<String>();
    static public ArrayList<MyRecipie> shared_recipies=new ArrayList<>();
    static public ArrayList<MyRecipie> my_recipies=new ArrayList<>();

    public static void setemail(String email){
        currently_logged_inemail=email;
    }

    public static String getEmail(){
        return currently_logged_inemail;
    }
    public static  void populate_friends() {
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference(currently_logged_inemail).child("Friends");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    friends.add(String.valueOf(dsp.getValue())); //add result into array list
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
