package com.je_chen.udp.socket.audio.test;

import com.je_chen.udp.socket.audio.client.ClientVoice;

import javax.sound.sampled.LineUnavailableException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static boolean calling = false;

    public static void main(String[] args) {
        ClientVoice clientVoice = new ClientVoice();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            System.out.println(message);
            if(message.equals("init")){
                try {
                    clientVoice.initAudio();
                } catch (LineUnavailableException | UnknownHostException | SocketException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
