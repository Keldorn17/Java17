package com.keldorn.model.store;

import java.util.ArrayList;

public class Store {
    private final ArrayList<ProductForSale> storeProducts;

    public Store() {
        storeProducts = new ArrayList<>();
    }

    public void addProduct(ProductForSale productForSale) {
        storeProducts.add(productForSale);
    }

    public void listProducts() {
        System.out.println("Store Products Details");
        for (var item : storeProducts) {
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

    public void addItemToOrder(ArrayList<OrderItem> order, int orderIndex, int qty) {
        order.add(new OrderItem(qty, storeProducts.get(orderIndex)));
    }

    public void printOrder(ArrayList<OrderItem> order) {
        double salesTotal = 0;
        for (var item : order) {
            item.productForSale().printPricedItem(item.quantity());
            salesTotal += item.productForSale().getSalesPrice(item.quantity());
        }
        System.out.printf("SalesTotal = %6.2f %n", salesTotal);
    }
}
