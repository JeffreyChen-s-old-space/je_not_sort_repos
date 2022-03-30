package Test;

import com.je_chen.socket_client.Client.TCPNioClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NioTest {

    public static void main(String[] argv){
        List<String> messageList = new ArrayList<>();
        messageList.add("I'm");
        messageList.add("JE-Chen");
        messageList.add("Nice to meet you");
        messageList.add("end");
        try {
            TCPNioClient tcpNioClient = new TCPNioClient("localhost",5555);
            tcpNioClient.sendMessage("Hello Server");
            System.out.println();
            tcpNioClient.sendMessage(messageList,1000);
            tcpNioClient.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
