package main.java.com.keldorn.model.card.poker;

import main.java.com.keldorn.model.card.Card;
import main.java.com.keldorn.model.card.Deck;

import java.util.*;
import java.util.function.Consumer;

public class PokerGame {
    private final List<Card> deck = new Deck().getStandardDeck();
    private int playerCound;
    private int cardsInHand;
    private List<PokerHand> pokerHands;
    private List<Card> remainingCards;

    public PokerGame(int playerCound, int cardsInHand) {
        this.playerCound = playerCound;
        this.cardsInHand = cardsInHand;
        pokerHands = new ArrayList<>(cardsInHand);
    }

    public void startPlay() {
        Collections.shuffle(deck);
        Deck.printDeck(deck);
        int randomMiddle = new Random().nextInt(15, 35);
        Collections.rotate(deck, randomMiddle);
        Deck.printDeck(deck);
        deal();
        System.out.println("-".repeat(30));
        Consumer<PokerHand> checkHand = PokerHand::evalHand;
        pokerHands.forEach(checkHand.andThen(System.out::println));

        int cardsDealt = playerCound * cardsInHand;
        int cardsRemaining = deck.size() - cardsDealt;

        remainingCards = new ArrayList<>(Collections.nCopies(cardsRemaining, null));
        remainingCards.replaceAll(c -> deck.get(cardsDealt + remainingCards.indexOf(c)));
        Deck.printDeck(remainingCards, "Remaining Cards", 2);
    }

    private void deal() {
        Card[][] hands = new Card[playerCound][cardsInHand];
        for (int deckIndex = 0, i = 0; i < cardsInHand; i++) {
            for (int j = 0; j < playerCound; j++) {
                hands[j][i] = deck.get(deckIndex++);
            }
        }

        int playerNo = 1;
        for (Card[] hand : hands) {
            pokerHands.add(new PokerHand(playerNo++, Arrays.asList(hand)));
        }
    }
}
