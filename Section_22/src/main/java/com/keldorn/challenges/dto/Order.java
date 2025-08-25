package main.java.com.keldorn.challenges.dto;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderDate;
    private final List<OrderDetails> orderDetailsList = new ArrayList<>();

    public Order(String orderDate) {
        this.orderDate = orderDate;
    }

    public Collection<OrderDetails> getOrderDetails() {
        return Collections.unmodifiableCollection(orderDetailsList);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void addOrderDetail(OrderDetails orderDetails) {
        orderDetailsList.add(orderDetails);
    }

    public boolean validateOrderDate() {
        boolean isValid = true;
        try {
            String[] parts = orderDate.split(" ");
            if (parts.length != 2) {
                throw new DateTimeException("Incorrect format");
            }
            String[] dateParts = parts[0].split("-");
            if (dateParts.length != 3) {
                throw new DateTimeException("Incorrect date format");
            }
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            LocalDate.of(year, month, day);

            LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (DateTimeException | NumberFormatException e) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderDate='" + orderDate + '\'' +
               ", orderDetailsList=" + orderDetailsList +
               '}';
    }
}
