package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItineraryActivity extends AppCompatActivity {

    DatabaseReference attractionDbRef;
    DatabaseReference listDbRef;
    DatabaseReference tripDetails;
    DatabaseReference tripDateDb;
    ListView myListView;
    List<AttractionLocation> newAttraction;
    List<TripDetails> tripDetailsList;
    ImageButton back;
    Button next, save;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        myListView = findViewById(R.id.savedListView);
        back = findViewById(R.id.imageButton);
        next = findViewById(R.id.button2);
        save = findViewById(R.id.save);
        newAttraction = new ArrayList<>();
        tripDetailsList = new ArrayList<>();
        tripDetails = FirebaseDatabase.getInstance().getReference("TripName");
        attractionDbRef = FirebaseDatabase.getInstance().getReference("SelectedAttractions");
        listDbRef = FirebaseDatabase.getInstance().getReference("AttractionsData");
        tripDateDb = FirebaseDatabase.getInstance().getReference("Date");
        attractionDbRef.orderByChild("location_distance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newAttraction.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {

                    AttractionLocation attraction = attractionDatasnap.getValue(AttractionLocation.class);
                    //System.out.println("name: " +attraction.getAttraction_name() +"avg_time: " +attraction.getAvg_time() );
                    //newAttraction.add(attraction);
                    //System.out.println("New array: " +newAttraction);

                    listDbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot attractionListDatasnap : snapshot.getChildren()) {
                                AttractionLocation attractionList = attractionListDatasnap.getValue(AttractionLocation.class);
                                if(attractionList.getAttraction_name().equals(attraction.getAttraction_name())){
                                    System.out.println("Attraction: " +attraction.getAttraction_name() +"  " +String.valueOf(attraction.getLocation_distance())
                                            +"   AttractionList: " +attractionList.getAttraction_name() +" " +String.valueOf(attractionList.getAvg_time()));
                                    image = attractionList.getImgUrl();
                                    newAttraction.add(attractionList);
                                    System.out.println("KURWAAA: " +newAttraction);
                                   // System.out.println("newAttraction: " +newAttraction);
                                }
                            }

                            ItineraryAdapter adapter = new ItineraryAdapter(ItineraryActivity.this, newAttraction);
                            myListView.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}

                    });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

            });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ItineraryActivity.this, OptimalRoute.class);
                startActivity(back);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ItineraryActivity.this, MainScreen.class);
                startActivity(next);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialog();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ItineraryActivity.this, R.style.MyDialogTheme);
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
                            FirebaseDatabase.getInstance().getReference("SavedTrip").child(trip_name).setValue(newAttraction).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        tripDateDb.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot tripSnapshot : snapshot.getChildren()) {
                                                    //TripDetails trip_date = tripSnapshot.getValue(TripDetails.class);
                                                    String date_value = tripSnapshot.getValue(String.class);
                                                    TripDetails tDetails = new TripDetails(date_value);
                                                    FirebaseDatabase.getInstance().getReference("SavedTrip").child(trip_name).child("date").setValue(tDetails.getDate());
                                                    FirebaseDatabase.getInstance().getReference("SavedTrip").child(trip_name).child("imgUrl").setValue(image);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        Toast.makeText(ItineraryActivity.this, "Saved.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ItineraryActivity.this, "Failed to save. Try again!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        }
                        else{
                            Toast.makeText(ItineraryActivity.this, "Name is empty. Fill the box.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ItineraryActivity.this, "you clicked on cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


    }

/*
    public void openDialog() {
        SaveDialog saveDialog = new SaveDialog();
        saveDialog.show(getSupportFragmentManager(), "save dialog");
    }

 */
/*
public class ListModel {

    String title;
    int avg_time;

    public ListModel(String title, int avg_time) {
        this.title = title;
        this.avg_time = avg_time;
    }

    public ListModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(int avg_time) {
        this.avg_time = avg_time;
    }
}

 */
}

