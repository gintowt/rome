package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectAttraction extends AppCompatActivity {

    ImageButton back;
    Button next;
    CheckBox colosseum, pantheon, fontanna, forum, basilica, piazza;
    FirebaseDatabase database;
    DatabaseReference reference;
    Selected selected;
    int i = 0;
    ArrayList<String> checkedAttractions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_attraction);

        reference = database.getInstance().getReference().child("Selected");

        selected = new Selected();
        back = findViewById(R.id.back_arrow);
        next = findViewById(R.id.next);
        colosseum = findViewById(R.id.colosseum);
        pantheon = findViewById(R.id.pantheon);
        fontanna = findViewById(R.id.fontanna);
        forum = findViewById(R.id.forum);
        basilica = findViewById(R.id.basilica);
        piazza = findViewById(R.id.piazza);

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
                checkedAttractions.clear();
                if (colosseum.isChecked()) {
                    String text = colosseum.getText().toString();
                    selected.setAttraction1(text);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    Log.i("Attraction1: ", selected.getAttraction1());
                    checkedAttractions.add(text);
                } else { }

                if (piazza.isChecked()) {
                    String text = piazza.getText().toString();
                    selected.setAttraction2(a2);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    checkedAttractions.add(text);
                } else { }

                if (forum.isChecked()) {
                    String text = forum.getText().toString();
                    selected.setAttraction3(a3);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    checkedAttractions.add(text);
                } else { }

                if (fontanna.isChecked()) {
                    String text = fontanna.getText().toString();
                    selected.setAttraction4(a4);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    checkedAttractions.add(text);
                } else { }

                if (basilica.isChecked()) {
                    String text = basilica.getText().toString();
                    selected.setAttraction5(a5);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    checkedAttractions.add(text);
                } else { }

                if (pantheon.isChecked()) {
                    String text = pantheon.getText().toString();
                    selected.setAttraction6(a6);
                    reference.child(String.valueOf(i+1)).setValue(selected);
                    checkedAttractions.add(text);
                } else { }

                for (int i = 0 ; i < checkedAttractions.size(); i++) {
                    String test = checkedAttractions.get(i);
                    System.out.println(test);
                }

                setList(checkedAttractions);
                Intent optimal = new Intent(SelectAttraction.this, OptimalRoute.class);
                optimal.putStringArrayListExtra("test",checkedAttractions );
                startActivity(optimal);
            }
        });
    }



    public void setList(ArrayList<String> checkedAttractions) {
        this.checkedAttractions = checkedAttractions;
    }

    public ArrayList<String> getList()
    {
        return checkedAttractions;
    }
}