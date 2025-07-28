package main.java.com.keldorn.model.game;

import main.java.com.keldorn.util.Separator;

import java.util.Scanner;

public final class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {
        System.out.print("Enter your playing name: ");
        String name = scanner.nextLine();

        System.out.printf("Welcome to %s, %s!%n", game.getGameName(), name);
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {
        boolean done = false;
        while (!done) {
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for (Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.println("Enter Next Action: ");

            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);

            if (gameAction != null) {
                Separator.separator();
                done = game.executeGameAction(playerIndex, gameAction);
                if (!done) {
                    Separator.separator();
                }
            }
        }
    }

    public static String getUserInput(String prompt) {
        System.out.println(prompt + ": ");
        return scanner.nextLine();
    }
}
