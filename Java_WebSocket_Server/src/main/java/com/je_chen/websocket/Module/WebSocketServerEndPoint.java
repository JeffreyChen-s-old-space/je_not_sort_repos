package com.je_chen.websocket.Module;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/websocket")
public class WebSocketServerEndPoint {

    private static Session user;

    @OnOpen
    public void onOpen(Session session) {
        user = session;
        System.out.println("onOpen");
        System.out.println(session.getId() + " connected");
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "Server Error"));
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received : " + message);
    }

    public void sendText(String message) {
        try {
            user.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
