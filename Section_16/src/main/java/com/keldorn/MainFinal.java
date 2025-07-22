package main.java.com.keldorn;

import main.java.com.keldorn.model.game.GameConsole;
import main.java.com.keldorn.model.pirate.PirateGame;

//class SpecialGameConsole<T extends Game<? extends Player>> extends GameConsole<Game<? extends  Player>> {
//    public SpecialGameConsole(Game<? extends Player> game) {
//        super(game);
//    }
//}

public class MainFinal {
    public static void main(String[] args) {
        GameConsole<PirateGame> game =
                new GameConsole<>(new PirateGame("Pirate Game"));

    }
}
