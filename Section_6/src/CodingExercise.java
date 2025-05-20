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

    public static boolean isOdd(int number) {
        return number > 0 && number % 2 != 0;
    }

    public static int sumOdd(int start, int end) {
        int sum = 0;
        for (int i = start; i < end + 1; i++) {
            sum += isOdd(i) ? i : 0;
        }
        if (start > end || start < 0) {
            sum = -1;
        }
        return sum;
    }

    public static boolean isPalindrome(int number) {
        int reverse = 0;
        int temp = number;
        while (temp != 0) {
            reverse *= 10;
            reverse += temp % 10;
            temp /= 10;
        }
        return reverse == number;
    }

    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1;
        }
        int lastDigit = number % 10;
        int firstDigit = lastDigit;
        number /= 10;

        while (number != 0) {
            firstDigit = number % 10;
            number /= 10;
        }
        return firstDigit + lastDigit;
    }

    public static int getEvenDigitSum(int number) {
        if (number < 0) {
            return -1;
        }
        int sum = 0;
        int temp = number;
        int remainder;
        while (temp != 0) {
            remainder = temp % 10;
            temp /= 10;
            if (remainder % 2 == 0) {
                sum += remainder;
            }
        }
        return sum;
    }

    public static boolean hasSharedDigit(int a, int b) {
        boolean result = false;
        if ((a >= 10 && a <= 99) && (b >= 10 && b <= 99)) {
            int aRemainder = a % 10;
            int bRemainder = b % 10;
            a /= 10;
            b /= 10;
            if (aRemainder == bRemainder || aRemainder == b || a == bRemainder || a == b) {
                result = true;
            }
        }
        return result;
    }

    public static boolean hasSameLastDigit(int a, int b, int c) {
        boolean result = false;
        if (isValid(a) && isValid(b) && isValid(c)) {
            int aLastDigit = a % 10;
            int bLastDigit = b % 10;
            int cLastDigit = c % 10;
            result = aLastDigit == bLastDigit || aLastDigit == cLastDigit || bLastDigit == cLastDigit;

        }
        return result;
    }

    public static boolean isValid(int number) {
        return number >= 10 && number <= 1000;
    }

    public static void printFactors(int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
            return;
        }
        StringBuilder result = new StringBuilder();
        result.append("1 ");
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                result.append(i).append(" ");
            }
        }
        if (number != 1) {
            result.append(number);
        }
        System.out.println(result);
    }

    public static int getGreatestCommonDivisor(int first, int second) {
        int result = -1;
        if (first >= 10 && second >= 10) {
            int min = (first < second) ? first : second;
            result = 1;
            for (int i = 1; i <= min; i++) {
                if (first % i == 0 && second % i == 0) {
                    result = i;
                }
            }
        }
        return result;
    }

    public static boolean isPerfectNumber(int number) {
        if (number < 1) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return (sum == number);
    }

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
            return;
        }
        String[] numberWords = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        StringBuilder result = new StringBuilder();
        int digitCount = getDigitCount(number);
        int reversedNumber = reverse(number);
        while (digitCount != 0) {
            if (reversedNumber == 0) {
                result.append(numberWords[0]).append(" ");
                digitCount--;
                continue;
            }
            result.append(numberWords[reversedNumber % 10]).append(" ");
            reversedNumber /= 10;
            digitCount--;
        }
        System.out.println(result.toString().trim());
    }

    public static int getDigitCount(int number) {
        if (number < 0) return -1;
        if (number == 0) return 1;
        return (int) Math.log10(Math.abs(number)) + 1;
    }

    public static int reverse(int number) {
        int result = 0;
        int calcNumber = number;
        while (calcNumber != 0) {
            result *= 10;
            result += calcNumber % 10;
            calcNumber /= 10;
        }
        return result;
    }

}
