 package com.roma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

 public class MapActivity extends FragmentActivity implements TaskLoadedCallback {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    ImageButton search;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    private GoogleMap mMap;
    private static final int ZOOM_LEVEL = 15;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;
    EditText search_destination;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        search = (ImageButton) findViewById(R.id.ic_magnify);
        search_destination = (EditText) findViewById(R.id.input_search);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        place1 = new MarkerOptions().position(new LatLng(41.8992813, 12.489064)).title("My location");
        //place2 = new MarkerOptions().position(new LatLng(50.0624368, 18.9345288)).title("Kobiór");
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                Log.d("mylog", String.valueOf(place1));
                place1 = new MarkerOptions().position(new LatLng(41.8992813, 12.489064)).title("My location");
                mMap.addMarker(place1);
                //mMap.addMarker(place2);
                CameraPosition camPos = new CameraPosition(place1.getPosition(), ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
            }
        });
/*
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
            }
        });
     */
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add = search_destination.getText().toString().trim();
                Log.i("check", String.valueOf(add));
                if (! add.isEmpty()) {
                    if (place2 != null){
                        mMap.clear();
                        mMap.addMarker(place1);
                    }
                        place2 = new MarkerOptions().position(getLocationFromAddress(add)).title(add);
                        Log.i("test", String.valueOf(getLocationFromAddress(add)));
                        mMap.addMarker(place2);
                        new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "walking"), "driving");


                } else {
                    Toast.makeText(MapActivity.this, "Please fill the box", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

   /* @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        mMap.addMarker(place1);
        mMap.addMarker(place2);
    }
    */

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

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=AIzaSyABbtU3WbL9QK3aR6Lo2pYQ2VRLHcXp1bc";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        search = findViewById(R.id.input_search);
        //Assign variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //Check permission
        if( ActivityCompat.checkSelfPermission(MapActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            Task<Location> task = client.getLastLocation();
            getCurrentLocation(task);
        } else {
            //when permission denied
            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }


    }

    private void getCurrentLocation(Task<Location> task) {
        //Initialize task location

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            LatLng latLng = new LatLng(50.30582, 18.9742);
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("I am here");

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getCurrentLocation();
            }
        }
    }
*/

}