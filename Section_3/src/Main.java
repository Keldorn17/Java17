import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Hello World
        System.out.println("Hello World");

        // Sum numbers
        int[] myNumbers = {35, 12, 6};
        int total = 0;
        for (var number : myNumbers) {
            total += number;
        }
        System.out.println("Sum Using Arrays: " + Arrays.stream(myNumbers).sum());
        System.out.println(total);

        // Min/Max Value for an Integer
        System.out.printf("Integer Value Range (%d to %d)\n", Integer.MIN_VALUE, Integer.MAX_VALUE);

        // Min/Max Value for a Byte
        System.out.printf("Byte Value Range (%d to %d)\n", Byte.MIN_VALUE, Byte.MAX_VALUE);

        // Long declaration
        long myLongNumber = 2_147_483_647_123L;
        System.out.println("A long has a width of " + Long.SIZE);
        System.out.printf("Long Value Range (%d to %d)\n", Long.MIN_VALUE, Long.MAX_VALUE);

        // Challenge
        byte byteNumber = Byte.MAX_VALUE;
        short shortNumber = Short.MAX_VALUE;
        int intNumber = Integer.MAX_VALUE;

        long longNumber = 50_000L + 10L * (byteNumber + shortNumber + (long) intNumber);
        System.out.println(longNumber);

        // Challenge
        // The objective of this challenge, is to convert a given number of pounds to kilograms.
        double pounds = 45d;
        double kgInPounds = pounds * 0.45359237d;
        System.out.println(pounds + " pounds in Kg: " + kgInPounds);

        char myUnicodeChar = '\u0044';
        char myDecimalCode = 68;
        System.out.println(myUnicodeChar);
        System.out.println(myDecimalCode);

        System.out.println("My values are: " + '?' + ", " + '\u003f' + ", " + (char) 63);

        String myString = "I wish I had \u00241,000,000.00";
        System.out.println(myString);

        // numberString isn't accessible outside the score
        {
            String numberString = "250.55";
            numberString = numberString + "49.55";
            System.out.println(numberString);
        }

        char firstChar = 'a';
        char secondChar = 'b';
        // a + b = Ã
        System.out.println((char) (firstChar + secondChar));
        //ab
        System.out.println("" + firstChar + secondChar);

        // Challenge
        int result = 10;
        // Lossy conversion, we would get an error if we use it like this: result = result - 5.5;
        // result = (int) (result - 5.5) also works because of the casting
        result -= 5.5;
        System.out.println(result);
        // Post-fix Operator result++, result--
    }
}
