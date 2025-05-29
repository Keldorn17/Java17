package com.keldorn.model.grocery;

import com.keldorn.dto.GroceryItem;

import java.util.*;

public class GroceryList {
    private final List<GroceryItem> groceryItems = new ArrayList<>();

    private void separator() {
        System.out.println("-".repeat(30));
    }

    private int getUserAction() {
        Scanner scanner = new Scanner(System.in);
        int result;
        while (true) {
            separator();
            System.out.print("""
                    Available actions:
                    0 - to shutdown
                    1 - to add item(s) to list (comma delimited list)
                    2 - to remove any items (comma delimited list)
                    Enter a number for which action you want to do:\s""");
            try {
                result = scanner.nextInt();
                break;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Invalid input try again.");
            }
        }
        return result;
    }

    private void addItems() {
        Scanner scanner = new Scanner(System.in);

        separator();
        System.out.print("""
                Format for a list: {Product Name, Product Type, count}
                Enter the item(s) to add (comma delimited list):\s""");
        String input = scanner.nextLine();

        String[] items = input.split("}");
        for (var item : items) {
            if (item.trim().charAt(0) == ',') {
                item = item.substring(1).trim();
            }
            var splits = item.split(",");
            var name = splits[0].replace("{", "").trim();
            var type = splits[1].trim();
            var count = Integer.parseInt(splits[2].trim());
            var newItem = new GroceryItem(name, type, count);
            if (!groceryItems.contains(newItem)) {
                groceryItems.add(newItem);
            }
        }
    }

    private void removeItems() {
        if (groceryItems.isEmpty()) {
            System.out.println("You cannot remove from the list because it empty.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        separator();
        System.out.println("Items: ");
        for (int i = 0; i < groceryItems.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, groceryItems.get(i));
        }
        System.out.print("""
                Format: ItemIndex1, ItemIndex2, ItemIndex3, ...
                Enter the item(s) index to remove(comma delimited list):\s""");
        var input = scanner.nextLine();
        if (input.isEmpty()) {
            return;
        }
        var items = input.split(",");
        int count = 0;
        for (var item : items) {
            groceryItems.remove(Integer.parseInt(item.trim()) - 1 - count);
            count++;
        }
    }

    public void run() {
        boolean mainLoop = true;
        while (mainLoop) {
            var userInput = getUserAction();
            switch (userInput) {
                case 0 -> mainLoop = false;
                case 1 -> addItems();
                case 2 -> removeItems();
                default -> System.out.println("Invalid input try again!");
            }
        }
    }
}
