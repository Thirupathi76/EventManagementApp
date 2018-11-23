package com.marolix.eventmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marolix.eventmanagementapp.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] images;
    String[] hotelNames;
    String[] areas;
    String[] capacitys;
    String[] locations;
    String[] ratings;

    public CustomAdapter(Context context, int[] images, String[] hotelNames, String[] areas, String[] capacitys, String[] locations, String[] ratings) {
        this.context = context;
        this.images = images;
        this.hotelNames = hotelNames;
        this.areas = areas;
        this.capacitys = capacitys;
        this.locations = locations;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.activity_main2, viewGroup, false);
        ImageView image;
        TextView hotelName, location, area, capacity, rating;
        image = view.findViewById(R.id.image1);
        hotelName = view.findViewById(R.id.hotelName);
        location = view.findViewById(R.id.location);
        area = view.findViewById(R.id.areas);
        capacity = view.findViewById(R.id.capacity);
        rating = view.findViewById(R.id.rating);
        image.setImageResource(images[i]);
        hotelName.setText(hotelNames[i]);
        location.setText(locations[i]);
        area.setText(areas[i]);
        capacity.setText(capacitys[i]);
        rating.setText(ratings[i]);


        return view;
    }
}
