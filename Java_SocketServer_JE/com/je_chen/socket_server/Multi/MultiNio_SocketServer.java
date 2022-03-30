package com.je_chen.socket_server.Multi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiNio_SocketServer implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private InetSocketAddress inetSocketAddress;
    private SelectionKey selectionKey;
    private boolean Running = true;

    public MultiNio_SocketServer(String host,int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        inetSocketAddress = new InetSocketAddress(host,port);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        selectionKey = serverSocketChannel.register(selector,ops,null);
    }

    public void setRunning(boolean running) {
        this.Running = running;
    }

    @Override
    public void run() {
        while (Running){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()){
                    SelectionKey keys = selectionKeyIterator.next();
                    if(keys.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }else if(keys.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) keys.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
                        socketChannel.read(byteBuffer);
                        String message = new String(byteBuffer.array()).trim();
                        System.out.println(message);
                        if(message.equals("end")){
                            socketChannel.close();
                        }
                    }
                    selectionKeyIterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
