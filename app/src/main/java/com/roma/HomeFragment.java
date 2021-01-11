package com.roma;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    Adapter adapter;
    ArrayList<Model> models;
    ImageButton saved, edit;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = v.findViewById(R.id.viewPager);
        loadCards();

        //set viewpager change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        edit = v.findViewById(R.id.itinerary);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DatePicker.class);
                startActivity(i);

            }
        });

        return v;
    }

    private void loadCards() {
        //init list
        models = new ArrayList<>();
        //add items to list
        models.add(new Model(R.drawable.recommended1, "Angels and Demons"));
        models.add(new Model(R.drawable.recommended2, "Churches"));
        models.add(new Model(R.drawable.recommended3, "Roma"));
        //setup adapter
        adapter = new Adapter(getActivity(), models);
        //set adapter to view pager
        viewPager.setAdapter(adapter);
        //set default padding
        viewPager.setPadding(100,0,100,0);

    }


}