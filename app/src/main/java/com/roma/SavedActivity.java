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

public class SavedActivity extends AppCompatActivity {

    DatabaseReference attractionDbRef;
    DatabaseReference listDbRef;
    DatabaseReference tripDetails;
    //DatabaseReference tripDateDb;
    ListView myListView;
    List<TripDetails> newAttraction;
    List<TripDetails> tripDetailsList;
    ImageButton back;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        myListView = findViewById(R.id.savedListView);
        back = findViewById(R.id.imageButton);
        delete = findViewById(R.id.delete);
        newAttraction = new ArrayList<>();
        tripDetailsList = new ArrayList<>();
        tripDetails = FirebaseDatabase.getInstance().getReference("TripName");
        //attractionDbRef = FirebaseDatabase.getInstance().getReference("SelectedAttractions");
        attractionDbRef = FirebaseDatabase.getInstance().getReference("SavedTrip");
        //listDbRef = FirebaseDatabase.getInstance().getReference("AttractionsData");
        //tripDateDb = FirebaseDatabase.getInstance().getReference("Date");
        attractionDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newAttraction.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {

                    TripDetails attraction = attractionDatasnap.getValue(TripDetails.class);
                    //System.out.println("name: " +attraction.getAttraction_name() +"avg_time: " +attraction.getAvg_time() );
                    newAttraction.add(attraction);
                    //System.out.println("New array: " +newAttraction);

                }
                SavedAdapter adapter = new SavedAdapter(SavedActivity.this, newAttraction);
                myListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SavedActivity.this, SavedTrips.class);
                startActivity(back);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialog();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SavedActivity.this, R.style.MyDialogTheme);
               //final View customLayout = getLayoutInflater().inflate(R.layout.saved_dialog, null);
                //alertDialogBuilder.setView(customLayout);
                alertDialogBuilder.setTitle("Delete.");
                alertDialogBuilder.setMessage("Do you want to delete this trip?");
                //alertDialogBuilder.setView(edittext);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SavedActivity.this, "you clicked on cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}