package main.java.com.keldorn.model.collections;

public class InventoryItem {

    private final Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReordered;
    private final int qtyLow;
    private final int salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyLow, int salesPrice) {
        this.product = product;
        placeInventoryOrder(qtyTotal);
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }

    public void reserveItem(int quantity) {
        quantityMoreThenTotal(quantity);
        quantityNegative(quantity);
        qtyReserved += quantity;
    }

    public void releaseItem(int quantity) {
        quantityMoreThenReserved(quantity);
        quantityNegative(quantity);
        qtyReserved -= quantity;
    }

    public void sellItem(int quantity) {
        quantityMoreThenReserved(quantity);
        quantityNegative(quantity);
        qtyTotal -= quantity;
        if (qtyTotal < qtyLow) {
            placeInventoryOrder(qtyLow - qtyTotal);
        }
    }

    protected void placeInventoryOrder(int quantity) {
        qtyTotal += quantity;
        if (qtyTotal < qtyLow) {
            throw new IllegalArgumentException("Insufficient order amount, please order at least: " + (qtyLow - quantity));
        }
    }

    private void quantityMoreThenTotal(int qty) {
        if (qty > (qtyTotal - qtyReserved)) {
            throw new IllegalArgumentException("Quantity cannot exceed the total number of product available.");
        }
    }

    private void quantityMoreThenReserved(int qty) {
        if (qty > qtyReserved) {
            throw new IllegalArgumentException("Quantity cannot exceed the total number of product reserved.");
        }
    }

    private void quantityNegative(int qty) {
        if (qty < 1) {
            throw new IllegalArgumentException("Quantity cannot be less then 1. (quantity = " + qty + ")");
        }
    }
}
