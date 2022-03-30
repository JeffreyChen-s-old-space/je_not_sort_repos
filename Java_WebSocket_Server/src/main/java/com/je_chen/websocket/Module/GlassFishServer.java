package com.je_chen.websocket.Module;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GlassFishServer {

    public static void main(String[] argv){
        try {
            Server server = new Server("localhost", 5050, "", null, WebSocketServerEndPoint.class);
            String Control_String;
            try {
                server.start();
                System.out.println("Input exit to stop the server.");
                do {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    Control_String = reader.readLine();
                } while (!Control_String.equals("exit"));
            } catch (IOException | DeploymentException e) {
                e.printStackTrace();
            } finally {
                server.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
