package com.keldorn.model.polymorphism.car;

public class GasPoweredCar extends Car {
    private final double avgKmPerLiter;
    private final int cylinder;

    public GasPoweredCar(String description, double avgKmPerLiter, int cylinder) {
        super(description);
        this.avgKmPerLiter = avgKmPerLiter;
        this.cylinder = cylinder;
    }

    public double getAvgKmPerLiter() {
        return avgKmPerLiter;
    }

    public int getCylinder() {
        return cylinder;
    }
}
