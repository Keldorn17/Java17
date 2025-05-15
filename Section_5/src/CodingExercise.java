public class CodingExercise {

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

}
