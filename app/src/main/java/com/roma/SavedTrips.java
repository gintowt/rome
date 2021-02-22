package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SavedTrips extends AppCompatActivity {

    ListView myListView;
    FloatingActionButton back;
    DatabaseReference savedDb;
    List<TripDetails> tripList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_trips);
        myListView = findViewById(R.id.savedListView);
        back = findViewById(R.id.floatingActionButton2);
        tripList = new ArrayList<>();
        savedDb = FirebaseDatabase.getInstance().getReference("date");

        savedDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tripList.clear();
                for (DataSnapshot tripDetail : snapshot.getChildren()) {
                    //TripDetails tDetails = tripDetail.getValue(TripDetails.class);
                    //String key = tripDetail.getKey();
                    String date_value = tripDetail.getValue(String.class);
                    TripDetails tDetails = new TripDetails(date_value);
                    //System.out.println("TRIP LIST: " + tDetails.getDate());
                    tripList.add(tDetails);
                    System.out.println("TRIP LIST: " +tripList );

                }
                TripAdapter adapter = new TripAdapter(SavedTrips.this, tripList);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SavedTrips.this, MainScreen.class);
                startActivity(back);
            }
        });
    }
}