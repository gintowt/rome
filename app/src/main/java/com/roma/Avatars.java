package com.roma;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

public class Avatars extends AppCompatActivity {

    ImageView newbie, artist, foodie, veteran;
    DatabaseReference avatarsDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatars);
        newbie = findViewById(R.id.newbie);
        artist = findViewById(R.id.artist);
        foodie = findViewById(R.id.foodie);
        veteran = findViewById(R.id.veteran);



    }
}