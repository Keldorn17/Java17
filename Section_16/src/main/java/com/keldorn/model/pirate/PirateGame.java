package main.java.com.keldorn.model.pirate;

import main.java.com.keldorn.model.game.Game;
import main.java.com.keldorn.model.game.GameAction;
import main.java.com.keldorn.model.pirate.enums.Weapon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PirateGame extends Game<Pirate> {
    private static final List<List<String>> levelMap;

    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading Data...");
        loadData();

        if (levelMap.isEmpty()) {
            throw new RuntimeException("Could not load data, try later");
        }
        System.out.println("Finished Loading Data");
    }

    public PirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Pirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();
        for(Weapon weapon : weapons) {
            char init = weapon.name().charAt(0);
            map.put(init, new GameAction(init, "Use " + weapon, this::useWeapon));
        }
        map.putAll(getStandardActions());
        return map;
    }

    private static void loadData() {
        // Level 1 Towns
        levelMap.add(new ArrayList<>(List.of(
                "BridgeTown, Barbados",
                "Fitts Village, Barbados",
                "HoleTown, Barbados"
        )));
        // Level 2 Towns
        levelMap.add(new ArrayList<>(List.of(
                "Fort-de-France, Martinique",
                "Sainte-Anne, Martinique",
                "Le Vauclin, Martinique"
        )));
    }

    public static List<String> getTowns(int level) {
        if (level <= (levelMap.size() - 1)) {
            return levelMap.get(level);
        }
        return null;
    }

    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    @Override
    public boolean executeGameAction(int player, GameAction action) {
        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }
}
