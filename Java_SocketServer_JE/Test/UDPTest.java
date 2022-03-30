package Test;

import com.je_chen.socket_server.Multi.UDPMultiClient_SocketServer;

import java.net.SocketException;

public class UDPTest {

    public static void main(String[] argv){
        try {
            UDPMultiClient_SocketServer udpMultiClient_socketServer = new UDPMultiClient_SocketServer(5555);
            new Thread(udpMultiClient_socketServer).start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
