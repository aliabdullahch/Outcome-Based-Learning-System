package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Signup extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        Button button1=findViewById(R.id.login_id);
        Button button2=findViewById(R.id.signup_id);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_id:
            {
                startActivity(new Intent(getApplicationContext(),Login.class));
                break;
            }
            case R.id.signup_id:
            {
                startActivity(new Intent(getApplicationContext(),sign_up.class));
                break;
            }

        }

    }
}