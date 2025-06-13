package com.keldorn.model.card;

import java.util.ArrayList;
import java.util.List;

public record CardType(Suit suit) {
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        for (var face : Face.values()) {
            cards.add(new Card(suit, face));
        }
        return cards;
    }
}
