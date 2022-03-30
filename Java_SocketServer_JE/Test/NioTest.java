package Test;

import com.je_chen.socket_server.Multi.MultiNio_SocketServer;

import java.io.IOException;

public class NioTest {

    public static void main(String[] argv){
        try {
            MultiNio_SocketServer multiNio_socketServer = new MultiNio_SocketServer("localhost",5555);
            new Thread(multiNio_socketServer).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
