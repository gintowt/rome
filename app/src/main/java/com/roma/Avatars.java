package com.roma;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class Avatars extends AppCompatActivity {

    ImageView newbie, artist, foodie, veteran;
    Button select_newbie, select_artist, select_foodie, select_veteran;
    DatabaseReference avatarsDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatars);
        newbie = findViewById(R.id.newbie);
        artist = findViewById(R.id.artist);
        foodie = findViewById(R.id.foodie);
        veteran = findViewById(R.id.veteran);

        select_newbie = findViewById(R.id.select_newbie);
        select_artist = findViewById(R.id.select_artist);
        select_foodie = findViewById(R.id.select_foodie);
        select_veteran = findViewById(R.id.select_veteran);

        newbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(Avatars.this, "This type of tourist is a perfect choice for someone who doesn't know much about the place they want to go or is new to traveling.",Toast.LENGTH_LONG).show();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Avatars.this, R.style.MyDialogTheme);
                final View customLayout = getLayoutInflater().inflate(R.layout.saved_dialog, null);
                alertDialogBuilder.setView(customLayout);
                alertDialogBuilder.setTitle("Newbie.");
                alertDialogBuilder.setMessage("This type of tourist is a perfect choice for someone who doesn't know much about the place they want to go or is new to traveling.");
                //alertDialogBuilder.setView(edittext);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

            }
        });

        select_newbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Avatar").child("name").setValue("newbie");
                Intent home = new Intent(Avatars.this, MainScreen.class);
                startActivity(home);
            }
        });

        select_artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Avatar").child("name").setValue("artist");
                Intent home = new Intent(Avatars.this, MainScreen.class);
                startActivity(home);
            }
        });

        select_foodie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Avatar").child("name").setValue("foodie");
                Intent home = new Intent(Avatars.this, MainScreen.class);
                startActivity(home);
            }
        });

        select_veteran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Avatar").child("name").setValue("veteran");
                Intent home = new Intent(Avatars.this, MainScreen.class);
                startActivity(home);
            }
        });
    }
}