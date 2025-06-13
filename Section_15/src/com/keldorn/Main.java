package com.keldorn;

import com.keldorn.model.card.Deck;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        collectionsOverview();
        cardTest();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void collectionsOverview() {
        separator();
        Collection<String> list = new HashSet<>();

        String[] names = {"Anna", "Bob", "Carol", "David", "Edna"};
        list.addAll(Arrays.asList(names));
        System.out.println(list);

        list.add("Fred");
        list.addAll(Arrays.asList("George", "Gary", "Grace"));
        System.out.println(list);
        System.out.println("Gary is in the list? " + list.contains("Gary"));

        list.removeIf(s -> s.charAt(0) == 'G');
        System.out.println(list);
        System.out.println("Gary is in the list? " + list.contains("Gary"));
    }

    private static void cardTest() {
        Deck deck = new Deck();
        deck.printDeck();
    }
}
