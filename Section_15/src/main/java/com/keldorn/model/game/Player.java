package main.java.com.keldorn.model.game;

import java.util.Scanner;

public class Player {
    private final GameMap map = new GameMap();
    private Location currentLocation = map.getLocation("road");

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            var exits = currentLocation.getExits();
            System.out.printf("*** You're standing %s ***%n".formatted(currentLocation.getDescription()));
            System.out.println("\tFrom here, you can see:");
            exits.forEach((direction, destination) ->
                    System.out.printf("\tA %s to the %s (%s)%n", destination, directionWord(direction), direction));
            System.out.print("Select Your Compass Direction (Q to quit) >> ");
            String direction = String.valueOf(scanner.nextLine().toUpperCase().charAt(0));
            if (direction.equalsIgnoreCase("Q")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println(move(direction));
        }
        scanner.close();
    }

    private String directionWord(String dir) {
        return switch (dir.toUpperCase()) {
            case "W" -> "West";
            case "N" -> "North";
            case "S" -> "South";
            case "E" -> "East";
            default -> "Unknown direction";
        };
    }

    private String move(String direction) {
        String destinationName = currentLocation.getDestination(direction);
        if (destinationName == null) {
            return "You can't go that way";
        }

        Location newLocation = map.getLocation(destinationName);
        if (newLocation == null) {
            return "That path leads to nowhere.";
        }
        currentLocation = newLocation;
        return "You moved " + direction + " to " + currentLocation.getName() + ": " + currentLocation.getDescription();
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
