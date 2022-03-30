package com.je_chen.socket_server.Multi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class TCPMultiClient_SocketServer {

    private ServerSocket ServerUseSocket;

    private Map<String,Socket> Users;

    private PrintWriter DataOutPut;

    public TCPMultiClient_SocketServer(){
        //TODO 當有真實IP時要測試
        Users = new HashMap<String, Socket>();
    }

    public void start(int Port) throws IOException {
        ServerUseSocket = new ServerSocket(Port);
        while (true){
            Socket User= ServerUseSocket.accept();
            Users.put(User.getInetAddress().getHostAddress(),User);
            Thread Handle = new Thread(new ClientSocketHandler(User));
            Handle.start();
        }
    }

    public void disconnect() throws IOException {
        ServerUseSocket.close();
        DataOutPut.close();
    }

    public void sendMessage(Socket socket,String Message) throws IOException {
        DataOutPut = new PrintWriter(socket.getOutputStream(), true);
        DataOutPut.println(Message);
    }

    public void broadcast(String message) throws IOException {
        for(String address : Users.keySet()){
            sendMessage(Users.get(address),"Hello");
        }
    }

    private static class ClientSocketHandler implements Runnable{
        private Socket Client_Socket;
        private PrintWriter DataOutput;
        private BufferedReader DataInput;

        public ClientSocketHandler(Socket socket) throws IOException {
            this.Client_Socket = socket;
            DataOutput = new PrintWriter(Client_Socket.getOutputStream(), true);
            DataInput = new BufferedReader(new InputStreamReader(Client_Socket.getInputStream()));

        }

        @Override
        public void run() {
            try {
                String Input;
                while ((Input = DataInput.readLine()) != null) {
                    if (Input.equals("Close")) {
                        DataOutput.println("Close");
                        break;
                    }
                    DataOutput.println("Hello");
                    System.out.println(Input);
                }
                DataOutput.close();
                DataInput.close();
                Client_Socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
