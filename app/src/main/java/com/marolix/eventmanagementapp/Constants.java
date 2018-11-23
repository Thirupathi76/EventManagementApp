package com.marolix.eventmanagementapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Constants {

    public static final String MYPREFERENCES = "mypreferences";
    private static final int REQ_PERMISSION = 3020;
    public static final String LOGIN_PREFERENCES = "login_preferences";
    public static final String EVENT_MANAGEMENT = "EVENT_MANAGEMENT";
    public static final String HOTEL_NAME = "HOTEL_NAME";
    public static final String MAIN_PARTY = "MAIN_PARTY";
    public static final String SUB_PARTIES = "SUB_PARTIES";


    public static boolean checkPermission(Context context) {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        /*int FourthPermissionResult = ContextCompat.checkSelfPermission(context, READ_PHONE_STATE);*/
        int FifthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                /*FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&*/
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity context) {

        ActivityCompat.requestPermissions(context, new String[]{
                        CAMERA,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                        /*READ_PHONE_STATE,*/
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION
                }, REQ_PERMISSION);

    }


}