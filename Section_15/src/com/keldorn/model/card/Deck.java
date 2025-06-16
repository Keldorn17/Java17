package com.keldorn.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final String DESCRIPTION_DEFAULT = "Current Deck";
    private static final int ROWS_DEFAULT = 4;

    private final List<Card> clubs = new CardType(Suit.CLUB).getCards();
    private final List<Card> diamonds = new CardType(Suit.DIAMOND).getCards();
    private final List<Card> hearts = new CardType(Suit.HEART).getCards();
    private final List<Card> spades = new CardType(Suit.SPADE).getCards();

    public List<Card> getStandardDeck() {
        List<Card> cards = new ArrayList<>(clubs);
        cards.addAll(diamonds);
        cards.addAll(hearts);
        cards.addAll(spades);
        return cards;
    }

    public List<Card> getShuffledDeck() {
        List<Card> cards = new ArrayList<>(getStandardDeck());
        Collections.shuffle(cards);
        return cards;
    }

    public void printDeck() {
        System.out.println(clubs);
        System.out.println(diamonds);
        System.out.println(hearts);
        System.out.println(spades);
    }

    public static void printDeck(List<Card> cards) {
        printDeck(cards, DESCRIPTION_DEFAULT);
    }

    public static void printDeck(List<Card> cards, String description) {
        printDeck(cards, description, ROWS_DEFAULT);
    }

    public static void printDeck(List<Card> cards, String description, int rows) {
        if (description != null && !description.isEmpty()) {
            System.out.println("-".repeat(30));
            System.out.println(description.trim());
        }
        int cardPerRow = (int) Math.ceil((double) cards.size() / rows);
        for (int i = 0; i < rows; i++) {
            for (int j = (i * cardPerRow); j < cardPerRow + (i * cardPerRow); j++) {
                if (j >= cards.size()) {
                    System.out.println();
                    return;
                }
                System.out.print(cards.get(j) + " ");
            }
            System.out.println();
        }
    }
}
