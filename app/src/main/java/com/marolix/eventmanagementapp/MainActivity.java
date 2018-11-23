package com.marolix.eventmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.marolix.eventmanagementapp.adapter.PartyListAdapter;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQ_PERMISSION = 890;
    String[] parties = {"Corporate Events", "Marriage Events", "Birthday Events",
            "Live Music and orchestra", "Entertainment Shows", "Personal Events"};
    RelativeLayout birthday, marriage, corporate, livemusic, personal, entertainment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkPermission(MainActivity.this)) {
            Log.e("Permission", "Granted");
        } else {
            requestPermission(MainActivity.this);
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



        /*RecyclerView recyclerView = findViewById(R.id.party_type);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        PartyListAdapter adapter = new PartyListAdapter(this, parties);
        recyclerView.setAdapter(adapter);
        RadioGroup group = findViewById(R.id.radioParty);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if (null != rb) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
//                    Utilities.setPreference(MainActivity.this, Constants.MAIN_PARTY, );
                    Intent data = new Intent(MainActivity.this, SubPartyActivity.class);
                    data.putExtra("SubParty", rb.getText().toString());
                    startActivity(data);
                }
            }
        });*/


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
//                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();

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
                startActivity(new Intent(MainActivity.this, SubPartyActivity.class));
                break;
        }
    }
}
