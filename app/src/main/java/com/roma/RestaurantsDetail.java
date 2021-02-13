package com.roma;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsDetail extends AppCompatActivity {

    TextView name, address, price, stars, cuisine;
    DatabaseReference listDbRef;
    List<RestaurantsDatabase> restaurantDetails;
    RestaurantsDatabase restaurantsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_detail);
        restaurantDetails = new ArrayList<>();
        listDbRef = FirebaseDatabase.getInstance().getReference("RestaurantsExplore");
        int position = getIntent().getIntExtra("position", 0);
        name = findViewById(R.id.name);
        cuisine = findViewById(R.id.cuisines);
        price = findViewById(R.id.price);
        address = findViewById(R.id.address);
        stars = findViewById(R.id.stars);

        listDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantDetails.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {
                        RestaurantsDatabase restaurantsExplore = attractionDatasnap.getValue(RestaurantsDatabase.class);
                        restaurantDetails.add(restaurantsExplore);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        name.setText(restaurantsDatabase.getName());



    }
}