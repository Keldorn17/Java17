package main.java.com.keldorn.model.pirate;

import main.java.com.keldorn.model.pirate.enums.Feature;
import main.java.com.keldorn.model.pirate.enums.Loot;

import java.util.*;

public final class Pirate extends Combatant {
    private final List<Town> townsVisited = new LinkedList<Town>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate(String name) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();
    }

    boolean useWeapon() {
        int count = opponents.size();
        if (count > 0) {
            int opponentIndex = count - 1;
            if (count > 1) {
                opponentIndex = new Random().nextInt(count);
            }
            Combatant combatant = opponents.get(opponentIndex);
            if (super.useWeapon(combatant)) {
                opponents.remove(opponentIndex);
            } else {
                return combatant.useWeapon(this);
            }
        }
        return false;
    }

    boolean visitTown() {
        List<Town> levelTowns = PirateGame.getTowns(value("level"));
        if (levelTowns == null) return true;
        Town town = levelTowns.get(value("townIndex"));
        if (town != null) {
            townsVisited.add(town);
            loot = town.loot();
            opponents = town.opponents();
            features = town.features();
            return false;
        }
        return true;
    }

    boolean hasExperiences() {
        return (features != null && !features.isEmpty());
    }

    boolean hasOpponents() {
        return (opponents != null && !opponents.isEmpty());
    }

    public String information() {
        var current = ((LinkedList<Town>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
        return "---> " + current +
                "\n" + super.information() +
                "\n\ttownsVisited = " + Arrays.toString(simpleNames);
    }

    boolean findLoot() {
        if (!loot.isEmpty()) {
            Loot item = loot.removeFirst();
            System.out.println("Found " + item + "!");
            adjustValue("score", item.getWorth());
            System.out.println(name() + "'s score is now " + value("score"));
        }
        if (loot.isEmpty()) {
            return visitNextTown();
        }
        return false;
    }

    boolean experienceFeature() {
        if (!features.isEmpty()) {
            Feature item = features.removeFirst();
            System.out.println("Ran into " + item + "!");
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + "'s health is now " + value("health"));
        }
        return (value("health") <= 0);
    }

    private boolean visitNextTown() {
        int townIndex = value("townIndex");
        List<Town> towns = PirateGame.getTowns(value("level"));
        if (towns == null) return true;
        if (townIndex >= (towns.size() - 1)) {
            System.out.println("Leveling up! Bonus: 500 points!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("Sailing to next Town! Bonus: 50 points!");
            adjustValue("townIndex", 1);
            adjustValue("score", 50);
        }
        return visitTown();
    }
}
