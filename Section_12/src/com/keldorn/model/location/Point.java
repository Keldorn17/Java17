package com.keldorn.model.location;

public abstract class Point implements Mappable {
    private final Location location;

    public Point(Location location) {
        this.location = location;
    }

    protected Location getLocation() {
        return location;
    }
}
