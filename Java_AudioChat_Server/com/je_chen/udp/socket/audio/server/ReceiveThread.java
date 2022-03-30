package com.je_chen.udp.socket.audio.server;

import javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveThread  extends Thread{

    private DatagramSocket dataInputSocket;
    private SourceDataLine audioOutput;
    private byte[] buffer = new byte[512];

    public ReceiveThread(DatagramSocket datagramSocket,SourceDataLine sourceDataLine){
        this.dataInputSocket=datagramSocket;
        this.audioOutput=sourceDataLine;
    }

    @Override
    public void run(){
        super.run();
        int dataLength = 1;
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        while (com.je_chen.udp.socket.audio.test.Main.calling){
            try {
                dataInputSocket.receive(datagramPacket);
                buffer = datagramPacket.getData();
                audioOutput.write(buffer,0,buffer.length);
                System.out.println("# " + dataLength++);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        audioOutput.close();
        audioOutput.drain();
        System.out.println("Thread Stop");
    }
}
