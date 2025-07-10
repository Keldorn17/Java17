package main.java.com.keldorn.model.collections;

import java.time.LocalDate;
import java.util.*;

public class Store {

    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final Map<Category, List<InventoryItem>> aisleInventory = new HashMap<>();
    private final Map<UUID, Cart> carts = new HashMap<>();

    public void addInventoryItem(InventoryItem item) {
        inventory.put(item.getProduct().getSku(), item);
        aisleInventory
                .computeIfAbsent(item.getProduct().getCategory(), k -> new ArrayList<>())
                .add(item);
    }

    public InventoryItem getInventoryItemBySku(String sku) {
        return inventory.get(sku);
    }

    public void abandonCart() {
        LocalDate today = LocalDate.now();
//        Iterator<Map.Entry<UUID, Cart>> it = carts.entrySet().iterator();
//
//        while (it.hasNext()) {
//            Map.Entry<UUID, Cart> entry = it.next();
//            if (!entry.getValue().getDate().isEqual(today)) {
//                it.remove();
//            }
//        }
        carts.entrySet().removeIf(entry -> !entry.getValue().getDate().isEqual(today));
    }

    public void addCart(Cart cart) {
        carts.put(cart.getId(), cart);
    }

    public void printInventory() {
        inventory.values().forEach(item -> {
            Product p = item.getProduct();
            System.out.println(p.getName() + " (" + p.getSku() + "): " + item.getQtyTotal() + " units @ $" + item.getSalesPrice());
        });
    }
}
