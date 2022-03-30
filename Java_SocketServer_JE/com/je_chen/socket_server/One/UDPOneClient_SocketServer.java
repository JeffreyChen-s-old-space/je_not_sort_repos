package com.je_chen.socket_server.One;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPOneClient_SocketServer implements Runnable{

    private boolean Running = true;
    private byte[] buffer = new byte[20];
    private int port;
    private DatagramSocket datagramSocket;

    public void setDatagramSocket(int port) throws SocketException {
        datagramSocket = new DatagramSocket(port);
    }

    public void setPort(int port){
        this.port=port;
    }

    @Override
    public void run() {
        while (Running) {
            try {
            //receive data
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            String message = new String(datagramPacket.getData(),0,datagramPacket.getLength());
            System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
