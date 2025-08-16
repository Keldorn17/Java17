package main.java.com.keldorn.challenges.challenge2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoeWarehouse {
    private static final int CAPACITY = 4;
    private final Deque<Order> orderList = new LinkedList<>();
    private final ExecutorService fulfillmentService;

    public ShoeWarehouse() {
        this.fulfillmentService = Executors.newFixedThreadPool(3);
    }

    public synchronized Order receiveOrder(Order order) {
        while (orderList.size() >= CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return order;
            }
        }
        orderList.add(order);
        System.out.println("Incoming: " + order + " " + Thread.currentThread().getName());
        fulfillmentService.submit(this::fulfillOrder);
        notifyAll();
        return order;
    }

    public void shutDown() {
        fulfillmentService.shutdown();
    }

    public synchronized Order fulfillOrder() {
        while (orderList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        var order = orderList.removeFirst();
        System.out.println("Fulfilled: " + order + " " + Thread.currentThread().getName());
        notifyAll();
        return order;
    }

}
