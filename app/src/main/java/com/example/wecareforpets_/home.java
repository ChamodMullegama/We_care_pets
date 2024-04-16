package com.example.wecareforpets_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class home extends AppCompatActivity {
    FirebaseAuth auth;
    Button logoutbutton;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        logoutbutton= findViewById(R.id.btn_logout);
        textView = findViewById(R.id.user_info);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(getApplicationContext(),login.class);
            startActivity(intent);
        }
        else {
            textView.setText(user.getEmail());
        }
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
               // finish();
            }
        });

        Button shopbutton = findViewById(R.id.btn_shop);
        shopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, view.class);
                startActivity(intent);
            }
        });

        Button caregiverbutton = findViewById(R.id.caregivers_btn);
        caregiverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, careGivers.class);
                startActivity(intent);
            }
        });

        Button categoryutton = findViewById(R.id.btn_category);
        categoryutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, category.class);
                startActivity(intent);
            }
        });


    }
}