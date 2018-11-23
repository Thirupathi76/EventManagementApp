package com.marolix.eventmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.marolix.eventmanagementapp.adapter.ViewPagerAdapter;
import com.marolix.eventmanagementapp.fragments.FragmentAddress;
import com.marolix.eventmanagementapp.fragments.FragmentMenu;
import com.marolix.eventmanagementapp.fragments.FragmentOverview;
import com.marolix.eventmanagementapp.fragments.FragmentReviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HotelBookingActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    ViewPager viewPagerSlider;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private Button book_hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_book);
        tabLayout = findViewById(R.id.tablayoutid);
        viewPager = findViewById(R.id.viewpagerid);

        book_hotel = findViewById(R.id.book_hotel);
        book_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelBookingActivity.this, BookingStatusActivity.class));
                Toast.makeText(HotelBookingActivity.this, "Hotel Booked", Toast.LENGTH_SHORT).show();
            }
        });
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentOverview(), "Overview");
        adapter.AddFragment(new FragmentAddress(), "Address");
        adapter.AddFragment(new FragmentMenu(), "Q&A");
        adapter.AddFragment(new FragmentReviews(), "Reviews");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        viewPagerSlider = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerSlider.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 4000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPagerSlider.getCurrentItem() == 0) {
                        viewPagerSlider.setCurrentItem(1);
                    } else if (viewPagerSlider.getCurrentItem() == 1) {
                        viewPagerSlider.setCurrentItem(2);
                    } else if (viewPagerSlider.getCurrentItem() == 2) {
                        viewPagerSlider.setCurrentItem(3);
                    } else {
                        viewPagerSlider.setCurrentItem(0);
                    }

                }
            });

        }
    }

    private class ViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> FragmentListTitles = new ArrayList<>();

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return FragmentListTitles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return FragmentListTitles.get(position);
        }

        public void AddFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            FragmentListTitles.add(title);
        }
    }
}
