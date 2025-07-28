package main.java.com.keldorn.model.dice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class DiceGameSimple {
    private final static Random random = new Random();
    private final static Scanner scanner = new Scanner(System.in);

    private final int MAX_DICE = 5;
    private final List<Integer> dices = new ArrayList<>(MAX_DICE);

    public DiceGameSimple() {
        for (int i = 0; i < MAX_DICE; i++) {
            dices.add(0);
        }
    }

    private void rollDices(int... diceIndex) {
        var diceInts = random.ints(1, 7)
                .limit(diceIndex.length)
                .toArray();
        for (int i = 0; i < diceIndex.length && i < MAX_DICE; i++) {
            dices.set(diceIndex[i], diceInts[i]);
        }
    }

    private int getFirstIndexFromValue(int value) {
        return dices.indexOf(value);
    }

    private void rollDices() {
        rollDices(IntStream.range(0, MAX_DICE).toArray());
    }

    public void playGame() {
        rollDices();
        while (true) {
            System.out.println("You're dice are: " + dices);
            System.out.print("""
                    Press Enter to Score.
                    Type "All" to re-roll all the dice.
                    List numbers (separated by spaces) to re-roll selected dice.
                    -->\s""");
            String userInput = scanner.nextLine();
            if (userInput.isBlank()) {
                System.out.println("Game over. Real game would score and continue.");
                break;
            }
            if (userInput.equalsIgnoreCase("All")) {
                rollDices();
                continue;
            }
            String[] values = userInput.split(" ");
            int[] indexes = new int[values.length];
            try {
                for (int i = 0; i < indexes.length; i++) {
                    indexes[i] = getFirstIndexFromValue(Integer.parseInt(values[i].trim()));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input try again.");
                continue;
            }

            Set<Integer> rerollIndices = Arrays.stream(indexes).boxed().collect(Collectors.toSet());

            List<Integer> keptDice = IntStream.range(0, MAX_DICE)
                    .filter(i -> !rerollIndices.contains(i))
                    .mapToObj(dices::get)
                    .toList();

            System.out.println("Keeping: " + keptDice);

            rollDices(indexes);
        }
    }

}
