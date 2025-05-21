import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int switchValue = 4;
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

        System.out.println();
        for (double i = 7.5; i <= 10; i += .25) {
            double interestAmount = calculateInterest(100, i);
            if (interestAmount > 8.5) {
                break;
            }
            System.out.println("$100.00 at " + i + "% interest = $" + interestAmount);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        System.out.println("\n1 to 100 prime numbers: " + list);
        System.out.println(list.size() + " prime found");

        System.out.println();
        int sum = 0;
        int count = 0;
        for (int i = 1; i < 1000; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                sum += i;
                count++;
                System.out.println("Found a match = " + i);
            }
            if (count == 5) {
                break;
            }
        }
        System.out.println("sum = " + sum + "\n");

        int j = 1;
        while (j <= 5) {
            System.out.println(j);
            j++;
        }

        do {
            System.out.println("Do while: " + j);
            j++;
        } while (j <= 5);

        System.out.println();
        int number = 0;
        while (number < 50) {
            number += 5;
            if (number % 25 == 0) {
                continue;
            }
            System.out.print(number + "_");
        }

        System.out.println();
        int test = 4;
        int oddCound = 0;
        int evenCound = 0;
        while (test <= 20) {
            test++;
            if (!isEvenNumber(test)) {
                oddCound++;
                continue;
            }
            System.out.print(test + " ");
            evenCound++;

            if (evenCound == 5) {
                break;
            }
        }
        System.out.println("\nNumber of even numbers found: " + evenCound);
        System.out.println("Number of odd numbers found: " + oddCound);

        System.out.println(sumDigits(125));

        System.out.println();
        int currentYear = ZonedDateTime.now().getYear();

//        try {
//            System.out.println(getInputFromConsole(currentYear));
//        } catch (NullPointerException e) {
//            System.out.println(getInputFromScanner(currentYear));
//        }

//        System.out.println(userInputSum(5));

        minMaxChallenge();
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
        System.out.println(day + " stands for " + dayOfWeek);
    }

    public static void printWeekDay(int dayOfWeek) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        try {
            System.out.println(dayOfWeek + " stands for " + days[--dayOfWeek]);  // dayOfWeek - 1
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Invalid Day");
        }
    }

    public static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }

    public static boolean isPrime(int number) {
        boolean result = number == 2 || (number > 2 && number % 2 != 0);

        for (int i = 3; i * i <= number && result; i += 2) {
            if (number % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }

    public static int sumDigits(int number) {
        if (number < 0) {
            return -1;
        }
        int temp = number;
        int sum = 0;
        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }
        return sum;
    }

    public static String getInputFromConsole(int currentYear) {
        String name = System.console().readLine("Hi, What's your Name? ");
        System.out.println("Hi " + name);

        String dateOfBirth = System.console().readLine("What year were you born? ");
        int age = currentYear - Integer.parseInt(dateOfBirth);

        return String.format("So you are %d years old", age);
    }

    public static String getInputFromScanner(int currentYear) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your Name? ");
        String name = scanner.nextLine();
        System.out.println("Hi " + name);

        System.out.println("What year were you born? ");

        boolean validDOB = false;
        int age = 0;

        do {
            System.out.println("Enter a year of birth >= " +
                    (currentYear - 125) + " and <= " + currentYear);
            age = checkData(currentYear, scanner.nextLine());
            validDOB = age >= 0;
        } while (!validDOB);

        return String.format("So you are %d years old", age);
    }

    public static int checkData(int currentYear, String dateOfBirth) {
        int dob;
        try {
            dob = Integer.parseInt(dateOfBirth);
        } catch (NumberFormatException e) {
            return -1;
        }
        int minimumYear = currentYear - 125;

        if ((dob < minimumYear) || (dob > currentYear)) {
            return -1;
        }

        return (currentYear - dob);
    }

    public static int userInputSum(int numberOfUserInput) {
        int i = 1;
        int sum = 0;

        while (i <= numberOfUserInput) {
            System.out.printf("\nEnter number #%d: ", i);
            try {
                sum += getUserInteger();
                i++;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid number");
            }
        }
        return sum;
    }

    public static int getUserInteger() {
        int userInput;
        try {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
        } catch (NoSuchElementException | IllegalStateException e) {
            throw new NoSuchElementException("No number were found.");
        }
        return userInput;
    }

    public static void minMaxChallenge() {
        int min = 0, max = 0, count = 0;
        while (true) {
            System.out.println("Enter a number or any character to quit: ");
            int number;
            try {
                number = getUserInteger();
                count++;
            } catch (NoSuchElementException e) {
                if (count == 0) {
                    System.out.println("No data found.");
                    break;
                }
                System.out.printf("Minimum value: %d\nMaximum value: %d\n", min, max);
                break;
            }
            if (count == 1) {
                min = number;
                max = number;
                continue;
            }
            min = Math.min(min, number);
            max = Math.max(max, number);
        }
    }
}
