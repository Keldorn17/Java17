package com.keldorn.model.location;

public record Location(double latitude, double longitude) {
    @Override
    public String toString() {
        return String.format("[%f, %f]", latitude, longitude);
    }
}
