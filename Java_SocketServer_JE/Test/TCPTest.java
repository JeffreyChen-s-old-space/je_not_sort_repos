package Test;

import com.je_chen.socket_server.Multi.TCPMultiClient_SocketServer;

import java.io.IOException;

public class TCPTest {

    public static void main(String[] args) {
        TCPMultiClient_SocketServer SocketServer = new TCPMultiClient_SocketServer();
        try {
            SocketServer.start(5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
