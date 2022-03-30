package com.je_chen.udp.socket.audio.server;

import javax.sound.sampled.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerVoice {

    private int serverPort = 5555;
    private String serverAddress = "127.0.0.1";
    private SourceDataLine audioOutput;

    public static AudioFormat getAudioFormat(){
        float sampleRate = 8000.0f;
        int sampleSize = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSize,channel,signed,bigEndian);
    }

    public void initAudio() throws LineUnavailableException, SocketException {
        AudioFormat format = getAudioFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
        if(!AudioSystem.isLineSupported(info)){
            System.out.println("Not support");
            System.exit(0);
        }
        audioOutput = (SourceDataLine) AudioSystem.getLine(info);
        audioOutput.open(format);
        audioOutput.start();
        ReceiveThread receiveThread = new ReceiveThread(new DatagramSocket(serverPort),audioOutput);
        com.je_chen.udp.socket.audio.test.Main.calling = true;
        receiveThread.start();
    }

}
