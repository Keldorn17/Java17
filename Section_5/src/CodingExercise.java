public class CodingExercise {

    private static final double INVALID_VALUE = -1.0;

    public static void main(String[] args) {

    }

    public static void checkNumber(int number) {
        System.out.println(number > 0 ? "positive" : number < 0 ? "negative" : "zero");
    }

    public static long toMilesPerHour(double kilometersPerHour) {
        long result = Math.round(kilometersPerHour / 1.609);
        return result < 0 ? -1 : result;
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
            return;
        }
        System.out.println(kilometersPerHour + " km/h = " + toMilesPerHour(kilometersPerHour) + " mi/h");
    }

    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes < 0) {
            System.out.println("Invalid Value");
            return;
        }
        int megaBytes = kiloBytes / 1024;
        int remainderKiloBytes = kiloBytes % 1024;
        System.out.println(kiloBytes + " KB = " + megaBytes + " MB and " + remainderKiloBytes + " KB");
    }

    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        boolean wakeUp = barking && (hourOfDay < 8 || hourOfDay > 22);
        if (hourOfDay < 0 || hourOfDay > 23) {
            wakeUp = false;
        }

        return wakeUp;
    }

    public static boolean isLeapYear(int year) {
        if (year <= 1 || year >= 9999) {
            return false;
        }
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static boolean areEqualByThreeDecimalPlaces(double number1, double number2) {
        return (int) (number1 * 1000) == (int) (number2 * 1000);
    }

    public static boolean hasEqualSum(int first, int second, int third) {
        return (first + second) == third;
    }

    public static boolean hasTeen(int first, int second, int third) {
        return isTeen(first) || isTeen(second) || isTeen(third);
    }

    public static boolean isTeen(int age) {
        return age >= 13 && age <= 19;
    }

    public static double area(double radius) {
        double result = INVALID_VALUE;
        if (radius >= 0) {
            result = Math.PI * Math.pow(radius, 2);
        }
        return result;
    }

    public static double area(double x, double y) {
        double result = INVALID_VALUE;
        if (x >= 0 && y >= 0) {
            result = x * y;
        }
        return result;
    }

    public static void printYearsAndDays(long minutes) {
        if (minutes < 0) {
            System.out.println("Invalid Value");
            return;
        }
        int days = (int) (minutes / 60 / 24);
        int years = days / 365;
        int daysRemaining = days % 365;
        System.out.printf(minutes + " min = " + years + " y and " + daysRemaining + " d");
    }

    public static void printEqual(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            System.out.println("Invalid Value");
            return;
        }
        if (a == b && a == c) {
            System.out.println("All numbers are equal");
        } else if (a != b && a != c && b != c) {
            System.out.println("All numbers are different");
        } else {
            System.out.println("Neither all are equal or different");
        }
    }

    public static boolean isCatPlaying(boolean summer, int temperature) {
        final int minTemp = 25;
        final int maxTemp = summer ? 45 : 35;
        return temperature >= minTemp && temperature <= maxTemp;
    }

}
