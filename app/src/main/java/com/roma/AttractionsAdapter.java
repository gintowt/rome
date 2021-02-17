package com.roma;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AttractionsAdapter extends ArrayAdapter  {

    private Activity mContext;
    List<AttractionsDatabase> attractionList;
    ArrayList<String> names;
    String name;

    public AttractionsAdapter(Activity mContext, List<AttractionsDatabase> attractionList) {
        super(mContext, R.layout.explore_list, attractionList);
        this.mContext = mContext;
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AttractionsAdapter.ViewHolder mainViewholder = null;
        if(convertView == null){
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.explore_list, parent, false);
            AttractionsAdapter.ViewHolder viewHolder = new AttractionsAdapter.ViewHolder();
            AttractionsDatabase attractionExplore = attractionList.get(position);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.image = convertView.findViewById(R.id.imageButton1);
            viewHolder.title.setText(attractionExplore.getName());
            Picasso.get().load(attractionExplore.getImgUrl()).into(viewHolder.image);
            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent details = new Intent(mContext, AttractionDetail.class);
                    System.out.println("POSITION::::"+position);
                    details.putExtra("position", position);
                    mContext.startActivity(details);
                }
            });
            convertView.setTag(viewHolder);
        } else {
            mainViewholder = (AttractionsAdapter.ViewHolder) convertView.getTag();
            mainViewholder.title.setText(String.valueOf(getItem(position)));

        }
        return convertView;

    }

    public class ViewHolder {
        TextView title;
        ImageButton image;
    }
}
