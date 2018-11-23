package com.marolix.eventmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final int REQ_PERMISSION = 76;
    RelativeLayout birthday, marriage, corporate, livemusic, personal, entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        if (checkPermission(HomeActivity.this)) {
            Log.e("Permission", "Granted");
        } else {
            requestPermission(HomeActivity.this);
        }

        birthday = findViewById(R.id.birthday);
        marriage = findViewById(R.id.marriage);
        corporate = findViewById(R.id.corporate);
        livemusic = findViewById(R.id.live_music);
        personal = findViewById(R.id.personal);
        entertainment = findViewById(R.id.entertainment);

        birthday.setOnClickListener(this);
        marriage.setOnClickListener(this);
        corporate.setOnClickListener(this);
        livemusic.setOnClickListener(this);
        personal.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_em) {
            startActivity(new Intent(HomeActivity.this, EventManagerActivity.class));

        } else if (id == R.id.nav_hotel) {
            startActivity(new Intent(HomeActivity.this, HotelList.class));

        } else if (id == R.id.nav_orders) {
            startActivity(new Intent(HomeActivity.this, EventManagerActivity.class));

        } else if (id == R.id.nav_em_reg) {
            startActivity(new Intent(HomeActivity.this, EMRegistration.class));

        } else if (id == R.id.nav_hotel_reg) {
            startActivity(new Intent(HomeActivity.this, LocationActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static boolean checkPermission(Context context) {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(context, CALL_PHONE);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity context) {

        ActivityCompat.requestPermissions(context, new String[]{
                CAMERA,
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE,
                CALL_PHONE,
                ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION
        }, REQ_PERMISSION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQ_PERMISSION:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadStoragePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean WriteExternalPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    /*boolean ReadPhoneStatePermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                     */
                    boolean CoarseLocationPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean FineLocation = grantResults[4] == PackageManager.PERMISSION_GRANTED;

                    if (CameraPermission && ReadStoragePermission && WriteExternalPermission /*&& ReadPhoneStatePermission*/ && CoarseLocationPermission && FineLocation) {

                        Log.e("granted all login ", "permissions");
//                        Toast.makeText(HomeActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(HomeActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.birthday:
            case R.id.marriage:
            case R.id.corporate:
            case R.id.live_music:
            case R.id.personal:
            case R.id.entertainment:
                startActivity(new Intent(HomeActivity.this, SubPartyActivity.class));
                break;
        }
    }
}
