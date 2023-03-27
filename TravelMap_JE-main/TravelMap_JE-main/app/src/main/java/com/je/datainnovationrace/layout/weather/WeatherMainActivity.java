package com.je.datainnovationrace.layout.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;

public class WeatherMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.locationRestaurant) {
            Intent intent = new Intent(WeatherMainActivity.this, LocationWeatherActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.hazardWeather) {
            Intent intent = new Intent(WeatherMainActivity.this, HazardWeatherActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}