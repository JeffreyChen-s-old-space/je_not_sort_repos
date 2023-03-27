package com.je.datainnovationrace.layout.test;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.map.MapIntent;
import com.je.datainnovationrace.utils.map.MapStreetViewRequest;
import com.je.datainnovationrace.utils.websocket.Websocket;

import java.net.URI;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private final MapIntent mapIntent = new MapIntent();

    private final MapStreetViewRequest mapStreetViewRequest = new MapStreetViewRequest(this);

    private Websocket websocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.testGoogleMapSearch) {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=台南市政府社會局");
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.testGoogleMapNavigation) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=台北市電腦同業公會&avoid=&mode=");
            mapIntent.startMapIntent(gmmIntentUri, this);
        } else if (id == R.id.testGoogleMapStreetView) {
            mapStreetViewRequest.getPanoIDJson(("https://maps.googleapis.com/maps/api/streetview/metadata?size=600x300&&heading=-45&pitch=42&fov=110&" + Uri.decode("location=台北市電腦同業公會") + "&source=outdoor&key=AIzaSyDEbUHP5qlHxW3RfNUGoUEaCD-NHVqdpiM"));
        } else if (id == R.id.testWebsocket) {
            URI uri = URI.create("ws://jechen.ddns.net:30001");
            websocket = new Websocket(uri);
            if (!websocket.isOpen())
                websocket.connect();
        }
    }

}