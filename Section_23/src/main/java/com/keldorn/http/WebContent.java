package main.java.com.keldorn.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class WebContent {
    public static void main(String[] args) {
        try {
//            URL url = new URL("http://example.com");
            URI uri = URI.create("https://jsonplaceholder.typicode.com/todos?id=5");
            URL url = uri.toURL();
//            printContents(url.openStream());
            URLConnection urlConnection = url.openConnection();
            System.out.println(urlConnection.getContentType());
            urlConnection.getHeaderFields()
                    .entrySet()
                    .forEach(System.out::println);

            System.out.println(urlConnection.getHeaderField("Cache-Control"));
            urlConnection.connect();
            printContents(urlConnection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printContents(InputStream is) {
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
