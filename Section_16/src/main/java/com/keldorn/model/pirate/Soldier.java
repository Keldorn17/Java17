package main.java.com.keldorn.model.pirate;

import main.java.com.keldorn.model.pirate.enums.Weapon;

public final class Soldier extends Combatant {
    public Soldier(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
