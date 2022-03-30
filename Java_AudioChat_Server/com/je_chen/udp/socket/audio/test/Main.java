package com.je_chen.udp.socket.audio.test;

import com.je_chen.udp.socket.audio.server.ServerVoice;

import javax.sound.sampled.LineUnavailableException;
import java.net.SocketException;
import java.util.Scanner;

public class Main {

    public static boolean calling = false;

    public static void main(String[] args) {
        ServerVoice serverVoice = new ServerVoice();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            if(message.equals("init")){
                try {
                    serverVoice.initAudio();
                } catch (LineUnavailableException | SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
