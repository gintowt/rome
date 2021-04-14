package com.roma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends ArrayAdapter {

    private Activity mContext;
    List<TripDetails> tripsList;
    //List<AttractionLocation> attractionList;

    public TripAdapter(Activity mContext, List<TripDetails> tripsList) {
        super(mContext, R.layout.saved_list, tripsList);
        this.mContext = mContext;
        this.tripsList = tripsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TripAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.saved_list, parent, false);

        }

        viewHolder = new TripAdapter.ViewHolder();
        TripDetails tripDetails = tripsList.get(position);
        viewHolder.trip_name = convertView.findViewById(R.id.name_trip);
        viewHolder.date = convertView.findViewById(R.id.date);
        viewHolder.view = convertView.findViewById(R.id.view3);
        viewHolder.image = convertView.findViewById(R.id.imageView);
        viewHolder.trip_name.setText(tripDetails.getName());
        String title = tripDetails.getName();
        if(!String.valueOf(tripDetails.getDate()).isEmpty()) {
            viewHolder.date.setText(tripDetails.getDate());
        }
        Picasso.get().load(tripDetails.getImgUrl()).into(viewHolder.image);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Button number " +position +" was clicked!!", Toast.LENGTH_SHORT).show();
                Intent plan = new Intent(mContext, SavedActivity.class);
                System.out.println("POSITION::::"+position);
                plan.putExtra("title", title);
                mContext.startActivity(plan);
            }
        });
        convertView.setTag(viewHolder);

        return convertView;

    }

    public class ViewHolder {
        TextView trip_name;
        TextView date;
        ImageView image;
        View view;
    }
}
