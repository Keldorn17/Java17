package com.keldorn.model.places;

public record Place(String town, int distance) {
    @Override
    public String toString() {
        return String.format("%s (%d)", town, distance);
    }
}
