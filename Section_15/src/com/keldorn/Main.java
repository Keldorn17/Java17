package com.keldorn;

import com.keldorn.model.card.Blackjack;
import com.keldorn.model.card.Card;
import com.keldorn.model.card.Deck;
import com.keldorn.model.card.Suit;
import com.keldorn.model.card.poker.PokerGame;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        collectionsOverview();
        cardTest();
//        blackjackTest();
        pokerGameTest();
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
        separator();
        Deck deck = new Deck();
        deck.printDeck();

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Suit.HEART, "A");
        Arrays.fill(cardArray, aceOfHearts);
        Deck.printDeck(Arrays.asList(cardArray), "Aces of Hearts", 1);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("cards.size() = " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Deck.printDeck(acesOfHearts, "Aces of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Suit.CLUB, "K");
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Deck.printDeck(kingsOfClubs, "Kings of Clubs", 1);

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Deck.printDeck(cards, "Card Collection with Aces added", 2);

        Collections.copy(cards, kingsOfClubs);
        Deck.printDeck(cards, "Card Collection with Kings copied", 2);

        cards = List.copyOf(kingsOfClubs);
        Deck.printDeck(cards, "List Copy of Kings", 1);

        Deck.printDeck(deck.getShuffledDeck(), "Shuffled Deck");

        List<Card> fullDeck = deck.getStandardDeck();
        Collections.reverse(fullDeck);
        Deck.printDeck(fullDeck, "Reversed Deck of Cards");

        var sortingAlgorithm = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);
        Collections.sort(fullDeck, sortingAlgorithm);
        Deck.printDeck(fullDeck, "Standard Deck sorted by rank, suit");

        Collections.reverse(fullDeck);
        List<Card> kings = new ArrayList<>(fullDeck.subList(4, 8));
        Deck.printDeck(kings, "Kings in deck", 1);

        List<Card> tens = new ArrayList<>(fullDeck.subList(16, 20));
        Deck.printDeck(tens, "Tens in deck", 1);

//        Collections.shuffle(fullDeck);
        int subListIndex = Collections.indexOfSubList(fullDeck, tens);
        System.out.println("sublist index for tens = " + subListIndex);
        System.out.println("contains = " + fullDeck.containsAll(tens));

        boolean disjoint = Collections.disjoint(fullDeck, tens);
        System.out.println("disjoint = " + disjoint);

        boolean disjoint2 = Collections.disjoint(tens, kings);
        System.out.println("disjoint2 = " + disjoint2);

        separator();
        Card tenOfHearts = Card.getNumericCard(Suit.HEART, 10);
        fullDeck.sort(sortingAlgorithm);
        int foundIndex = Collections.binarySearch(fullDeck, tenOfHearts, sortingAlgorithm);
        System.out.println("foundIndex = " + foundIndex);
        System.out.println("foundIndex = " + fullDeck.indexOf(tenOfHearts));
        System.out.println(fullDeck.get(foundIndex));

        Card tenOfClubs = Card.getNumericCard(Suit.CLUB, 10);
        Collections.replaceAll(fullDeck, tenOfClubs, tenOfHearts);
        Deck.printDeck(fullDeck.subList(32, 36), "Tens row", 1);

        Collections.replaceAll(fullDeck, tenOfHearts, tenOfClubs);
        Deck.printDeck(fullDeck.subList(32, 36), "Tens row", 1);

        if (Collections.replaceAll(fullDeck, tenOfHearts, tenOfClubs)) {
            System.out.println("Tens of hearts replaced with tens of clubs");
        } else {
            System.out.println("No tens of hearts found in the list");
        }

        System.out.println("Ten of Clubs Cards = " +
                Collections.frequency(fullDeck, tenOfClubs));
        System.out.println("Best Card = " + Collections.max(fullDeck, sortingAlgorithm));
        System.out.println("Worst Card = " + Collections.min(fullDeck, sortingAlgorithm));

        var sortBySuit = Comparator.comparing(Card::suit)
                .thenComparing(Card::rank);
        fullDeck.sort(sortBySuit);
        Deck.printDeck(fullDeck, "Sorted by Suit, Rank");

        List<Card> copied = new ArrayList<>(fullDeck.subList(0, 13));
        Collections.rotate(copied, 2);
        System.out.println("UnRotated: " + fullDeck.subList(0, 13));
        System.out.println("Rotated " + 2 + ": " + copied);

        copied = new ArrayList<>(fullDeck.subList(0, 13));
        Collections.rotate(copied, -2);
        System.out.println("UnRotated: " + fullDeck.subList(0, 13));
        System.out.println("Rotated " + -2 + ": " + copied);

        copied = new ArrayList<>(fullDeck.subList(0, 13));
        for (int i = 0; i < copied.size() / 2; i++) {
            Collections.swap(copied, i, copied.size() - 1 - i);
        }
        System.out.println("Manual reverse: " + copied);

        copied = new ArrayList<>(fullDeck.subList(0, 13));
        Collections.reverse(copied);
        System.out.println("Using reverse: " + copied);
    }

    private static void blackjackTest() {
        separator();
        Blackjack blackJack = new Blackjack();
        blackJack.run();
    }

    private static void pokerGameTest() {
        PokerGame fiveCardDraw = new PokerGame(4, 5);
        fiveCardDraw.startPlay();
    }
}
