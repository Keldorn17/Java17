package main.java.com.keldorn.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.keldorn.handlers.JsonBodyHandler;
import main.java.com.keldorn.handlers.ThreadSafeFileHandler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentRequests {
    private static final Path dataFilePath = Path.of("./resources/data.json");
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Map<String, Integer> orderMap =
                Map.of("apples", 500,
                        "oranges", 1000,
                        "bananas", 750,
                        "carrots", 2000,
                        "cantaloupes", 100);

        String urlParams = "product=%s&amount=%d";
        String urlBase = "http://localhost:8080";

        List<URI> sites = new ArrayList<>();
        orderMap.forEach((k, v) -> sites.add(URI.create(urlBase + "?" + urlParams.formatted(k, v))));

        HttpClient client = HttpClient.newHttpClient();
//        sendGets(client, sites);
//        sendPost(client, URI.create(urlBase), urlParams, orderMap);
        sendPostsGetJson(client, urlBase, urlParams, orderMap);
    }

    public static void writeToFile(String content) {
        lock.lock();
        try {
            Files.writeString(dataFilePath, content + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private static void sendGets(HttpClient client, List<URI> uris) {
        var futures = uris.stream()
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();

        futures.forEach(f -> System.out.println(f.join().body()));
    }

    private static void sendPost(HttpClient client, URI uri, String formattable, Map<String, Integer> orderMap) {
        List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>();
        orderMap.forEach((key, value) -> {
            var request = HttpRequest.newBuilder(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(formattable.formatted(key, value)))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            // Normal BodyHandlers.ofFile is not Thread safe
//            futures.add(client.sendAsync(request, HttpResponse.BodyHandlers.ofFile(dataFilePath,
//                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)));
            futures.add(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        });

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0]));
        allFutureRequests.join();

        futures.forEach(f -> {
            try {
                Files.writeString(dataFilePath, f.join().body() + System.lineSeparator(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void sendPost2(HttpClient client, String baseURI, String paramString, Map<String, Integer> orders) {
        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(
                        request, HttpResponse.BodyHandlers.ofString()))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        List<String> lines = new ArrayList<>();

        futures.forEach(f -> lines.add(f.join().body()));
        try {
            Files.write(dataFilePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendPostsGetJson(HttpClient client, String baseURI, String paramString, Map<String, Integer> orders) {
        ObjectMapper objectMapper = new ObjectMapper();
        var handler = JsonBodyHandler.create(objectMapper);
        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(request, handler))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();

        futures.forEach(f -> {
            JsonNode node = f.join().body().get("order");
            System.out.printf("Order Id:%s Expected Delivery: %s%n", node.get("orderId"), node.get("orderDeliveryDate").asText());
        });
    }

    private static void sendPostsSafeFileWrite(HttpClient client, String baseURI, String paramString, Map<String, Integer> orders) {
        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenAcceptAsync(r -> writeToFile(r.body())))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        List<String> lines = new ArrayList<>();
    }

    private static void sendPostsFileHandler(HttpClient client, String baseURI, String paramString, Map<String, Integer> orders) {
        var handler = new ThreadSafeFileHandler(dataFilePath);
        var futures = orders.entrySet().stream()
                .map(e -> paramString.formatted(e.getKey(), e.getValue()))
                .map(s -> HttpRequest.newBuilder(URI.create(baseURI))
                        .POST(HttpRequest.BodyPublishers.ofString(s)))
                .map(HttpRequest.Builder::build)
                .map(request -> client.sendAsync(request, handler))
                .toList();

        var allFutureRequests = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture<?>[0])
        );
        allFutureRequests.join();
        List<String> lines = new ArrayList<>();
    }
}
