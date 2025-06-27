package main.java.com.keldorn.model.game;

public record Direction(String direction, String destination) {
    public Direction(String data) {
        this(parseData(data)[0], parseData(data)[1]);
    }

    private static String[] parseData(String data) {
        String[] splitData = data.trim().split(":");
        if (splitData.length != 2 || splitData[0].isBlank() || splitData[1].isBlank()) {
            throw new IllegalArgumentException("Invalid direction format: '%s'. Expected format 'DIRECTION:destination'.".formatted(data));
        }
        return new String[] { splitData[0].trim(), splitData[1].trim() };
    }
}
