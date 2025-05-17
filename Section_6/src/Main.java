public class Main {

    public static void main(String[] args) {

        int switchValue = 4;

//        switch (switchValue) {
//            case 1:
//                System.out.println("Value was 1");
//                break;
//            case 2:
//                System.out.println("Value was 2");
//                break;
//            case 3: case 4: case 5:
//                System.out.println("Was a 3, a 4, or a 5");
//                System.out.println("Actually it was a " + switchValue);
//                break;
//            default:
//                System.out.println("Was not 1, 2, 3, 4, or 5");
//                break;
//        }

        switch (switchValue) {
            case 1 -> System.out.println("Value was 1");
            case 2 -> System.out.println("Value was 2");
            case 3, 4, 5 -> {
                System.out.println("Was a 3, a 4, or a 5");
                System.out.println("Actually it was a " + switchValue);
            }
            default -> System.out.println("Was not 1, 2, 3, 4, or 5");
        }
        String month = "april";
        System.out.println(month.toUpperCase() + " is in the " + getQuarter(month) + " quarter");

        NatoPhoneticAlphabet natoPhoneticAlphabet = new NatoPhoneticAlphabet();
        System.out.println(natoPhoneticAlphabet.getNatoPhoneticString("Asd"));

        printWeekDay(2);
        printDayOfWeek(3);
    }

    public static String getQuarter(String month) {
        return switch (month.toUpperCase()) {
            case "JANUARY", "FEBRUARY", "MARCH" -> "1st";
            case "APRIL", "MAY", "JUNE" -> "2nd";
            case "JULY", "AUGUST", "SEPTEMBER" -> "3rd";
            case "OCTOBER", "NOVEMBER", "DECEMBER" -> "4th";
            default -> {
                String badResponse = month + " is bad";
                yield badResponse;
            }
        };
    }

    public static void printDayOfWeek(int day) {
        String dayOfWeek = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid Day";
        };
        System.out.println(day + " stands for " +dayOfWeek);
    }

    public static void printWeekDay(int dayOfWeek) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        try {
            System.out.println(dayOfWeek + " stands for " + days[--dayOfWeek]);  // dayOfWeek - 1
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Invalid Day");
        }
    }
}
