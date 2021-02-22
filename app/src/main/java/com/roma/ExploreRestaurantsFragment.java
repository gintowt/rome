package com.roma;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExploreRestaurantsFragment extends Fragment {

    Button attractions;
    //Button restaurants;
    DatabaseReference listDbRef;
    ListView myListView;
    List<RestaurantsDatabase> newAttraction;

    public ExploreRestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_restaurants, container, false);
        attractions = v.findViewById(R.id.btn_attraction);
        myListView = v.findViewById(R.id.savedListView);
        newAttraction = new ArrayList<>();
        listDbRef = FirebaseDatabase.getInstance().getReference("RestaurantsExplore");
        listDbRef.orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newAttraction.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {

                    RestaurantsDatabase attractionExplore = attractionDatasnap.getValue(RestaurantsDatabase.class);
                    newAttraction.add(attractionExplore);
                    System.out.println("ATTRACTION EXPLORE: " +attractionExplore.getName() +" " +attractionExplore.getImgUrl());
                }
                RestaurantsAdapter adapterExplore = new RestaurantsAdapter(getActivity(), newAttraction);
                myListView.setAdapter(adapterExplore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_layout, new ExploreFragment()).commit();
            }
        });

        return v;
    }
}