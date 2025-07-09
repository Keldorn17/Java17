package main.java.com.keldorn.model.collections;

public class Product {

    private final String sku;
    private final String name;
    private final String manufacturer;
    private final Category category;

    public Product(String sku, String name, String manufacturer, Category category) {
        this.sku = sku;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}
