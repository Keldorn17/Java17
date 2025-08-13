package main.java.com.keldorn.challenges.challenge2;

import java.util.stream.Stream;

public class ShoeMain {
    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Thread producerThread = new Thread(() -> {
            var orders = Stream.iterate(1, i -> i + 1)
                    .limit(10)
                    .map(s -> Order.generateOrder())
                    .toList();
            for (var order : orders) {
                shoeWarehouse.receiveOrder(order);
            }
        });

        Thread consumerThread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                var ignore = shoeWarehouse.fulfillOrder();
            }
        });

        Thread consumerThread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                var ignore = shoeWarehouse.fulfillOrder();
            }
        });

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}
