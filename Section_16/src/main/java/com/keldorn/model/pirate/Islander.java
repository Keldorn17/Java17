package main.java.com.keldorn.model.pirate;

import main.java.com.keldorn.model.pirate.enums.Weapon;

public final class Islander extends Combatant {
    public Islander(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
