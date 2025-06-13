package com.keldorn.model.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
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

    public void printDeck() {
        System.out.println(clubs);
        System.out.println(diamonds);
        System.out.println(hearts);
        System.out.println(spades);
    }
}
