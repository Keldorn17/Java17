public class CodingExercise {

    public static void main(String[] args) {

    }

    public static void printNumberInWord(int number) {
        String result = switch (number) {
            case 0 -> "ZERO";
            case 1 -> "ONE";
            case 2 -> "TWO";
            case 3 -> "THREE";
            case 4 -> "FOUR";
            case 5 -> "FIVE";
            case 6 -> "SIX";
            case 7 -> "SEVEN";
            case 8 -> "EIGHT";
            case 9 -> "NINE";
            default -> "OTHER";
        };
        System.out.println(result);
    }

    public static boolean isLeapYear(int year) {
        boolean result = false;
        if (isYearValid(year)) {
            result = (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
        }
        return result;
    }

    public static int getDaysInMonth(int month, int year) {
        int result = -1;
        if (isYearValid(year)) {
            result = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                case 2 -> isLeapYear(year) ? 29 : 28;
                default -> -1;
            };
        }
        return result;
    }

    public static boolean isYearValid(int year) {
        return (year >= 1) && (year <= 9999);
    }
}
