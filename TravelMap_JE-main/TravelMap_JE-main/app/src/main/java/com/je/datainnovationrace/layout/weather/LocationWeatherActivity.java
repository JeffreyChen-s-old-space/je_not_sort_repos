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

public class LocationWeatherActivity extends AppCompatActivity {

    private Websocket websocket;
    private RecyclerView recyclerView;
    private final ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_weather);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        URI uri = URI.create("ws://jechen.ddns.net:30003");
        websocket = new Websocket(uri, this) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                data.clear();
                Log.e("WebSocket", "onOpen");
                this.send("selectAll one_week_weather");
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
                stringBuilder.append(splitArray[0]).append("\n\n");
                String[] temp = splitArray[1].split(":");
                String[] temp1 = splitArray[2].split(":");
                String[] temp2 = splitArray[3].split(":");
                int count = 0;
                for (int i = 0; i < temp.length; i++) {
                    if (i % 2 == 0) {
                        stringBuilder.append(getResources().getString(R.string.number_of))
                                .append(count + 1)
                                .append(getResources().getString(R.string.day_morning))
                                .append(temp[i]).append("\n\n");
                        count++;
                    } else
                        stringBuilder.append(getResources().getString(R.string.number_of))
                                .append(count).append(getResources().getString(R.string.day_night))
                                .append(temp[i]).append("\n\n");
                    stringBuilder.append(getResources().getString(R.string.high_temp))
                            .append(temp1[i]).append("\n\n");
                    stringBuilder.append(getResources().getString(R.string.low_temp))
                            .append(temp2[i]).append("\n\n");
                }
                data.add(stringBuilder.toString());
            }
        };
        if (!websocket.isOpen()) {
            websocket.connect();
        }
    }

    private void setView() {
        ContextCompat.getMainExecutor(LocationWeatherActivity.this).execute(() -> {
            RecyclerView.Adapter adapter = new RecycleViewAdapter(data, R.layout.warp_content_recycleview_layout);
            recyclerView.setAdapter(adapter);
        });
    }

}