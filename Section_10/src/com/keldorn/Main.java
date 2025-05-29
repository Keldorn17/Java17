package com.keldorn;

import com.keldorn.dto.GroceryItem;
import com.keldorn.model.grocery.GroceryList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        arrayLists();
        moreLists();
        arrayAndArrayLists();

        GroceryList groceryList = new GroceryList();
        groceryList.run();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void arrayLists() {
        separator();
        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));

        List<GroceryItem> groceryItems = new ArrayList<>();

        groceryItems.add(new GroceryItem("butter"));
        groceryItems.add(new GroceryItem("banana", "PRODUCE", 3));
        groceryItems.add(new GroceryItem("oranges", "PRODUCE", 5));
        groceryItems.set(1, new GroceryItem("apples", "PRODUCE", 4));
        groceryItems.remove(2);
        System.out.println(groceryItems);
    }

    private static void moreLists() {
        separator();
        String[] items = {"apples", "bananas", "milk", "eggs"};

        List<String> list = List.of(items);
        System.out.println(list);

        System.out.println(list.getClass().getName());
//        list.add("yogurt");
        ArrayList<String> groceries = new ArrayList<>(list);
        groceries.add("yogurt");
        System.out.println(groceries);

        ArrayList<String> nextList = new ArrayList<>(
                List.of("pickles", "mustard", "cheese")
        );
        System.out.println(nextList);

        groceries.addAll(nextList);
        System.out.println(groceries);

        System.out.println("Third item = " + groceries.get(2));

        if (groceries.contains("mustard")) {
            System.out.println("List contains mustard");
        }
        groceries.add("yogurt");
        System.out.println("first = " + groceries.indexOf("yogurt"));
        System.out.println("last = " + groceries.lastIndexOf("yogurt"));

        System.out.println(groceries);
        groceries.remove(1);
        System.out.println(groceries);
        groceries.remove("yogurt");
        System.out.println(groceries);

        groceries.removeAll(List.of("apples", "eggs"));
        System.out.println(groceries);

        groceries.retainAll(List.of("apples", "milk", "mustard", "cheese"));
        System.out.println(groceries);

        groceries.clear();
        System.out.println(groceries);

        groceries.addAll(List.of("apples", "milk", "mustard", "cheese"));
        groceries.addAll(Arrays.asList("eggs", "pickles", "mustard", "ham"));

        System.out.println(groceries);
        groceries.sort(Comparator.naturalOrder());
        System.out.println(groceries);

        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);

        var groceryArray = groceries.toArray(new String[0]);
        System.out.println(Arrays.toString(groceryArray));
    }

    private static void arrayAndArrayLists() {
        separator();
        String[] originalArray = new String[] {"First", "Second", "Third"};
        var originalList = Arrays.asList(originalArray);

        originalList.set(0, "one");
        System.out.println("List = " + originalList);
        System.out.println("Array = " + Arrays.toString(originalArray));

        originalList.sort(Comparator.naturalOrder());
        System.out.println("Array = " + Arrays.toString(originalArray));

        List<String> newList = Arrays.asList("Sunday", "Monday", "Tuesday");
        System.out.println(newList);
    }
}
