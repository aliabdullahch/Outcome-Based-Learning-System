package com.example.smdproject.Models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyRecipie {
    public String id;
    public String name;
    public String image;
    public String recipie;
    public String ingredients;

    public MyRecipie(String name, String image, String recipie, String ingredients,String id) {
        this.name = name;
        this.image = image;
        this.recipie = recipie;
        this.ingredients = ingredients;
        this.id=id;
    }

    public MyRecipie() {

    }

    public void print() {
        System.out.println("The name of the recipe is " + this.name);
        System.out.println("The ingre of the recipie is " + this.ingredients);
        System.out.println("The recipie of the recipie is " + this.recipie);
        System.out.println("The id of the recipie is " + this.id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecipie() {
        return recipie;
    }

    public void setRecipie(String recipie) {
        this.recipie = recipie;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void storeinDatabase(MyRecipie obj) {
        String email = CentralData.getEmail();
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference(email);
        dbref.child("MyRecipies").child(id).setValue(obj);
    }
    public void shareRecipie(MyRecipie obj)
    {
ArrayList<String> friends_emails=new ArrayList<String>();
friends_emails.addAll(CentralData.friends);
        DatabaseReference dbref_shared;String id;
        for(int i=0;i<friends_emails.size();i++)
        {
            System.out.println("inside loop");
            System.out.println(friends_emails.get(i));
            dbref_shared=FirebaseDatabase.getInstance().getReference(friends_emails.get(i)).child("Shared");
            id=dbref_shared.push().getKey();
            dbref_shared.child(id).setValue(obj);
        }


    }


}