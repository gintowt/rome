package com.roma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetail extends AppCompatActivity {

    TextView name, address, price, stars, cuisine;
    ImageView image;
    DatabaseReference listDbRef;
    List<RestaurantsDatabase> restaurantDetails;
    Button back, navigate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_detail);
        restaurantDetails = new ArrayList<>();
        listDbRef = FirebaseDatabase.getInstance().getReference("RestaurantsExplore");
        int position = getIntent().getIntExtra("position", 0);
        name = findViewById(R.id.name);
        cuisine = findViewById(R.id.description);
        price = findViewById(R.id.time);
        address = findViewById(R.id.price);
        stars = findViewById(R.id.stars);
        image = findViewById(R.id.imageView2);
        back = findViewById(R.id.back);
        navigate = findViewById(R.id.navigate);

        listDbRef.orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantDetails.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {
                        RestaurantsDatabase restaurantsExplore = attractionDatasnap.getValue(RestaurantsDatabase.class);
                        restaurantDetails.add(restaurantsExplore);
                }

                if (position < restaurantDetails.size()-1){
                    RestaurantsDatabase show = restaurantDetails.get(position);
                    name.setText(show.getName());
                    cuisine.setText(show.getCuisines());
                    price.setText(show.getPriceRange());
                    address.setText(show.getAddress());
                    stars.setText(String.valueOf(show.getStars()));
                    Picasso.get().load(show.getImgUrl()).resize(900,900).into(image);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RestaurantDetail.this, MainScreen.class);
                back.putExtra("back", 2);
                startActivity(back);
            }
        });

    }
}