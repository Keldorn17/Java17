package com.keldorn.model.machine;

import com.keldorn.interfaces.Trackable;

public class Truck implements Trackable {
    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded'");
    }
}
