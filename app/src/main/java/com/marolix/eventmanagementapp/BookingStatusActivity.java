package com.marolix.eventmanagementapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_status);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Confirm Booking");
        }
        TextView text_party = findViewById(R.id.text_party);

        String text = "Party : " + Utilities.getPreference(BookingStatusActivity.this, Constants.MAIN_PARTY) + "\n\n" +
                "Event Management : " + Utilities.getPreference(BookingStatusActivity.this, Constants.EVENT_MANAGEMENT) + "\n\n" +
                "Hotel/Function hall : " + Utilities.getPreference(BookingStatusActivity.this, Constants.HOTEL_NAME) + "\n\n";
        ArrayList<String> sub_items = Utilities.getArrayPreference(BookingStatusActivity.this, Constants.SUB_PARTIES);
        String sub_list = "Sub parties : \n\n";
        for (String name : sub_items) {
            sub_list += name + "\n";
        }

        text_party.setText(text + sub_list);

    }
}
