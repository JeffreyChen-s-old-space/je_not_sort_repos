package com.je.datainnovationrace.layout.attractions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.intent.StartActivityIntent;

public class AttractionMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final StartActivityIntent startActivityIntent = new StartActivityIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_main);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.mapWatchMarker) {
            Intent intent = new Intent(AttractionMainActivity.this, MarkerMapsActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        } else if (id == R.id.mapAddMarker) {
            Intent intent = new Intent(AttractionMainActivity.this, AddMarkerActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }else if (id == R.id.searchLocationAttractionsButton){
            Intent intent = new Intent(AttractionMainActivity.this, SearchLocationAttractionsActivity.class);
            startActivityIntent.startActivityIntent(intent, this);
        }
    }
}