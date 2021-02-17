package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AttractionDetail extends AppCompatActivity {

    TextView name, description, price, avg_time;
    ImageView image;
    DatabaseReference listDbRef;
    List<AttractionsDatabase> attractionsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);
        attractionsDetails = new ArrayList<>();
        listDbRef = FirebaseDatabase.getInstance().getReference("AttractionsExplore");
        int position = getIntent().getIntExtra("position", 0);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        avg_time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        image = findViewById(R.id.imageView2);

        listDbRef.orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                attractionsDetails.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {
                    AttractionsDatabase attractionsExplore = attractionDatasnap.getValue(AttractionsDatabase.class);
                    attractionsDetails.add(attractionsExplore);
                }

                if (position < attractionsDetails.size()-1){
                    AttractionsDatabase show = attractionsDetails.get(position);
                    name.setText(show.getName());
                    description.setText(show.getDescription());
                    price.setText(show.getPrice());
                    avg_time.setText(String.valueOf(show.getAvg_time()));
                    Picasso.get().load(show.getImgUrl()).into(image);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}