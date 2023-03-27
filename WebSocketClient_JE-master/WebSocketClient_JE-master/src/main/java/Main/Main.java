package Main;

import Moudle.JavaWebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] argc) {
        JavaWebSocketClient javaWebSocketClient = null;
        URI uri = null;
        try {
            uri = new URI("ws://jechen.ddns.net:5050");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        javaWebSocketClient = new JavaWebSocketClient(uri);
        javaWebSocketClient.connect();
    }
}


