package com.je_chen.android_websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.nio.ByteBuffer;

public class Websocket extends WebSocketClient {

    public Websocket(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("WebSocket","onOpen");
    }

    @Override
    public void onMessage(String message) {
        Log.e("WebSocket","onMessage");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("WebSocket","onClose");
    }

    @Override
    public void onError(Exception ex) {
        Log.e("WebSocket","onError");
    }
}
