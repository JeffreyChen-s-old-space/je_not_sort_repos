package com.je_chen.udp.socket.audio.client;

import com.je_chen.udp.socket.audio.test.Main;

import javax.sound.sampled.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientVoice {

    private int serverPort = 5555;
    private String serverAddress = "127.0.0.1";
    private TargetDataLine audioInput;

    public static  AudioFormat getAudioFormat(){
        float sampleRate = 44100.0f;
        int sampleSize = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSize,channel,signed,bigEndian);
    }

    public void initAudio() throws LineUnavailableException, UnknownHostException, SocketException {
        AudioFormat format = getAudioFormat();
        DataLine.Info info= new DataLine.Info(TargetDataLine.class,format);
        if(!AudioSystem.isLineSupported(info)){
            System.out.println("Not support");
            System.exit(0);
        }
        audioInput = (TargetDataLine) AudioSystem.getLine(info);
        audioInput.open(format);
        audioInput.start();
        InetAddress inetAddress= InetAddress.getByName(serverAddress);
        Recode_Voice recode_voice = new Recode_Voice(audioInput,new DatagramSocket(),inetAddress,serverPort);
        Main.calling = true;
        recode_voice.start();
    }
}
