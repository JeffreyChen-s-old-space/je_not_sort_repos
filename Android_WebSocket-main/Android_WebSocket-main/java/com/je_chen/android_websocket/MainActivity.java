package com.je_chen.android_websocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Websocket websocket;

    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                URI uri = URI.create("ws://yourhost:5050/websocket/websocket");
                websocket = new Websocket(uri){
                    @Override
                    public void onMessage(String message) {
                        Log.e("WebSocket",message);
                    }
                };
                websocket.connect();
                break;

            case R.id.button2:
                if(websocket!=null && websocket.isOpen())
                    websocket.send("hello");
                break;
        }
    }
}
