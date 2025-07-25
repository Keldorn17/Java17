package main.java.com.keldorn.model.pirate.enums;

public enum Feature {
    ALLIGATOR(-45),
    ALOE(5),
    JELLYFISH(-10),
    PINEAPPLE(10),
    SNAKE(-25),
    SPRING(25),
    SUN_POISON(-15);

    private final int healthPoints;

    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
