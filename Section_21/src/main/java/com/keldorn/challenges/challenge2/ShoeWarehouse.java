package main.java.com.keldorn.challenges.challenge2;

import java.util.Deque;
import java.util.LinkedList;

public class ShoeWarehouse {
    private static final int CAPACITY = 4;
    private final Deque<Order> orderList = new LinkedList<>();

    public synchronized void receiveOrder(Order order) {
        while (orderList.size() >= CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        orderList.add(order);
        System.out.println("Incoming: " + order + " " + Thread.currentThread().getName());
        notifyAll();
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
