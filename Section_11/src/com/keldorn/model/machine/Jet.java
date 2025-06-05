package com.keldorn.model.machine;

import com.keldorn.enums.FlightStages;
import com.keldorn.interfaces.FlightEnabled;
import com.keldorn.interfaces.Trackable;

public class Jet implements FlightEnabled, Trackable {
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

    @Override
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage);
    }
}
