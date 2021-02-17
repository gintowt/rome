package com.roma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatePicker extends AppCompatActivity {

    Button next;
    EditText name;
    String trip_name;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        next = findViewById(R.id.button_next);
        name = (EditText)findViewById(R.id.trip_name);
        //name.getText().toString();

        reference = database.getInstance().getReference().child("TripName");

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trip_name = name.getText().toString().trim();
                if(!trip_name.isEmpty()) {
                    Intent i = new Intent(DatePicker.this , SelectAttraction.class);
                    reference.setValue(trip_name);
                    startActivity(i);
                } else {
                    Toast.makeText(DatePicker.this, "Trip name is missing! Fill the box.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}