package com.marolix.eventmanagementapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marolix.eventmanagementapp.Constants;
import com.marolix.eventmanagementapp.EMActivity;
import com.marolix.eventmanagementapp.R;
import com.marolix.eventmanagementapp.SubPartyActivity;
import com.marolix.eventmanagementapp.Utilities;

import java.util.ArrayList;
import java.util.List;

public class EventManagerAdapter extends RecyclerView.Adapter<EventManagerAdapter.RecyclerViewHolders> {

    private List<String> itemList = new ArrayList<>();
    private Context context;
    private String[] emNames;
    private String[] address;
    private String[] ratings;
    private String[] phones;

    private int[] images = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
            R.drawable.icon5, R.drawable.icon8, R.drawable.icon9, R.drawable.icon10};
    /*public PartyListAdapter(Context context, ArrayList<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }*/

    public EventManagerAdapter(Context context, String[] emNames, String[] address, String[] ratings, String[] phones) {

        this.context = context;
        this.emNames = emNames;
        this.address = address;
        this.ratings = ratings;
        this.phones = phones;
    }

    public List<String> getData() {
        return this.itemList;
    }

    public void setData(ArrayList<String> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event_manager, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);

        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        holder.eventName.setText(emNames[position]);
        holder.address.setText(address[position]);
        holder.ratings.setText(ratings[position]);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String call = phones[position];
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + call));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    }
                    context.startActivity(callIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        holder.imageView.setImageResource(images[position]);

//        holder.countryPhoto.setUrl(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return emNames.length;
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView eventName, address, ratings;
        ImageView call, imageView;
        public Context context;


        public RecyclerViewHolders(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            eventName = itemView.findViewById(R.id.eventName);
            address = itemView.findViewById(R.id.eventAddress);
            ratings = itemView.findViewById(R.id.eventRate);
            call = itemView.findViewById(R.id.call);
            imageView = itemView.findViewById(R.id.image_em);

        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {

                Intent intent = new Intent(context, EMActivity.class);
                Utilities.setPreference(context, Constants.EVENT_MANAGEMENT, emNames[getAdapterPosition()]);
                context.startActivity(intent);

//                String affiliateModel = itemList.get(getAdapterPosition());
            }
        }

    }

}
