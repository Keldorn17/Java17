package com.keldorn.model.location;

public class River extends Line {
    private final String name;

    public River(String name, Location... locations) {
        super(locations);
        this.name = name;
    }

    @Override
    public void render() {
        System.out.printf("Render %s River as %s (%s)%n", name, getExplicitName(), getLocations());
    }
}
