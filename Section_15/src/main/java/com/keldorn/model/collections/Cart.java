package main.java.com.keldorn.model.collections;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cart {

    private final UUID id;
    private final Map<Product, Integer> products = new HashMap<>();
    private final LocalDate date;
    private final CartType cartType;

    public Cart(CartType cartType) {
        this.id = UUID.randomUUID();
        this.date = LocalDate.now();
        this.cartType = cartType;
    }

    public UUID getId() {
        return id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public LocalDate getDate() {
        return date;
    }

    public CartType getCartType() {
        return cartType;
    }

    public void addItem(Product product, int quantity) {
        products.merge(product, quantity, Integer::sum);
    }

    public void addItem(Product product) {
        addItem(product, 1);
    }

    public void removeItem(Product product, int quantity) {
        products.computeIfPresent(product, (k, v) -> {
            int updated = v - quantity;
            return updated > 0 ? updated : null;
        });
    }

    public void removeItem(Product product) {
        products.remove(product);
    }


}
