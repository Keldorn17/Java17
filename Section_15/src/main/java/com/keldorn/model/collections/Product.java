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

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }
}
