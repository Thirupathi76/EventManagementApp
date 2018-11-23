package com.marolix.eventmanagementapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marolix.eventmanagementapp.Constants;
import com.marolix.eventmanagementapp.MainActivity;
import com.marolix.eventmanagementapp.R;
import com.marolix.eventmanagementapp.SubPartyActivity;
import com.marolix.eventmanagementapp.Utilities;

import java.util.ArrayList;
import java.util.List;

public class PartyListAdapter extends RecyclerView.Adapter<PartyListAdapter.RecyclerViewHolders> {

    private List<String> itemList = new ArrayList<>();
    private Context context;
    private String[] parties;
    
    /*public PartyListAdapter(Context context, ArrayList<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }*/

    public PartyListAdapter(MainActivity context, String[] parties) {
        this.context = context;
        this.parties = parties;
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

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_party_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);

        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.partyName.setText(parties[position]);

//        holder.countryPhoto.setUrl(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return parties.length;
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView partyName;
        public Context context;


        public RecyclerViewHolders(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            partyName = itemView.findViewById(R.id.type_party_text);
            
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {

                Utilities.setPreference(context, Constants.MAIN_PARTY, parties[getAdapterPosition()]);
                Intent intent = new Intent(context, SubPartyActivity.class);
                intent.putExtra("PARTY_NAME", parties[getAdapterPosition()]);
                context.startActivity(intent);

//                String affiliateModel = itemList.get(getAdapterPosition());
            }
        }

    }

}
