package com.marolix.eventmanagementapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.marolix.eventmanagementapp.adapter.EventManagerAdapter;

public class EventManagerActivity extends AppCompatActivity {

    String[] emNames = {"Happy Events", "SPARK Events", "Roshan Events", "Gampas Events",
            "Unique Decorations", "SP Studios", "Seven steps Events", "Darsh Entertainments"};
    String[] address = {"Gachibowli, Hyderabad", "Madhapur, Hyderabad", "Kukatpally", "Maredpally",
            "LB Nagar", "Shamshabad", "Abids Hyderabad", "Mehedipattnam"};
    String[] ratings = {"4.5", "4.3", "4.3", "4.2", "4.0", "4.3", "4.6", "4.1"};
    String[] phone = {"9550907676", "9550907676", "9550907676", "9550907676", "9550907676", "9550907676", "9550907676", "9550907676"};

    private ProgressDialog dialog;
    private EventManagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_managers);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Event Management");
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_event_mangers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventManagerAdapter(this, emNames, address, ratings, phone);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filters_menu, menu);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter:
                View vItem = findViewById(R.id.filter);
                PopupMenu popup = new PopupMenu(EventManagerActivity.this, vItem);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        dialog = new ProgressDialog(EventManagerActivity.this);

                        dialog.setTitle("Please wait");
                        dialog.setMessage("Loading ...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
//                        text.setVisibility(View.VISIBLE);
                            }
                        }, 1500);
//                        Toast.makeText(EventManagerActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu


        return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
