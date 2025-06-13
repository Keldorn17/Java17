package com.keldorn.model.card;

public enum Face {
    TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"),
    FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
    EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
    JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

    private final int rank;
    private final String faceSymbol;

    Face(int rank, String faceSymbol) {
        this.rank = rank;
        this.faceSymbol = faceSymbol;
    }

    public int getRank() {
        return rank;
    }

    public String getFaceSymbol() {
        return faceSymbol;
    }

    public static Face fromRank(int rank) {
        if (rank < 2 || rank > 14) {
            throw new IllegalArgumentException("Rank must be between 2 and 14");
        }
        return Face.values()[rank - 2];
    }

    public static Face getFaceFromSymbol(String faceSymbol) {
        try {
            int numberCardValue = Integer.parseInt(faceSymbol);
            return fromRank(numberCardValue);
        } catch (NumberFormatException e) {
            return switch (faceSymbol.toUpperCase()) {
                case "J" -> Face.JACK;
                case "Q" -> Face.QUEEN;
                case "K" -> Face.KING;
                case "A" -> Face.ACE;
                default -> throw new IllegalArgumentException(faceSymbol + " is not a valid faceSymbol. [J, Q, K, A]");
            };
        }
    }
}
