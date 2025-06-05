package com.keldorn.dto;

import com.keldorn.interfaces.FlightEnabled;

public record DragonFly(String name, String type) implements FlightEnabled {
    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}
