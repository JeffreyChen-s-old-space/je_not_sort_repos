package com.je.datainnovationrace.layout.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;

public class RestaurantMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_main);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.locationRestaurant) {
            Intent intent = new Intent(RestaurantMainActivity.this, SearchLocationRestaurantActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}