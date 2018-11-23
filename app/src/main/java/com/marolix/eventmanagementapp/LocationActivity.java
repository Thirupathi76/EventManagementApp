package com.marolix.eventmanagementapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private LocationManager locationManager;
    private LocationRequest mLocationRequest = null;
    private GoogleApiClient googleApiClient;
    GPSTracker gpsTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        gpsTracker = new GPSTracker(LocationActivity.this);
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            emptyText.setText(R.string.gps_off);
            Toast.makeText(this, "GPS off", Toast.LENGTH_SHORT).show();
        }

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(2000);

        googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
        getCurrentLocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        getCurrentLocation();
    }
    private void getCurrentLocation() {
        Log.e("Button Click", "Working");
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("inside", "this");
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

            if (mLastLocation != null) {
                double lat = mLastLocation.getLatitude(),
                        lon = mLastLocation.getLongitude();
                Log.e("Current LatLng >> ",lat + "," + lon);
                Toast.makeText(this, lat + ",\n" + lon, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
