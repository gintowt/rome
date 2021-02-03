package com.roma;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<AttractionLocation> attractionList;
    //List<AttractionLocation> attractionList;
    ArrayList<String> names;

    public ListAdapter(Activity mContext, List<AttractionLocation> attractionList) {
        super(mContext, R.layout.list_item, attractionList);
        this.mContext = mContext;
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);
        TextView title = listItemView.findViewById(R.id.attr_title);
        TextView distance = listItemView.findViewById(R.id.attr_distance);
        Button navigate = listItemView.findViewById(R.id.navigate);
        AttractionLocation attractionLocation = attractionList.get(position);
        names = new ArrayList<>();
        //AttractionLocation attractionLocation = attractionList.get(position);
        title.setText(attractionLocation.getAttraction_name());
        //distance.setText(String.valueOf(attractionLocation.getLocation_distance()));
        //title.setText(attractionLocation.getTitle());
        distance.setText(String.valueOf(attractionLocation.getAvg_time()));

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plan = new Intent(getContext(), TestRoute.class);
                mContext.startActivity(plan);
            }
        });
        return listItemView;

    }
}
