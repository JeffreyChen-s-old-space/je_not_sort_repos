import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;

public class HttpServerHandlerJE implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String request = null;
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return;
        }
        if ("GET".equals(httpExchange.getRequestMethod())) {
            request = handleGetRequest(httpExchange);
        }
        handleResponse(httpExchange, request);
    }

    private String handleGetRequest(HttpExchange exchange) {
        return exchange.getRequestURI()
                .toString().replace("/","");
    }

    private void handleResponse(HttpExchange httpExchange, String request) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "<html>" +
                "<head>" +
                "</head>" +
                "<body>" +
                "<h1>" +
                "Hello " +
                request +
                "</h1>" +
                "</body>" +
                "</html>";
        httpExchange.sendResponseHeaders(HttpsURLConnection.HTTP_OK, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
