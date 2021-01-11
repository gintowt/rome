package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectAttraction extends AppCompatActivity {

    ImageButton back;
    Button next;
    CheckBox coloseum, pantheon, fontanna, forum, basilica, piazza;
    FirebaseDatabase database;
    DatabaseReference reference;
    Selected selected;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_attraction);

        reference = database.getInstance().getReference().child("Selected");

        selected = new Selected();
        back = findViewById(R.id.back_arrow);
        next = findViewById(R.id.next);
        coloseum = findViewById(R.id.coloseum);
        pantheon = findViewById(R.id.pantheon);
        fontanna = findViewById(R.id.fontanna);
        forum = findViewById(R.id.forum);
        basilica = findViewById(R.id.basilica);
        piazza = findViewById(R.id.piazza);

        String a1 = "Coloseum";
        String a2 = "Piazza Navona";
        String a3 = "Forum Romanum";
        String a4 = "Fontanna di Trevi";
        String a5 = "St Peter's Basilica";
        String a6 = "Pantheon";

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SelectAttraction.this, DatePicker.class);
                startActivity(back);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coloseum.isChecked()) {
                    selected.setAttraction1(a1);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                if (piazza.isChecked()) {
                    selected.setAttraction2(a2);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                if (forum.isChecked()) {
                    selected.setAttraction3(a3);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                if (fontanna.isChecked()) {
                    selected.setAttraction4(a4);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                if (basilica.isChecked()) {
                    selected.setAttraction5(a5);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                if (pantheon.isChecked()) {
                    selected.setAttraction6(a6);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                } else { }

                Intent optimal = new Intent(SelectAttraction.this, OptimalRoute.class);
                startActivity(optimal);
            }
        });
    }
}