package com.keldorn.model.exercise.cars;

public class Car {
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.name = name;
        engine = true;
        wheels = 4;
    }

    public String startEngine() {
        return "Car -> Engine is starting ";
    }

    public String accelerate() {
        return "Car -> Car is accelerating ";
    }

    public String brake() {
        return "Car -> Car is braking ";
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }
}
