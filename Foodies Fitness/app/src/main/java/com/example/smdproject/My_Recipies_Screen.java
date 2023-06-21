package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smdproject.Adapters.MyRecipiesAdapter;
import com.example.smdproject.Models.CentralData;
import com.example.smdproject.Models.MyRecipie;
import com.example.smdproject.Models.User;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.UUID;

public class My_Recipies_Screen extends AppCompatActivity  {
    FloatingActionButton fab;
    Dialog dialog;
    Dialog dialog2;
    EditText name;
    EditText recipie;
    EditText Ingredients;
    Button Upload;
    Button done;
    Button done2;
    Button addFriend;
    EditText friend_email_box;
    String oname;String oimage;String orecipie;String oingredients;
    ArrayList<MyRecipie> myRecipies=new ArrayList<MyRecipie>();
    RecyclerView recyclerView;
    MyRecipiesAdapter myRecipiesAdapter;
    DatabaseReference dbRef_my_recipies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipies_screen);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.my_recipes_button);
        fab = findViewById(R.id.fab);
        dialog=new Dialog(My_Recipies_Screen.this);
        dialog2=new Dialog(My_Recipies_Screen.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog2.setContentView(R.layout.dialogbox2);
        name=dialog.findViewById(R.id.new_recipie_name);
        recipie=dialog.findViewById(R.id.new_recipie_steps);
        Ingredients=dialog.findViewById(R.id.new_recipie_ingre);
        Upload=dialog.findViewById(R.id.upload_id);
        addFriend=findViewById(R.id.add_friend_button);
        done=dialog.findViewById(R.id.done_id);
        done2=dialog2.findViewById(R.id.done2_id);
        friend_email_box=dialog2.findViewById(R.id.friend_email_box);
recyclerView=findViewById(R.id.my_recpies_recycler);
CentralData.populate_friends();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.show();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(My_Recipies_Screen.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oname=name.getText().toString();
                orecipie=recipie.getText().toString();
                oingredients=Ingredients.getText().toString();
                UUID uuid= UUID.randomUUID();

                MyRecipie mr= new MyRecipie(oname,oimage,orecipie,oingredients,uuid.toString());
                mr.print();
                mr.storeinDatabase(mr);
                dialog.dismiss();
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
            }
        });
        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friend_email=friend_email_box.getText().toString();
                User.addFriend(friend_email.substring(0,friend_email.indexOf('.')));
dialog2.dismiss();
            }
        });


        dbRef_my_recipies= FirebaseDatabase.getInstance().getReference(CentralData.getEmail()).child("MyRecipies");
        dbRef_my_recipies.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot!=null)
                {
                    MyRecipie my_rec=snapshot.getValue(MyRecipie.class);
                    myRecipies.add(my_rec);
                    CentralData.my_recipies.add(my_rec);
                    myRecipiesAdapter.notifyDataSetChanged();

                }

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

         myRecipiesAdapter=new MyRecipiesAdapter(My_Recipies_Screen.this,myRecipies);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(myRecipiesAdapter);
















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

                        return true;
                    }
                    case R.id.explore_button:
                    {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }case R.id.health_button:
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=data.getData();
        oimage=uri.toString();
        System.out.println("The uri is "+oimage);
    }
}