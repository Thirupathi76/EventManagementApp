package com.marolix.eventmanagementapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.marolix.eventmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class SubPartyListAdapter extends RecyclerView.Adapter<SubPartyListAdapter.RecyclerViewHolders> {

    private List<String> itemList = new ArrayList<>();
    private Context context;
    private String[] subParties;


    public SubPartyListAdapter(Context context, String[] subParties) {
        this.context = context;
        this.subParties = subParties;
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

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_party_items, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);

        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.checkBox.setText(subParties[position]);
        if (holder.checkBox.isChecked()){
            if (subParties[position].equals("Others")){
                holder.editText.setVisibility(View.VISIBLE);

            }
        }

//        holder.countryPhoto.setUrl(itemList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return subParties.length;
    }

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

//        public TextView partyName;
        public Context context;
        public CheckBox checkBox;
        public EditText editText;


        public RecyclerViewHolders(View itemView, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            editText = itemView.findViewById(R.id.et_others);
            checkBox = itemView.findViewById(R.id.checkbox);
//            partyName = itemView.findViewById(R.id.type_party_text);
            
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {

                /*Intent intent = new Intent(context, SubPartyActivity.class);
                intent.putExtra("PARTY_NAME", subParties[getAdapterPosition()]);
                context.startActivity(intent);*/

//                String affiliateModel = itemList.get(getAdapterPosition());
            }
        }

    }

}
