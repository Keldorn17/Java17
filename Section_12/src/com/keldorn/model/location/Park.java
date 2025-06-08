package com.keldorn.model.location;

public class Park extends Point {
    private final String name;

    public Park(String name, Location location) {
        super(location);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " National Park";
    }
}
