package main.java.com.keldorn.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private static long visitorCounter = 0;

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/", exchange -> {
                String requestMethod = exchange.getRequestMethod();
                System.out.println("Request Method: " + requestMethod);

                String data = new String(exchange.getRequestBody().readAllBytes());
                System.out.println("Body data: " + data);
                if (requestMethod.equals("POST")) {
                    visitorCounter++;
                }
                var bytes = getHtml();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });

            server.start();
            System.out.println("Server is listening on port 8080...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getHtml() {
        String response = """
                <html>
                    <body>
                        <h1>Hello World from My Http Server</h1>
                        <p>Number of Visitors who signed up = %d</p>
                        <form method = "post">
                            <input type = "submit" value = "Submit">
                        </form>
                    </body>
                </html>""".formatted(visitorCounter);
        return response.getBytes();
    }
}
