package com.je.datainnovationrace.layout.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.recyleview.RecycleViewAdapter;
import com.je.datainnovationrace.utils.websocket.Websocket;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;

public class HazardWeatherActivity extends AppCompatActivity {

    private Websocket websocket;
    private RecyclerView recyclerView;
    private final ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_weather);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        URI uri = URI.create("ws://jechen.ddns.net:30003");
        websocket = new Websocket(uri, this) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                data.clear();
                Log.e("WebSocket", "onOpen");
                this.send("selectAll hazard_weather");
            }

            @Override
            public void onMessage(String message) {
                if (message.equals("data done")) {
                    setView();
                    this.send("exit");
                    return;
                }
                String[] splitArray = message.replace("(", "")
                        .replace(")", "")
                        .replace("'", "")
                        .split(",");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(splitArray[0]);
                String temp = splitArray[1];
                if (temp.isEmpty() || temp.trim().equals(""))
                    stringBuilder.append("  ").append(getResources().getString(R.string.no_hazard));
                else
                    stringBuilder.append(temp);
                data.add(stringBuilder.toString());
            }
        };
        if (!websocket.isOpen()) {
            websocket.connect();
        }
    }

    private void setView() {
        ContextCompat.getMainExecutor(HazardWeatherActivity.this).execute(() -> {
            RecyclerView.Adapter adapter = new RecycleViewAdapter(data, R.layout.size72dp_recycleview_layout);
            recyclerView.setAdapter(adapter);
        });
    }

}