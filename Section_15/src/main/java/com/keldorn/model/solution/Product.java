package main.java.com.keldorn.model.solution;

import main.java.com.keldorn.model.collections.Category;

public record Product(String sku, String name, String mfgr, Category category) {
}
