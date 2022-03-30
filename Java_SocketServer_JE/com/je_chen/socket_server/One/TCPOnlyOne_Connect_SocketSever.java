package com.je_chen.socket_server.One;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPOnlyOne_Connect_SocketSever {
    private ServerSocket ServerUseSocket;
    private Socket ClientUseSocket;
    private PrintWriter DataOutPut;
    private BufferedReader DataInput;

    public void Start(int port) throws IOException {
        ServerUseSocket = new ServerSocket(port);
        ClientUseSocket = ServerUseSocket.accept();
        DataOutPut = new PrintWriter(ClientUseSocket.getOutputStream(), true);
        DataInput = new BufferedReader(new InputStreamReader(ClientUseSocket.getInputStream()));
        String Input;
        while ((Input = DataInput.readLine()) != null) {
            if ("close".equals(Input)) {
                DataOutPut.println("Closed");
                break;
            }
            DataOutPut.println(Input);
            System.out.println(Input);
        }
    }

    public void Disconnect() throws IOException {
        DataInput.close();
        DataOutPut.close();
        ClientUseSocket.close();
        ServerUseSocket.close();
    }

}
