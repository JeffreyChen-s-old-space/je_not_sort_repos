package com.je_chen.websocket.Main;

import com.je_chen.websocket.Module.ChatServer;

import java.net.*;


public class Main {


    public static void main(String[] argv) {
        try {
            ChatServer chatServer = new ChatServer(5050);
            chatServer.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
