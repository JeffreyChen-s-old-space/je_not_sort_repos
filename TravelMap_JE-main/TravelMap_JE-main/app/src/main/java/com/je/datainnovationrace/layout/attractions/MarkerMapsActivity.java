package com.je.datainnovationrace.layout.attractions;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.websocket.Websocket;

import org.jetbrains.annotations.NotNull;

import java.net.URI;

public class MarkerMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Websocket websocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        URI uri = URI.create("ws://jechen.ddns.net:30005");
        websocket = new Websocket(uri, this) {
            @Override
            public void onMessage(String message) {
                if (message.equals("data done")) {
                    this.send("exit");
                    return;
                }
                ContextCompat.getMainExecutor(MarkerMapsActivity.this).execute(() -> {
                    String[] splitArray = message.replace("(", "")
                            .replace(")", "")
                            .replace("'", "")
                            .split(",");
                    LatLng latLng = new LatLng(Double.parseDouble(splitArray[4]), Double.parseDouble(splitArray[3]));
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(splitArray[1])
                            .snippet(splitArray[2])
                    );
                });
            }
        };
        if (!websocket.isOpen()) {
            websocket.connect();
        }
    }

    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        mMap = googleMap;
        if (websocket.isOpen()) {
            websocket.send("select");
            websocket.send("exit");
        }
        LatLng taiwan = new LatLng(23.973875, 120.982024);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(taiwan, 8.0f));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (websocket!=null && websocket.isOpen())
                websocket.send("exit");
        }
        return super.onKeyDown(keyCode, event);
    }
}