package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class DatePicker extends AppCompatActivity {

    Button next;
    EditText name;
    String trip_name, selectedDate;
    FirebaseDatabase database;
    DatabaseReference reference;
    CalendarView calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        next = findViewById(R.id.button_next);
        name = (EditText)findViewById(R.id.trip_name);
        calendar = findViewById(R.id.calendar);
        //name.getText().toString();

        reference = database.getInstance().getReference().child("TripName");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "." + (month+1) + "." + year;
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trip_name = name.getText().toString().trim();
                if(!trip_name.isEmpty()) {
                    Intent i = new Intent(DatePicker.this , SelectAttraction.class);
                    reference.child("trip_name").setValue(trip_name);
                    reference.child("date").setValue(selectedDate);
                    startActivity(i);
                } else {
                    Toast.makeText(DatePicker.this, "Trip name is missing! Fill the box.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}