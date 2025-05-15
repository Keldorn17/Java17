public class Main {

    public static void main(String[] args) {

        System.out.println("Your final score was: " +
                calculateScore(800, 5, 100));
        System.out.println("Your final score was: " +
                calculateScore(10_000, 8, 200));

        displayHighScorePosition("Test1", calculateHighScorePosition(1500));
        displayHighScorePosition("Test2", calculateHighScorePosition(1000));
        displayHighScorePosition("Test3", calculateHighScorePosition(500));
        displayHighScorePosition("Test4", calculateHighScorePosition(100));
        displayHighScorePosition("Test5", calculateHighScorePosition(25));
    }

    private static int calculateScore(int score, int levelCompleted, int bonus) {
        return score + 1000 + (levelCompleted * bonus);
    }

    private static int calculateHighScorePosition(int playerScore) {
        int result = 4;

        if (playerScore >= 1000) {
            result = 1;
        } else if (playerScore >= 500) {
            result = 2;
        } else if (playerScore >= 100) {
            result = 3;
        }

        return result;
    }

    private static void displayHighScorePosition(String playerName, int highScorePosition) {
        System.out.println(playerName + " managed to get into position " + highScorePosition + " on the high score list");
    }
}
