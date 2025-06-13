package com.keldorn.model.card;

public enum Suit {
    CLUB, DIAMOND, HEART, SPADE;

    public char getImage() {
        return switch (this) {
            case CLUB -> (char) 9827;
            case DIAMOND -> (char) 9830;
            case HEART -> (char) 9829;
            case SPADE -> (char) 9824;
        };
    }
}
