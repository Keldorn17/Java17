package com.keldorn.model.polymorphism.car;

public class Car {
    private final String description;

    public Car(String description) {
        this.description = description;
    }

    public void startEngine() {
        System.out.println("Engine starting for a(n) " + getClass().getSimpleName());
    }

    public void drive() {
        System.out.println("Driving");
    }

    protected void runEngine() {
        System.out.println("Engine is running");
    }

    public String getDescription() {
        return description;
    }
}
