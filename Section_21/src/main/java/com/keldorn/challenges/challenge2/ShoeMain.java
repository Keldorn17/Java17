package main.java.com.keldorn.challenges.challenge2;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ShoeMain {
    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Callable<Order> orderingTask = () -> shoeWarehouse.receiveOrder(Order.generateOrder());
        List<Callable<Order>> tasks = Collections.nCopies(15, orderingTask);
        try (var orderingService = Executors.newCachedThreadPool()) {
            orderingService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        shoeWarehouse.shutDown();
    }

    public static void mySolutionMain(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        int generateAmount = 15;
        var orders = generateOrders(generateAmount);
        try (var receive = Executors.newSingleThreadExecutor()) {
            for (Order order : orders) {
                receive.submit(() -> shoeWarehouse.receiveOrder(order));
            }
        }
    }

    private static List<Order> generateOrders(int amount) {
//        return IntStream.rangeClosed(1, amount)
//                .mapToObj(i -> Order.generateOrder())
//                .toList();
        return Stream.generate(Order::generateOrder)
                .limit(amount)
                .toList();
    }
}
