package com.example.wecareforpets_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class pet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        Button yourButton = findViewById(R.id.btn_contact);
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage();
            }
        });
    }

    private void showToastMessage() {
        Toast.makeText(getApplicationContext(), "Request is coming. The seller will contact you soon...", Toast.LENGTH_SHORT).show();
    }

    }
