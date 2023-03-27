package com.je.datainnovationrace.layout.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.layout.attractions.AttractionMainActivity;
import com.je.datainnovationrace.layout.hotel.HotelMainActivity;
import com.je.datainnovationrace.layout.map.MapMainActivity;
import com.je.datainnovationrace.layout.restaurant.RestaurantMainActivity;
import com.je.datainnovationrace.layout.travel.TravelMainActivity;
import com.je.datainnovationrace.layout.weather.WeatherMainActivity;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;
import com.je.datainnovationrace.utils.permission.PermissionsCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainLayout extends AppCompatActivity implements View.OnClickListener {

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__layout);

        PackageManager packageManager = getPackageManager();
        PermissionsCheck permissionsCheck = new PermissionsCheck(packageManager);

        List<String> requestPermission = new ArrayList<>(Arrays.asList(

                // PHONE
                Manifest.permission.CALL_PHONE,

                // LOCATION
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,

                //SMS
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,

                //網路
                Manifest.permission.INTERNET,

                //檔案
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermission.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionsCheck.checkPermission(this, requestPermission.toArray(new String[0]));
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.locationRestaurant) {
            Intent intent = new Intent(MainLayout.this, WeatherMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.main_attraction) {
            Intent intent = new Intent(MainLayout.this, AttractionMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.main_travel) {
            Intent intent = new Intent(MainLayout.this, TravelMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.main_map) {
            Intent intent = new Intent(MainLayout.this, MapMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.main_hotel) {
            Intent intent = new Intent(MainLayout.this, HotelMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }else if (id == R.id.main_restaurant) {
            Intent intent = new Intent(MainLayout.this, RestaurantMainActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}