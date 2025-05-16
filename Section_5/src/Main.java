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

        int newScore = calculateScore("Tim", 500);
        System.out.println("New score is " + newScore);
        System.out.println("New score is " + calculateScore(75));

        calculateScore();
        System.out.println(convertToCentimeters(5, 6));

        System.out.println(getDurationString(3945));
        System.out.println(getDurationString(65, 45));
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

    public static int calculateScore(String playerName, int score) {
        System.out.println("Player " + playerName + " scored " + score + " points");
        return score * 1000;
    }

    public static int calculateScore(int score) {
        return calculateScore("Anonymous", score);
    }

    public static void calculateScore() {
        System.out.println("No player name, no player score.");
    }

    public static double convertToCentimeters(int inches) {
        return Math.round(inches * 2.54 * 100) / 100.0;
    }

    public static double convertToCentimeters(int foot, int inches) {
        return convertToCentimeters(foot * 12 + inches);
    }

    public static String getDurationString(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Seconds cannot be negative!");
        }

        int minutes = seconds / 60;
        int secondsRemaining = seconds % 60;
        return getDurationString(minutes, secondsRemaining);
    }

    public static String getDurationString(int minutes, int seconds) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative!");
        }
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds has to be between 0 and 59!");
        }

        int hours = minutes / 60;
        int minutesRemaining = minutes % 60;
        return String.format("%02dh %02dm %02ds", hours, minutesRemaining, seconds);
    }


}
