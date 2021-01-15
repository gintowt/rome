package com.roma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
public class Adapter extends PagerAdapter {

    private Context context;
    private ArrayList<Model> models;

    //constructor
    public Adapter( Context context, ArrayList<Model> models){
        this.context = context;
        this.models = models;
    }

    //return size of items in list
    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){

        //inflate layout item.xlm
        View view = LayoutInflater.from(context).inflate(R.layout.item, container, false);

        //init uid views from item.xml
        ImageView image; // desc;
        TextView title;
        image = view.findViewById(R.id.image);
        //desc = view.findViewById(R.id.desc);
        title = view.findViewById(R.id.title);

        //get data
        Model model = models.get(position);
        String title_model = model.getTitle();
        int image_model = model.getImage();
        //int desc_model = model.getDesc();

        //set data
        image.setImageResource(image_model);
        //desc.setImageResource(desc_model);
        title.setText(title_model);


        //handle card click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, title_model , Toast.LENGTH_SHORT).show();
            }
        });

        //add view to container
        container.addView(view, position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }
}
