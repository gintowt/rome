package com.roma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;

public class ItineraryActivity extends AppCompatActivity {

    DatabaseReference attractionDbRef;
    DatabaseReference listDbRef;
    ListView myListView;
    List<AttractionLocation> newAttraction;
    ArrayList<String> attractionString;
    //ArrayList<String> mAttraction;
    //List<ListModel> listArray;
    //ListModel listModel = new ListModel();
    Integer avg_time = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        myListView = findViewById(R.id.listView);
        newAttraction = new ArrayList<>();
        attractionString = new ArrayList<>();
        //listArray = new ArrayList<>();
        attractionDbRef = FirebaseDatabase.getInstance().getReference("SelectedAttractions");
        listDbRef = FirebaseDatabase.getInstance().getReference("AttractionsData");
        //attractionDbRef.orderByChild("location_distance");
        attractionDbRef.orderByChild("location_distance").addValueEventListener(new ValueEventListener() {
        //listDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newAttraction.clear();
                //listArray.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {

                    AttractionLocation attraction = attractionDatasnap.getValue(AttractionLocation.class);
                    //System.out.println("name: " +attraction.getAttraction_name() +"avg_time: " +attraction.getAvg_time() );
                    //newAttraction.add(attraction);
                    //System.out.println("New array: " +newAttraction);

                    listDbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot attractionListDatasnap : snapshot.getChildren()) {
                                AttractionLocation attractionList = attractionListDatasnap.getValue(AttractionLocation.class);
                                if(attractionList.getAttraction_name().equals(attraction.getAttraction_name())){
                                    System.out.println("Attraction: " +attraction.getAttraction_name() +"  " +String.valueOf(attraction.getLocation_distance())
                                            +"   AttractionList: " +attractionList.getAttraction_name() +" " +String.valueOf(attractionList.getAvg_time()));
                                    newAttraction.add(attractionList);
                                    System.out.println("KURWAAA: " +newAttraction);
                                   // System.out.println("newAttraction: " +newAttraction);
                                }

                                /*
                                if (String.valueOf(attractionList.getAttraction_name()).equals(String.valueOf(attraction.getAttraction_name()))){
                                    newAttraction.add(attractionList);
                                    System.out.println("Test array: " +newAttraction);
                                }

                                 */

                            }

                            ListAdapter adapter = new ListAdapter(ItineraryActivity.this, newAttraction);
                            myListView.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}

                    });



                    /*
                    if (String.valueOf(attraction.getAttraction_name()).equals("Colosseum")) {
                        avg_time = 60;
                        listModel.setTitle(attraction.getAttraction_name());
                        listModel.setAvg_time(avg_time);
                        listArray.add(listModel);
                    } else {
                        if (String.valueOf(attraction.getAttraction_name()).equals("Pantheon")) {
                            avg_time = 60;
                            listModel.setTitle(attraction.getAttraction_name());
                            listModel.setAvg_time(avg_time);
                            listArray.add(listModel);
                        } else {
                        }


                        //newAttraction.add(attraction);
                        System.out.println("newAttraction: " + listArray);

                     */


                    //ListAdapter adapter = new ListAdapter(ItineraryActivity.this, newAttraction);
                    //myListView.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

            });





    }

/*
public class ListModel {

    String title;
    int avg_time;

    public ListModel(String title, int avg_time) {
        this.title = title;
        this.avg_time = avg_time;
    }

    public ListModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvg_time() {
        return avg_time;
    }

    public void setAvg_time(int avg_time) {
        this.avg_time = avg_time;
    }
}

 */
}

