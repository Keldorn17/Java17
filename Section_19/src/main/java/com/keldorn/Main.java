package main.java.com.keldorn;

import main.java.com.keldorn.util.Separator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        regularExpressions();
        miniChallenges();
    }

    private static void regularExpressions() {
        Separator.separator();
        String helloWorld = "%s %s".formatted("Hello", "World");

        System.out.println(format("%s %s", "Hello", "World"));

        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";

        String[] patterns = {
                "abc",
                "ab|bc",
                "[0-9]",
                "[0-9]{2}",
                "[0-9]+",
                "[a-zA-Z]*",
                "^[a-zA-Z]{3}",
                "[aA]ny\\b",
                "[a-zA-Z]*$"
        };
        for (String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: " + pattern + " => " + output);
        }
        Separator.separator();

        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

        String[] lines = paragraph.split("\\R");
        System.out.println("This paragraph has " + lines.length + " lines");
        String[] words = paragraph.split("\\s");
        System.out.println("This paragraph has " + words.length + " words");
        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner(paragraph);
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R");

//        while (scanner.hasNext()) {
//            String element = scanner.next();
//            System.out.println(element);
//        }

//        scanner.tokens()
//                .map(s -> s.replaceAll("\\p{Punct}", ""))
//                .flatMap(s -> Arrays.stream(s.split("\\s+")))
//                .filter(s -> s.matches("[a-zA-Z]+ble"))
//                .forEach(System.out::println);
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));
        scanner.close();

    }

    private static String format(String regexp, String... args) {
        for (var arg : args) {
            regexp = regexp.replaceFirst("%s", arg);
        }

//        int index = 0;
//        while (regexp.matches(".*%s.*")) {
//            regexp = regexp.replaceFirst("%s", args[index++]);
//        }

        return regexp;
    }

    private static void miniChallenges() {
        Separator.separator();
        String sentence = "Hello, World!";
        boolean matches = sentence.matches("Hello, World!");
        System.out.println(sentence + ": " + matches);
        Separator.separator();

        String sentence2 = "The bike is red.";
        boolean matches2 = sentence2.matches("^[A-Z].*[.]$");
        System.out.println(sentence2 + ": " + matches2);
        Separator.separator();

        for (var string : new String[] {
                "The bike is red, and has flat tires.",
                "I love being a new L.P.A. student!",
                "Hello, friends and family: Welcome!",
                "How are you, Mary?"
        }) {
            System.out.println(string + ": " + string.matches("^[A-Z].*\\p{Punct}$"));
        }
        Separator.separator();

    }
}
