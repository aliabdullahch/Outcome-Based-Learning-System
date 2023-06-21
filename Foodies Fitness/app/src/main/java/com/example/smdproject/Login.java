package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smdproject.Models.CentralData;
import com.example.smdproject.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText email_box;
    EditText password_box;
    DatabaseReference dbRef;
    String email;
    String password;
    DatabaseReference dbRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button=findViewById(R.id.login2_id);
        button.setOnClickListener(this);
        findViewsbyid();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.login2_id:
            {
                email=email_box.getText().toString();
                password=password_box.getText().toString();
                dbRef2= FirebaseDatabase.getInstance().getReference(email.substring(0,(email.indexOf('.'))));

DatabaseReference pass_ref=dbRef2.child("password");
if(pass_ref!=null)
{
    final String[] firebase_password = new String[1];
    pass_ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            firebase_password[0] = (snapshot.getValue(String.class));
            if(firebase_password[0]!=null)
            {
                if(firebase_password[0].equals(password))
                {
                    CentralData.setemail(email.substring(0,(email.indexOf('.'))));
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    myIntent.putExtra("email", email.substring(0,(email.indexOf('.'))));
                    startActivity(myIntent);
                }
            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}




            }
        }
    }
    private void findViewsbyid()
    {

        email_box=findViewById(R.id.email2_id);
        password_box=findViewById(R.id.password2_id);
    }
}