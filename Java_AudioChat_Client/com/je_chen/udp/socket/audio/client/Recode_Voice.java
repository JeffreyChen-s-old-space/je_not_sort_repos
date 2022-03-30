package com.je_chen.udp.socket.audio.client;

import com.je_chen.udp.socket.audio.test.Main;

import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Recode_Voice extends Thread{

    private TargetDataLine audioInput;
    private DatagramSocket dataOut;
    private byte[] buffer = new byte[512];
    private InetAddress serverIP;
    private int port;

    public Recode_Voice(TargetDataLine targetDataLine,DatagramSocket datagramSocket,InetAddress inetAddress,int serverport){
        audioInput = targetDataLine;
        dataOut = datagramSocket;
        serverIP = inetAddress;
        port = serverport;
    }

    @Override
    public void run() {
        super.run();
        int dataLength = 1;
        while (Main.calling){
            audioInput.read(buffer,0,buffer.length);
            DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length,serverIP,port);
            try {
                dataOut.send(datagramPacket);
                System.out.println("send #" + dataLength++);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        audioInput.close();
        audioInput.drain();
        System.out.println("Thread Stop");
    }
}
