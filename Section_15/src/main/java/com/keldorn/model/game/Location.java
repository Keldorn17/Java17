package main.java.com.keldorn.model.game;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final String name;
    private final String description;
    private final Map<String, String> exits = new HashMap<>();

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addExit(String direction, String destination) {
        exits.put(direction.toUpperCase(), destination);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public String getDestination(String direction) {
        return exits.get(direction.toUpperCase());
    }
}
