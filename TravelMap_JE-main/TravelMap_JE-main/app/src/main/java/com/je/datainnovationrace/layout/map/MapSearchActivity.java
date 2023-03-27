package com.je.datainnovationrace.layout.map;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.map.MapIntent;
import com.je.datainnovationrace.utils.map.MapStreetViewRequest;

public class MapSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private final MapIntent mapIntent = new MapIntent();
    private final MapStreetViewRequest mapStreetViewRequest = new MapStreetViewRequest(this);
    private EditText searchLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_search);
        searchLocationEditText = findViewById(R.id.searchLocationEditText);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.addMarkerButton) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Restaurant");
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.mapSearchHotel) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Hotel");
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.mapSearchAttractions) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Attractions");
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.searchLocation) {
            String getLocationText = searchLocationEditText.getText().toString();
            if (getLocationText.isEmpty()) {
                searchLocationEditText.setError(getResources().getString(R.string.map_search_error));
                return;
            }
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + getLocationText);
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.mapSearchStreetView) {
            String getLocationText = searchLocationEditText.getText().toString();
            if (getLocationText.isEmpty()) {
                searchLocationEditText.setError(getResources().getString(R.string.map_search_error));
                return;
            }
            mapStreetViewRequest.getPanoIDJson(
                    ("https://maps.googleapis.com/maps/api/streetview/metadata?size=600x300&&heading=-45&pitch=42&fov=110&" +
                            Uri.decode("location=" + getLocationText)
                            + "&source=outdoor&key=AIzaSyBZvXt8dn7C4J15DiFdy496iI-jI4uUmEY")
            );
        }
    }
}