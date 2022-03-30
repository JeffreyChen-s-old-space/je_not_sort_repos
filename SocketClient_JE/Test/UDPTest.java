package Test;

import com.je_chen.socket_client.Client.UDPSocketClient;

import java.io.IOException;

public class UDPTest {

    public static void main(String[] argv){
        try {
            UDPSocketClient udp_socketClient = new UDPSocketClient(5555);
            udp_socketClient.run("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
