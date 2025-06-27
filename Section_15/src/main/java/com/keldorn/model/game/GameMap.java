package main.java.com.keldorn.model.game;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class GameMap {
    private final Map<String, Location> locations = new HashMap<>();

    GameMap() {
        initialize();
    }

    private void initialize() {
        for (var line : getLocationsString()) {
            addLocation(line);
        }
    }

    private static List<String> getLocationsString() {
        List<String> baseLocations = new ArrayList<>();
//        Path path = Path.of(ClassLoader.getSystemResource("AdventureGameLocationData.csv").toURI()); // Maven/Gradle needed
        Path path = Path.of("src/main/resources/AdventureGameLocationData.csv");

        try (Scanner scanner = new Scanner(path.toFile())) {
            while (scanner.hasNextLine()) {
                baseLocations.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not load game locations from " + path.toAbsolutePath(), e);
        }
        return baseLocations;
    }

    public void addLocation(String name, String description, Direction... direction) {
        Location location = new Location(name.trim(), description.trim());
        for (var dirDest : direction) {
            location.addExit(dirDest.direction().trim(), dirDest.destination().trim());
        }
        locations.put(name, location);
    }

    private void addLocation(String locationString) {
        String[] parts = locationString.trim().split(",");
        Direction[] directions = new Direction[parts.length - 2];
        for (int i = 2; i < parts.length; i++) {
            directions[i - 2] = new Direction(parts[i]);
        }
        addLocation(parts[0], parts[1], directions);
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    public boolean hasLocation(String name) {
        return locations.containsKey(name);
    }
}
