package com.keldorn.model.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Line implements Mappable {
    private final List<Location> locations = new ArrayList<>();

    public Line(Location... locations) {
        this.locations.addAll(Arrays.asList(locations));
    }

    protected List<Location> getLocations() {
        return locations;
    }

    @Override
    public void render() {
        System.out.printf("Render %s as %s (%s)%n", this, getExplicitName(), getLocations());
    }
}
