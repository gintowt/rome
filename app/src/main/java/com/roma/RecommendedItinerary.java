package com.roma;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecommendedItinerary extends AppCompatActivity {

    DatabaseReference recommendedDbRef;
   // DatabaseReference tripDetails;
   // DatabaseReference tripDateDb;
    ListView myListView;
    List<RecommendedModel> recommendedAttraction;
   // List<TripDetails> tripDetailsList;
    ImageButton back;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_itinerary);

        String title = getIntent().getStringExtra("title");
        myListView = findViewById(R.id.recommendedListView);
        back = findViewById(R.id.imageButton);
        save = findViewById(R.id.save);
        recommendedAttraction = new ArrayList<>();
       // tripDetailsList = new ArrayList<>();
        //tripDetails = FirebaseDatabase.getInstance().getReference("TripName");
        recommendedDbRef = FirebaseDatabase.getInstance().getReference("RecommendedItinerary").child(title);
      //  tripDateDb = FirebaseDatabase.getInstance().getReference("Date");
        recommendedDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot attractionListDatasnap : snapshot.getChildren()) {
                    //String key = attractionListDatasnap.getKey();
                   // RecommendedModel keyDetails = new RecommendedModel(key);
                    //newAttraction.add(keyDetails);
                    RecommendedModel attractionList = attractionListDatasnap.getValue(RecommendedModel.class);
                    recommendedAttraction.add(attractionList);
                    System.out.println("recommended: " +recommendedAttraction);
                    // System.out.println("newAttraction: " +newAttraction);

                }

                RecommendedAdapter adapter = new RecommendedAdapter(RecommendedItinerary.this, recommendedAttraction);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RecommendedItinerary.this, MainScreen.class);
                startActivity(back);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialog();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RecommendedItinerary.this, R.style.MyDialogTheme);
                final View customLayout = getLayoutInflater().inflate(R.layout.saved_dialog, null);
                alertDialogBuilder.setView(customLayout);
                alertDialogBuilder.setTitle("Save.");
                alertDialogBuilder.setMessage("Do you want to save this trip?");
                //alertDialogBuilder.setView(edittext);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // FirebaseDatabase.getInstance().getReference("SavedTrip").setValue(tripDetailsList);
                        EditText editText = customLayout.findViewById(R.id.trip_name);
                        String trip_name = editText.getText().toString().trim();
                        if (!trip_name.isEmpty()) {
                            //FirebaseDatabase.getInstance().getReference("TripDetails").child("name").setValue(trip_name);

                            //FirebaseDatabase.getInstance().getReference("TripDetail").child("trip_name").setValue(trip_name);
                            FirebaseDatabase.getInstance().getReference("SavedTrip").child(trip_name).setValue(recommendedAttraction).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                       /* tripDateDb.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot tripSnapshot : snapshot.getChildren()) {
                                                    //TripDetails trip_date = tripSnapshot.getValue(TripDetails.class);
                                                    String date_value = tripSnapshot.getValue(String.class);
                                                    TripDetails tDetails = new TripDetails(date_value);
                                                    FirebaseDatabase.getInstance().getReference("SavedTrip").child(trip_name).child("date").setValue(tDetails.getDate());
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        */
                                        Toast.makeText(RecommendedItinerary.this, "Saved.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RecommendedItinerary.this, "Failed to save. Try again!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        }
                        else{
                            Toast.makeText(RecommendedItinerary.this, "Name is empty. Fill the box.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RecommendedItinerary.this, "You clicked on cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


    }


}