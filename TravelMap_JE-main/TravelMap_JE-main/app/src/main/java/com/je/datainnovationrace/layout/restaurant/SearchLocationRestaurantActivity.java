package com.je.datainnovationrace.layout.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.je.datainnovationrace.R;
import com.je.datainnovationrace.utils.recyleview.RecycleViewAdapter;
import com.je.datainnovationrace.utils.websocket.Websocket;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.ArrayList;

public class SearchLocationRestaurantActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private Websocket websocket;
    private RecyclerView recyclerView;
    private final ArrayList<String> data = new ArrayList<>();
    private Spinner spinner;
    private String selectString = "臺北市";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location_restaurant);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.taiwan_locations, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.searchLocationRestaurantButton) {
            URI uri = URI.create("ws://jechen.ddns.net:30004");
            websocket = new Websocket(uri, this) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    data.clear();
                    Log.e("WebSocket", "onOpen");
                    this.send("select " + selectString);
                }

                @Override
                public void onMessage(String message) {
                    if (message.equals("data done")) {
                        setView();
                        this.send("exit");
                        return;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    String[] splitArray = message.replace("(", "")
                            .replace(")", "")
                            .replace("'", "")
                            .split(",");
                    stringBuilder.append(getResources().getString(R.string.Name)).append(splitArray[1]).append("\n");
                    String temp;
                    String[] filedName = {
                            getResources().getString(R.string.Description),
                            getResources().getString(R.string.Region),
                            getResources().getString(R.string.Town),
                            getResources().getString(R.string.Add),
                            getResources().getString(R.string.Tel),
                            getResources().getString(R.string.Opentime),
                            getResources().getString(R.string.Website),
                            getResources().getString(R.string.Picture1),
                            getResources().getString(R.string.Picdescribe1),
                            getResources().getString(R.string.Picture2),
                            getResources().getString(R.string.Picdescribe2),
                            getResources().getString(R.string.Picture3),
                            getResources().getString(R.string.Picdescribe3),
                            "",
                            "",
                            getResources().getString(R.string.Parkinginfo)};
                    for (int i = 2; i < splitArray.length; i++) {
                        temp = splitArray[i];
                        if (i == 15 || i == 16)
                            continue;
                        if (!temp.trim().equals("") && !temp.trim().equals("None"))
                            stringBuilder.append(filedName[i - 2]).append(splitArray[i]).append("\n");
                    }
                    data.add(stringBuilder.toString());
                }
            };
            if (!websocket.isOpen()) {
                websocket.connect();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectString = parent.getItemAtPosition(position).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (websocket!=null && websocket.isOpen())
                websocket.send("exit");
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setView() {
        ContextCompat.getMainExecutor(SearchLocationRestaurantActivity.this).execute(() -> {
            RecyclerView.Adapter adapter = new RecycleViewAdapter(data, R.layout.warp_content_recycleview_layout);
            recyclerView.setAdapter(adapter);
        });
    }
}