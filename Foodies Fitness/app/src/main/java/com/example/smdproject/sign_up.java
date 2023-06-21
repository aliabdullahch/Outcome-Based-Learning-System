package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smdproject.Models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class sign_up extends AppCompatActivity implements View.OnClickListener{
    Button button;
    EditText name_box;
    EditText email_box;
    EditText password_box;
DatabaseReference dbRef;
    String name;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         button=findViewById(R.id.signupin_id);
        button.setOnClickListener(this);
        findViewsbyid();
        //details of the user




    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.signupin_id:
            {
                if(!(email_box.getText().toString().equals(null)) &&!(name_box.getText().toString().equals(null)) &&!(password_box.getText().toString().equals(null)))
                {
                    name=name_box.getText().toString();
                    email=email_box.getText().toString();
                    password=password_box.getText().toString();
                    dbRef= FirebaseDatabase.getInstance().getReference(email.substring(0,(email.indexOf('.'))));
                    String id=dbRef.push().getKey();
                    User u=new User(email,name,password);
                    dbRef.setValue(u);
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }

            }
        }
    }
    private void findViewsbyid()
    {
        name_box=findViewById(R.id.name_id);
        email_box=findViewById(R.id.email_id);
        password_box=findViewById(R.id.password_id);
    }

}