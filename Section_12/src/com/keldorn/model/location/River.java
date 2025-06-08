package com.keldorn.model.location;

public class River extends Line {
    private final String name;

    public River(String name, Location... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " River";
    }
}
