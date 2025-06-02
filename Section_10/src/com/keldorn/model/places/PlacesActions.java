package com.keldorn.model.places;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class PlacesActions {
    private final LinkedList<Place> places = new LinkedList<>();

    public PlacesActions() {
        initializeLinkedList();
    }

    private void addPlace(Place place) {
        if (places.contains(place)) {
            System.out.println("Found duplicate : " + place);
            return;
        }

        for (var element : places) {
            if (element.town().equalsIgnoreCase(place.town())) {
                System.out.println("Found duplicate : " + place);
                return;
            }
        }

        int index = 0;
        for (var element : places) {
            if (place.distance() < element.distance()) {
                places.add(index, place);
                return;
            }
            index++;
        }

        places.add(place);
    }

    private void initializeLinkedList() {
        addPlace(new Place("Adelaide", 1374));
        addPlace(new Place("Alice Springs", 2771));
        addPlace(new Place("Brisbane", 917));
        addPlace(new Place("Darwin", 3972));
        addPlace(new Place("Melbourne", 877));
        addPlace(new Place("Perth", 3923));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String actions = """
                Available actions (Select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";
        String userInput;

        var listIterator = places.listIterator();
        boolean forwardMove = false;
        Set<String> forward = Set.of("f", "forward");
        Set<String> backward = Set.of("b", "backward");
        Set<String> list = Set.of("l", "list", "list places");
        Set<String> menu = Set.of("m", "menu");
        Set<String> quit = Set.of("q", "quit");

        while (true) {
            System.out.println(actions);
            userInput = scanner.nextLine().trim();
            if (forward.contains(userInput.toLowerCase())) {
                forwardMove = true;
                if (!listIterator.hasNext()) {
                    System.out.println(places.getLast());
                    continue;
                }
                System.out.println("Next item: " + listIterator.next());
                continue;
            }
            if (backward.contains(userInput.toLowerCase())) {
                if (!listIterator.hasPrevious()) {
                    System.out.println("Sydney (0)");
                    continue;
                }
                if (forwardMove) {
                    forwardMove = false;
                    listIterator.previous();
                }
                System.out.println("Previous item: " + listIterator.previous());
                continue;
            }
            if (list.contains(userInput.toLowerCase())) {
                System.out.println("Places: ");
                for (var place : places) {
                    System.out.println(place);
                }
                continue;
            }
            if (menu.contains(userInput.toLowerCase())) {
                System.out.println("Doing Menu things");
                continue;
            }
            if (quit.contains(userInput.toLowerCase())) {
                break;
            }
            System.out.println("Invalid input or out of range. Try again.");
        }
    }
}
