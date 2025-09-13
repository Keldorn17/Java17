package main.java.com.keldorn.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class ASyncHandlerClient {
    public static void main(String[] args) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            URI uri = URI.create("http://localhost:8080");

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .header("User-Agent", "Chrome")
                    .headers("Accept", "application/json", "Accept", "text/html")
                    .timeout(Duration.ofSeconds(30))
                    .build();
            HttpResponse<Stream<String>> response;
            CompletableFuture<HttpResponse<Stream<String>>> responseFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofLines());

            responseFuture.thenApply(ASyncHandlerClient::filterResponse)
                    .thenApply(ASyncHandlerClient::transformResponse)
                    .thenAccept(ASyncHandlerClient::printResponse)
                    .thenRun(() -> {
                        for (int i = 0; i < 10; i++) {
                            System.out.print(i);
                        }
                    })
                    .thenRun(System.out::println);

            System.out.println("Ten Jobs to do besides handling the response.");
            int jobs = 0;
            while (jobs++ < 10) {
                TimeUnit.SECONDS.sleep(1);
                System.out.printf("Job %d ", jobs);
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<String> filterResponse(HttpResponse<Stream<String>> response) {
        System.out.println("Filtering Response...");
        if (response.statusCode() == HTTP_OK) {
            return response.body()
                    .filter(s -> s.contains("<h1>"));
        } else {
            return Stream.empty();
        }
    }

    private static Stream<String> transformResponse(Stream<String> response) {
        System.out.println("Transforming Response");
        return response.map(s -> s.replaceAll("<[^>]*>", "").strip());
    }

    private static void printResponse(Stream<String> response) {
        System.out.println("Printing Response");
        response.forEach(System.out::println);
    }
}
