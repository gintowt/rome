package com.roma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OptimalRoute extends AppCompatActivity implements OnMapReadyCallback {

    SelectAttraction selected;
    private static final int ZOOM_LEVEL = 15;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimal_route);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.optimal);
        if(mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //selected = new SelectAttraction();
        //ArrayList<String> total = selected.getList();
        //int number = total.size();

        Intent select_Attraction = getIntent();
        ArrayList<String> total = select_Attraction.getStringArrayListExtra("test");
        int number = total.size();
        ArrayList<LatLng> test = new ArrayList<>();
        //System.out.println(number);

        for (int i = 0; i < number; i++) {

            String name = total.get(i);
            //System.out.println(name);
            LatLng address = getLocationFromAddress(name);
            test.add(address);
            googleMap.addMarker(new MarkerOptions().position(address).title("Marker in " + name));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));
            if (i == 0) {
                CameraPosition camPos = new CameraPosition(address, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
            }


           /* String name = total.get(i);
            System.out.println(name);
            LatLng address = getLocationFromAddress(name);
            googleMap.addMarker(new MarkerOptions().position(address).title("Marker in " + name));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));*/

        }

        for (int i = 0; i < test.size(); i++) {

            if (i < (test.size() - 1)){
                Polyline line = googleMap.addPolyline(new PolylineOptions()
                        .add(test.get(i), test.get(i + 1))
                        .width(8)
                        .color(Color.RED));
            }
        }
     /*   int i = 0;
        do{
            String name = total.get(i);
            System.out.println(name);
            LatLng address = getLocationFromAddress(name);
            googleMap.addMarker(new MarkerOptions().position(address).title("Marker in " + name));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));
            if (i == 0) {
                CameraPosition camPos = new CameraPosition(address, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
            }
            i++;
        }while(i < number);
    */
       // LatLng sydney = new LatLng(-34, 151);
        //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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


}