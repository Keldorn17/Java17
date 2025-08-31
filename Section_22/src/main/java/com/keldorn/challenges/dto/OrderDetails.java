package main.java.com.keldorn.challenges.dto;

import java.util.StringJoiner;

public record OrderDetails(int quantity, String itemDescription) {
    @Override
    public String toString() {
        return new StringJoiner(", ", "{", "}")
                .add("\"itemDescription\":\"" + itemDescription + "\"")
                .add("\"qty\":" + quantity)
                .toString();
    }
}
