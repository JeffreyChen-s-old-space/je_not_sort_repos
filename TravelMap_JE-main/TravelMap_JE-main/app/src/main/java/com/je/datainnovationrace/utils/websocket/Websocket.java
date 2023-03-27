package com.je.datainnovationrace.utils.websocket;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.je.datainnovationrace.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class Websocket extends WebSocketClient {

    private Context connectContext = null;

    public Websocket(URI serverUri) {
        super(serverUri);
    }

    public Websocket(URI serverUri, Context connectContext) {
        super(serverUri);
        this.connectContext = connectContext;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("WebSocket", "onOpen");
    }

    @Override
    public void onMessage(String message) {
        Log.d("WebSocket_onMessage", message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("WebSocket", "onClose " + reason);
    }

    @Override
    public void onError(Exception ex) {
        Log.e("WebSocket", "onError " + ex.getMessage());
        if (this.connectContext != null)
            ContextCompat.getMainExecutor(connectContext).execute(() -> {
                Toast.makeText(connectContext, connectContext.getResources().getString(R.string.websocket_error), Toast.LENGTH_LONG).show();
            });
    }
}
