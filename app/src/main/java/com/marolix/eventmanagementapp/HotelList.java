package com.marolix.eventmanagementapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.marolix.eventmanagementapp.adapter.CustomAdapter;

public class HotelList extends AppCompatActivity {
    ListView listView;
    private int[] images = {R.drawable.icon11, R.drawable.icon12, R.drawable.icon13, R.drawable.icon14,
            R.drawable.icon15, R.drawable.icon16, R.drawable.icon17, R.drawable.icon20};
    String hotelNames[] = {"Hotel Abinand Grand", "Hotel Avasa", "Hotel Hayath", "Kassani Gr", "Radisiion", "Red Fox", "Trident", "Sandhya Convention"};
    String areas[] = {"3 Halls", "2 Halls", "1 Hall", "4 Halls", "3 Halls", "2 Halls", "1 Hall", "4 Halls"};
    String capacitys[] = {"100-200", "200-300", "300-400", "100-200", "100-200", "200-300", "300-400", "100-200"};
    String locations[] = {"Gachibowli", "Ameerpet", "Gachibowli", "Abids", "Gachibowli", "Kukatpally", "Gachibowli", "Madhapur"};
    String[] ratings = {"4.5", "4.3", "4.3", "4.2", "4.0", "4.3", "4.6", "4.1"};
    private ProgressDialog dialog;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        listView = findViewById(R.id.listview);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hotel or Function Hall");
        }
        adapter = new CustomAdapter(HotelList.this, images, hotelNames, areas, capacitys, locations, ratings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Utilities.setPreference(HotelList.this, Constants.HOTEL_NAME, hotelNames[i]);
                Intent intent = new Intent(HotelList.this, HotelBookingActivity.class);
                startActivity(intent);
            }
        });

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
                PopupMenu popup = new PopupMenu(HotelList.this, vItem);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        dialog = new ProgressDialog(HotelList.this);

                        dialog.setTitle("Please wait");
                        dialog.setMessage("Loading...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
//                        text.setVisibility(View.VISIBLE);
                            }
                        }, 1500);
//                        Toast.makeText(HotelList.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
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
