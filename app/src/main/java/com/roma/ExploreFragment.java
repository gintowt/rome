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


public class ExploreFragment extends Fragment {

    Button restaurants;
    DatabaseReference listDbRef;
    ListView myListView;
    List<AttractionsDatabase> newAttraction;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_saved, container, false);
        restaurants = v.findViewById(R.id.btn_restaurant);
        myListView = v.findViewById(R.id.listView);
        newAttraction = new ArrayList<>();
        listDbRef = FirebaseDatabase.getInstance().getReference("AttractionsExplore");
        listDbRef.orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newAttraction.clear();
                for (DataSnapshot attractionDatasnap : snapshot.getChildren()) {

                    AttractionsDatabase attractionExplore = attractionDatasnap.getValue(AttractionsDatabase.class);
                    newAttraction.add(attractionExplore);
                    System.out.println("ATTRACTION EXPLORE: " +attractionExplore.getName() +" " +attractionExplore.getImgUrl());
                }
                AttractionsAdapter adapterExplore = new AttractionsAdapter(getActivity(), newAttraction);
                myListView.setAdapter(adapterExplore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_layout, new ExploreRestaurantsFragment()).commit();
            }
        });


        return v;
    }
}