package com.keldorn.model.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private final List<Card> cards;

    public Blackjack() {
        Deck deck = new Deck();
        cards = new LinkedList<>(deck.getShuffledDeck());
    }

    public void run() {
        List<Card> dealerHand = new ArrayList<>();
        List<Card> playerHand = new ArrayList<>();

        startingCards(dealerHand, playerHand);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dealer hand: [" + dealerHand.getFirst() + ", ??]");
        while (true) {
            System.out.println("Player hand");
            printHand(playerHand);
            if (calculateValue(playerHand) >= 21) {
                break;
            }
            System.out.print("Do you want to hit or stand? (H for hit, S for stand): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Empty input. Try again.");
                continue;
            }
            char value = input.charAt(0);
            if (value == 'H') {
                drawCardInto(playerHand);
                continue;
            }
            if (value == 'S') {
                break;
            }
            System.out.println("Invalid input. Try again.");
        }

        dealerHits(dealerHand);
        showResult(dealerHand, playerHand);
    }

    private void drawCardInto(List<Card> toAdd) {
        toAdd.add(cards.removeFirst());
    }

    private void startingCards(List<Card> dealer, List<Card> player) {
        drawCardInto(dealer);
        drawCardInto(player);
        drawCardInto(dealer);
        drawCardInto(player);
    }

    private int calculateValue(List<Card> hand) {
        int sum = 0;
        int aceCount = 0;

        for (var card : hand) {
            sum += card.face().getValue();
            if (card.face() == Face.ACE) {
                aceCount++;
            }
        }

        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    private void printHand(List<Card> hand) {
        System.out.println("Value: " + calculateValue(hand) + ", Cards: " + hand);
    }

    private void dealerHits(List<Card> dealer) {
        while (true) {
            int value = calculateValue(dealer);
            if (value >= 17) {
                break;
            }
            drawCardInto(dealer);
        }
    }

    private void calculateWinner(List<Card> dealer, List<Card> player) {
        int dealerValue = calculateValue(dealer);
        int playerValue = calculateValue(player);

        if ((playerValue > 21) || (dealerValue > playerValue && dealerValue <= 21)) {
            System.out.println("Dealer wins");
            return;
        }
        if (dealerValue == playerValue) {
            System.out.println("Tie");
            return;
        }
        System.out.println("Player wins");
    }

    private void showResult(List<Card> dealer, List<Card> player) {
        System.out.println("-".repeat(30));
        System.out.println("Player Hand");
        printHand(player);
        System.out.println("Dealer Hand");
        printHand(dealer);

        calculateWinner(dealer, player);
    }
}
