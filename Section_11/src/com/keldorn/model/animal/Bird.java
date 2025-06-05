package com.keldorn.model.animal;

import com.keldorn.interfaces.FlightEnabled;
import com.keldorn.interfaces.Trackable;

public class Bird extends Animal implements FlightEnabled, Trackable {
    public Bird() {
        super("default bird", "small", 10);
    }

    public Bird(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        System.out.println("Flaps wings. Moves with a speed of " + speed);
    }

    @Override
    public void makeNoise() {
        System.out.println("Makes noise");
    }

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is fling");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded'");
    }
}
