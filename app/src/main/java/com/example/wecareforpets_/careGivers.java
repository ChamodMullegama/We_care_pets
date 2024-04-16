package com.example.wecareforpets_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class careGivers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<caregiversModel> caregiversList;
    private CaregiverAdapter adapter;
    private CaregiverAdapter.OnBookClickListener onBookClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_givers);

        recyclerView = findViewById(R.id.careGivers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        caregiversList = new ArrayList<>();
        adapter = new CaregiverAdapter(caregiversList,onBookClickListener);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("caregivers");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                caregiversList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    caregiversModel caregiver = snapshot.getValue(caregiversModel.class);
                    caregiversList.add(caregiver);
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        Button bookNowButton = findViewById(R.id.btn_book);
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(careGivers.this, book.class);
                startActivity(intent);
            }
        });

    }
}
