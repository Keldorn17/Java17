package main.java.com.keldorn.challenges.challenge2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public record Order(UUID orderId, ShoeType shoeType, int quantity) {
    private static final List<ShoeType> shoeTypes = new ArrayList<>(List.of(ShoeType.values()));
    private static final Random random = new Random();

    public static Order generateOrder() {
//        ShoeType type = shoeTypes.get(random.nextInt(shoeTypes.size()));
        ShoeType type = shoeTypes.stream()
                .skip(random.nextInt(0, shoeTypes.size()))
                .findFirst()
                .orElseThrow();
        return new Order(UUID.randomUUID(), type, random.nextInt(1, 10));
    }
}
