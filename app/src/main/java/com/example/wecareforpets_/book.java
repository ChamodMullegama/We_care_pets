package com.example.wecareforpets_;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Spinner caregiverSpinner;
        caregiverSpinner = findViewById(R.id.caregiverpiker);

        String[] caregiversName = {"Ksun Dimantha", "Sithum Sanjana","kavidu athukorala"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, caregiversName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        caregiverSpinner.setAdapter(adapter);

        Button bookButton = findViewById(R.id.bookAnimalButton);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to book?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                showSuccessMessage();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessMessage() {
        Toast.makeText(this, "Caregiver booking successful! caregiver will contact you soon.", Toast.LENGTH_SHORT).show();
    }
}
