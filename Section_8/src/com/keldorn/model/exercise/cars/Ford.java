package com.keldorn.model.exercise.cars;

public class Ford extends Car {
    public Ford(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return super.startEngine() + this.getClass().getSimpleName();
    }

    @Override
    public String accelerate() {
        return super.accelerate() + this.getClass().getSimpleName();
    }

    @Override
    public String brake() {
        return super.brake() + this.getClass().getSimpleName();
    }
}
