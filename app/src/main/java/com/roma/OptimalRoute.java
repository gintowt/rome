package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class OptimalRoute extends AppCompatActivity implements OnMapReadyCallback {

    private static final int ZOOM_LEVEL = 12;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimal_route);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.optimal);
        if(mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plan = new Intent(OptimalRoute.this, ItineraryActivity.class);
                startActivity(plan);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent select_Attraction = getIntent();
        ArrayList<String> total = select_Attraction.getStringArrayListExtra("test");
        ArrayList<LatLng> test = new ArrayList<>();
        ArrayList<String> attraction_name = new ArrayList<>();
        ArrayList<Float> distance = new ArrayList<>();
        int number = total.size();
        ArrayList<AttractionLocation> atr_loc = new ArrayList<>();
        float[] results = new float[1];

        //System.out.println(number);

        for (int i = 0; i < number; i++) {

            String name = total.get(i);
            //System.out.println(name);
            attraction_name.add(name);
            LatLng address = getLocationFromAddress(name);
            test.add(address);
            googleMap.addMarker(new MarkerOptions().position(address).title("Marker in " + name));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));

            if (i == 0) {
                CameraPosition camPos = new CameraPosition(address, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
            }

        }

        for (int i = 0; i < test.size(); i++) {

                if (i == 0){
                    results[0] = 0;
                    distance.add(results[0]);
                } else {
                    LatLng location1 = test.get(0);
                    double startLat = location1.latitude;
                    double startLng = location1.longitude;
                    LatLng location2 = test.get(i);
                    double endLat = location2.latitude;
                    double endLng = location2.longitude;
                    Location.distanceBetween(startLat, startLng, endLat, endLng, results);
                    //Collections.addAll(distance, results);
                    distance.add(results[0]);
                    //distance.addAll(Arrays.asList(results));
                    System.out.println(distance);
                }

        }

        for (int i=0; i < distance.size(); i++) {
            AttractionLocation distance_between = new AttractionLocation();
            distance_between.setLocation_distance(distance.get(i));
            distance_between.setAttraction_name(attraction_name.get(i));
            atr_loc.add(distance_between);
            System.out.println("distance: " +distance_between.getLocation_distance() + " name: " + distance_between.getAttraction_name());
            //System.out.println(atr_loc);
            addToFirebase(distance_between.getLocation_distance(), distance_between.getAttraction_name(), distance.size());
        }

    }

    private void addToFirebase(Float distance, String attraction_name, int size) {
        AttractionLocation attraction_location = new AttractionLocation();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference selected_attractions = ref.child("SelectedAttractions");
        attraction_location.setLocation_distance(distance);
        attraction_location.setAttraction_name(attraction_name);
        //String id = ref.push().getKey();
        //ref.child(id).setValue(attraction_location);

        selected_attractions.child("attraction").setValue(attraction_location);

        //selected_attractions.child("distance").setValue(distance);

    }


    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this, Locale.getDefault());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

            return p1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }


    class AttractionLocation {
        Float location_distance;
        String attraction_name;

        AttractionLocation() {}
        AttractionLocation (Float location_distance, String attraction_name) {
            this.location_distance = location_distance;
            this.attraction_name = attraction_name;
        }

        public Float getLocation_distance() {
            return location_distance;
        }

        public void setLocation_distance(Float location_distance) {
            this.location_distance = location_distance;
        }

        public String getAttraction_name() {
            return attraction_name;
        }

        public void setAttraction_name(String attraction_name) {
            this.attraction_name = attraction_name;
        }

    }
}