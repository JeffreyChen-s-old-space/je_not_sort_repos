package Test;

import com.je_chen.socket_client.Client.TCPSocketClient;

public class TCPTest {

    public static void main(String[] args){

        for(int i=0;i<=10;i++){
            new Thread(new TCPSocketClient("Socket " + i,"127.0.0.1",5555)).start();
        }
    }
}
