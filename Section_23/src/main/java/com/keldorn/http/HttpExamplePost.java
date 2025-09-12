package main.java.com.keldorn.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExamplePost {
    public static void main(String[] args) {
        try {
            URI uri = URI.create("http://localhost:8080");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Accept", "application/json, text/html");
            connection.setReadTimeout(30_000);

            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String parameters = "first=Joe&last=Smith";
            int length = parameters.getBytes().length;
            connection.setRequestProperty("Content-Length", String.valueOf(length));

            DataOutputStream output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(parameters);
            output.flush();
            output.close();
            int responseCode = connection.getResponseCode();
            System.out.printf("Response code: %d%n", responseCode);
            if (responseCode != HTTP_OK) {
                System.out.println("Error reading web page " + url);
                System.out.printf("Error: %s%n", connection.getResponseMessage());
                return;
            }
            HttpExample.printContents(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
