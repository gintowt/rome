package com.roma;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItineraryAdapter extends ArrayAdapter {

    private Activity mContext;
    List<AttractionLocation> attractionList;
    //List<AttractionLocation> attractionList;

    public ItineraryAdapter(Activity mContext, List<AttractionLocation> attractionList) {
        super(mContext, R.layout.list_item, attractionList);
        this.mContext = mContext;
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            //ListView listItemView = (ListView) inflater.inflate(R.layout.list_item, null, true);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
             } /*else {
               viewHolder = (ViewHolder) convertView.getTag();
               //holder.title.setText(String.valueOf(getItem(position)));
             }
             */
        viewHolder = new ViewHolder();
        AttractionLocation attractionLocation = attractionList.get(position);
        //TextView title = listItemView.findViewById(R.id.attr_title);
        viewHolder.title = convertView.findViewById(R.id.attr_title);
        //TextView avg_time = listItemView.findViewById(R.id.attr_distance);
        viewHolder.avg_time = convertView.findViewById(R.id.attr_distance);
        //Button navigate = listItemView.findViewById(R.id.navigate);
        viewHolder.navigate = convertView.findViewById(R.id.navigate);
        // ImageView image = listItemView.findViewById(R.id.imageView);
        viewHolder.image = convertView.findViewById(R.id.imageView);
        viewHolder.title.setText(attractionLocation.getAttraction_name());
        // title.setText(attractionLocation.getAttraction_name());
        viewHolder.avg_time.setText(String.valueOf(attractionLocation.getAvg_time()));
        // avg_time.setText(String.valueOf(attractionLocation.getAvg_time()));
        Picasso.get().load(attractionLocation.getImgUrl()).into(viewHolder.image);
        // Picasso.get().load(attractionLocation.getImgUrl()).into(image);
        viewHolder.navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Button number " +position +" was clicked!!", Toast.LENGTH_SHORT).show();
                Intent plan = new Intent(mContext, FinalRoute.class);
                System.out.println("POSITION::::" + position);
                plan.putExtra("position", position);
                mContext.startActivity(plan);
            }
        });
        convertView.setTag(viewHolder);

        /*
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);
        TextView title = listItemView.findViewById(R.id.attr_title);
        TextView distance = listItemView.findViewById(R.id.attr_distance);
        Button navigate = listItemView.findViewById(R.id.navigate);
        AttractionLocation attractionLocation = attractionList.get(position);
        names = new ArrayList<>();
        //AttractionLocation attractionLocation = attractionList.get(position);
        title.setText(attractionLocation.getAttraction_name());
        name = String.valueOf(title);
        //distance.setText(String.valueOf(attractionLocation.getLocation_distance()));
        //title.setText(attractionLocation.getTitle());
        distance.setText(String.valueOf(attractionLocation.getAvg_time()));

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plan = new Intent(getContext(), TestRoute.class);
                plan.putExtra("title", name);
                mContext.startActivity(plan);
            }
        });

         */
            return convertView;

        }

    public class ViewHolder {
        TextView title;
        TextView avg_time;
        Button navigate;
        ImageView image;
    }
}
