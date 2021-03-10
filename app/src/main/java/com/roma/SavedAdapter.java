package com.roma;

import android.app.Activity;
import android.content.Intent;
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

import java.util.List;

public class SavedAdapter extends ArrayAdapter {

    private Activity mContext;
    List<SavedModel> savedList;

    public SavedAdapter(Activity mContext, List<SavedModel> savedList) {
        super(mContext, R.layout.list_item, savedList);
        this.mContext = mContext;
        this.savedList = savedList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SavedHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        viewHolder = new SavedHolder();
        SavedModel attractionLocation = savedList.get(position);
        viewHolder.title = convertView.findViewById(R.id.attr_title);
        viewHolder.avg_time = convertView.findViewById(R.id.attr_distance);
        viewHolder.navigate = convertView.findViewById(R.id.navigate);
        viewHolder.image = convertView.findViewById(R.id.imageView);
        viewHolder.title.setText(attractionLocation.getAttraction_name());
        viewHolder.avg_time.setText(String.valueOf(attractionLocation.getAvg_time()));
        Picasso.get().load(attractionLocation.getImgUrl()).into(viewHolder.image);
        viewHolder.navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Button number " +position +" was clicked!!", Toast.LENGTH_SHORT).show();
                Intent plan = new Intent(mContext, SavedRoute.class);
                System.out.println("POSITION::::" + position);
                plan.putExtra("position", position);
                mContext.startActivity(plan);
            }
        });
        convertView.setTag(viewHolder);
        return convertView;
    }

    public class SavedHolder {
        TextView title;
        TextView avg_time;
        Button navigate;
        ImageView image;
    }
}
