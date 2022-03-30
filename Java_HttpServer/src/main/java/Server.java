import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    public static void main(String[] argv){
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost",8080),0);
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            httpServer.createContext("/", new HttpServerHandlerJE());
            httpServer.setExecutor(threadPoolExecutor);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
