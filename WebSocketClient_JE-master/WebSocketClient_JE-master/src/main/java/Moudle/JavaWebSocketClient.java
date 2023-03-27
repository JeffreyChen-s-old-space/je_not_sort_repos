package Moudle;


import java.net.URI;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class JavaWebSocketClient extends WebSocketClient{

    public JavaWebSocketClient(URI ServerUri, Draft draft) {
        super(ServerUri, draft);
    }

    public JavaWebSocketClient(URI ServerUri) {
        super(ServerUri);
    }

    public JavaWebSocketClient(URI ServerUri, Map<String, String> HttpHeaders) {
        super(ServerUri, HttpHeaders);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connect");
    }

    @Override
    public void onMessage(String Message) {
        System.out.println("Received Message : "+ Message);
    }

    @Override
    public void onClose(int code, String Reason, boolean Remote) {
        System.out.println("Connection closed by " + (Remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + Reason);
    }

    @Override
    public void onError(Exception e) {
        System.err.println(e.toString());
    }
}
