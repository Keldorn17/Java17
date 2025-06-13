package com.keldorn.model.card;

import java.util.Set;

public record Card(Suit suit, Face face) {
    private static final Set<String> VALID_FACE_CARDS = Set.of("J", "Q", "K", "A");

    public int rank() {
        return face.getRank();
    }

    @Override
    public String toString() {
        return "%s%s(%d)".formatted(face.getFaceSymbol(), suit.getImage(), face.getRank());
    }

    public static Card getNumericCard(Suit suit, int numericCardNumber) {
        if (numericCardNumber < 2 || numericCardNumber > 10) {
            throw new IllegalArgumentException("numericCardNumber must be between 2 and 10");
        }
        return new Card(suit, Face.fromRank(numericCardNumber));
    }

    public static Card getFaceCard(Suit suit, String faceCardAbbreviation) {
        if (!isValidCard(faceCardAbbreviation)) {
            throw new IllegalArgumentException(faceCardAbbreviation + " is not a valid faceSymbol. [J, Q, K, A]");
        }
        return new Card(suit, Face.getFaceFromSymbol(faceCardAbbreviation));
    }

    private static boolean isValidCard(String faceCard) {
        return faceCard != null && VALID_FACE_CARDS.contains(faceCard.toUpperCase());
    }
}
