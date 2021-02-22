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

public class RestaurantsAdapter extends ArrayAdapter {
    private Activity mContext;
    List<RestaurantsDatabase> restaurantsList;
    ArrayList<String> names;
    String name;

    public RestaurantsAdapter(Activity mContext, List<RestaurantsDatabase> restaurantsList) {
        super(mContext, R.layout.explore_list, restaurantsList);
        this.mContext = mContext;
        this.restaurantsList = restaurantsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RestaurantsAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.explore_list, parent, false);

        } /*else {
            viewHolder = (RestaurantsAdapter.ViewHolder) convertView.getTag();
            //mainViewholder.title.setText(String.valueOf(getItem(position)));

        }*/

        viewHolder = new RestaurantsAdapter.ViewHolder();
        RestaurantsDatabase restaurantsExplore = restaurantsList.get(position);
        viewHolder.title = convertView.findViewById(R.id.title);
        viewHolder.image = convertView.findViewById(R.id.imageButton1);
        viewHolder.title.setText(restaurantsExplore.getName());
        Picasso.get().load(restaurantsExplore.getImgUrl()).resize(900,900).into(viewHolder.image);
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(mContext, RestaurantDetail.class);
                System.out.println("POSITION::::"+position);
                details.putExtra("position", position);
                mContext.startActivity(details);
            }
        });
        convertView.setTag(viewHolder);

        return convertView;

    }

    public class ViewHolder {
        TextView title;
        ImageButton image;
    }
}
