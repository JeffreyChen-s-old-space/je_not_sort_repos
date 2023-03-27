package com.je.datainnovationrace.layout.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.layout.attractions.AddMarkerActivity;
import com.je.datainnovationrace.layout.attractions.MarkerMapsActivity;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;

public class MapMainActivity extends AppCompatActivity implements View.OnClickListener{

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.addMarkerButton){
            Intent intent = new Intent(MapMainActivity.this, MapSearchActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }else if(id == R.id.mapNavigation){
            Intent intent = new Intent(MapMainActivity.this, MapNavigationActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}