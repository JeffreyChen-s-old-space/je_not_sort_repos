package com.je.datainnovationrace.layout.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;

public class HotelMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.locationRestaurant) {
            Intent intent = new Intent(HotelMainActivity.this, SearchLocationHotelActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}