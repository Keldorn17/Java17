import java.time.ZonedDateTime;

public class StringFormating {

    public static void main(String[] args) {

        String bulletIt = "Point a Bulleted List:\n" +
                "\t\u2022 First Point\n" +
                "\t\t\u2022 Sub Point";

        System.out.println(bulletIt);

        String textBlock = """
                Print a Bulleted List:
                    \u2022 Fist Point
                        \u2022 Sub Point""";

        System.out.println(textBlock);

        int age = 22;
        System.out.printf("Your age is %d%n", age);

        int yearOfBirth = ZonedDateTime.now().getYear() - age;
        System.out.printf("Age = %d, Birth year = %d\n", age, yearOfBirth);

        for (int i = 1; i <= 100000; i *= 10) {
            System.out.printf("Printing %6d %n", i);
        }

        String formattedString = String.format("Your age is %d", age);
        System.out.println(formattedString);

        formattedString = "Your age is %d".formatted(age);
        System.out.println(formattedString);
    }
}
