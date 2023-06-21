package com.example.smdproject.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User
{
    String email;
    String name;
    String password;
    ArrayList<MyRecipie> my_recipies=new ArrayList<MyRecipie>();
    ArrayList<MyRecipie> shared_with_me=new ArrayList<MyRecipie>();
    ArrayList<String> Friends=new ArrayList<String>();

    public User() {

    }

    public User(String email, String password, ArrayList<MyRecipie> my_recipies, ArrayList<MyRecipie> shared_with_me) {
        this.email = email;
        this.password = password;
        this.my_recipies = my_recipies;
        this.shared_with_me = shared_with_me;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;

    }

    public ArrayList<String> getFriends() {
        return Friends;
    }

    public void setFriends(ArrayList<String> Friends) {
        this.Friends.addAll(Friends);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
public void print()
{
    System.out.println("The name is "+this.name);
    System.out.println("The email is "+this.email);
    System.out.println("The password is "+this.password);
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<MyRecipie> getMy_recipies() {
        return my_recipies;
    }

    public void setMy_recipies(ArrayList<MyRecipie> my_recipies) {
        this.my_recipies = my_recipies;
    }

    public ArrayList<MyRecipie> getShared_with_me() {
        return shared_with_me;
    }

    public void setShared_with_me(ArrayList<MyRecipie> shared_with_me) {
        this.shared_with_me = shared_with_me;
    }
    public static void addFriend(String f_email)
    {
        String email = CentralData.getEmail();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference(email);
        String id= dbref.child("Friends").push().getKey();
        dbref.child("Friends").child(id).setValue(f_email);
        CentralData.friends.add(f_email);
    }


}
