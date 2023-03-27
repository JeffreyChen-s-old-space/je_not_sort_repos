package com.je.datainnovationrace.layout.attractions;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.location.LocationGeocode;
import com.je.datainnovationrace.utils.location.LocationSystem;
import com.je.datainnovationrace.utils.websocket.Websocket;

import java.net.URI;

public class AddMarkerActivity extends AppCompatActivity implements View.OnClickListener {

    private Websocket websocket;
    private LocationSystem locationSystem;
    private LocationGeocode locationGeocode;
    private EditText markerTitle, markerSnippet, markerPx, markerPy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationSystem = new LocationSystem(locationManager, "gps", 5000, 5);
        locationGeocode = new LocationGeocode(this);
        markerTitle = findViewById(R.id.markerTitle);
        markerSnippet = findViewById(R.id.markerSnippet);
        markerPx = findViewById(R.id.markerPx);
        markerPy = findViewById(R.id.markerPy);
        URI uri = URI.create("ws://jechen.ddns.net:30005");
        websocket = new Websocket(uri);
        if (!websocket.isOpen())
            websocket.connect();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String getPxText, getPyText;
        if (id == R.id.addMarkerButton) {
            String getTitleText = markerTitle.getText().toString();
            if (getTitleText.isEmpty()) {
                markerTitle.setError(getResources().getString(R.string.map_search_error));
                return;
            }
            String getSnippetText = markerSnippet.getText().toString();
            if (getSnippetText.isEmpty()) {
                markerSnippet.setError(getResources().getString(R.string.map_search_error));
                return;
            }
            try {
                getPxText = markerPx.getText().toString().trim();
                Double.parseDouble(getPxText);
                if (getPxText.isEmpty()) {
                    markerPx.setError(getResources().getString(R.string.map_search_error));
                    return;
                }
            } catch (Exception exception) {
                markerPy.setError(getResources().getString(R.string.value_error));
                return;
            }
            try {
                getPyText = markerPy.getText().toString().trim();
                Double.parseDouble(getPyText);
                if (getPyText.isEmpty()) {
                    markerPy.setError(getResources().getString(R.string.map_search_error));
                    return;
                }
            } catch (Exception exception) {
                markerPy.setError(getResources().getString(R.string.value_error));
                return;
            }
            if (websocket.isOpen()) {
                websocket.send("insert " + getTitleText + " " + getSnippetText + " " + getPxText + " " + getPyText);
                websocket.send("exit");
            }
        } else if (id == R.id.getMarkerDataButton) {
            double px = 120.3207;
            double py = 22.6259;
            try {
                px = locationSystem.getLastKnownLocation("gps").getLongitude();
                py = locationSystem.getLastKnownLocation("gps").getLatitude();
            }catch (Exception e){
                e.printStackTrace();
            }
            markerPx.setText(String.valueOf(px));
            markerPy.setText(String.valueOf(py));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (websocket != null && websocket.isOpen())
                websocket.send("exit");
        }
        return super.onKeyDown(keyCode, event);
    }
}