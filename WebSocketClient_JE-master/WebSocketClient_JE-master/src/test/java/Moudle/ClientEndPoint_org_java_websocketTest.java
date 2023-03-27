package Moudle;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientEndPoint_org_java_websocketTest {

    @Test
    void testConnect(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                JavaWebSocketClient Client = null;
                try {
                    Client = new JavaWebSocketClient(new URI("ws://localhost:5555"));
                    Client.connect();
                    Client.send("Hello");
                    Thread.sleep(5000);
                    Client.close();
                } catch (URISyntaxException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }
}