package main.java.com.keldorn;

import main.java.com.keldorn.util.Separator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        regularExpressions();
        miniChallenges();
        patterMatching();
        review();
        emailChallenge();
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

        for (var string : new String[]{
                "The bike is red, and has flat tires.",
                "I love being a new L.P.A. student!",
                "Hello, friends and family: Welcome!",
                "How are you, Mary?"
        }) {
            System.out.println(string + ": " + string.matches("^[A-Z].*\\p{Punct}$"));
        }
    }

    private static void patterMatching() {
        Separator.separator();
        String sentence = "I like B.M.W. motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matched + ": " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + ": " + sentence);
        System.out.println("sentence.length:" + sentence.length());
        System.out.println("Matched Ending Index: " + matcher.end());

        System.out.println(matcher.lookingAt() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on: " +
                sentence.substring(0, matcher.end()));

        matcher.reset();
        System.out.println(matcher.find() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on: " +
                sentence.substring(matcher.start(), matcher.end()));

        System.out.println("Matched on: " + matcher.group());

        String htmlSnippet = """
                <h1>My Heading</h1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPatter = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPatter.matcher(htmlSnippet);

        while (htmlMatcher.find()) {
//            System.out.println("group: " + htmlMatcher.group());
//            System.out.println("group0: " + htmlMatcher.group(0));
            System.out.println(htmlMatcher.group("level") + " " +
                    htmlMatcher.group(2));
            System.out.println("index = " + htmlMatcher.start("level"));
        }

        htmlMatcher.reset();
        htmlMatcher.results()
                .forEach(mr -> System.out.println(mr.group(1) + " " + mr.group(2)));

        String tabbedText = """
                group1 group2 group3
                1 2 3
                a b d
                """;
        tabbedText.lines()
                .flatMap(s -> Pattern.compile(" ").splitAsStream(s))
                .forEach(System.out::println);

        htmlMatcher.reset();
        String updatedSnippet = htmlMatcher.replaceFirst((mr) ->
                "<em>" + mr.group(2) + "</em>");
        Separator.separator();
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
        System.out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(
                Pattern.compile("<([hH]\\d)>(.*)</\\1>")
        );

        htmlMatcher.reset();
        Separator.separator();
        System.out.println("Using Back Reference: \n" +
                htmlMatcher.replaceFirst("<em>$2</em>"));

        String replacedHTML = htmlMatcher.replaceAll((mr) ->
                "<em>" + mr.group(2) + "</em>");
        Separator.separator();
        System.out.println(replacedHTML);

        htmlMatcher.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher.find()) {
            htmlMatcher.appendReplacement(sb,
                    switch (htmlMatcher.group(1).toLowerCase()) {
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }
        htmlMatcher.appendTail(sb);
        System.out.println(sb);
    }

    private static void review() {
        Separator.separator();
        String phoneList = """
                (800) 123-4567
                (800)123-4567
                (800) 123 4567
                800-123-4567
                800 123-4567
                800 123 4567
                8001234567
                """;
        Pattern phonePattern =
                Pattern.compile("\\(*[0-9]{3}[)\\s-]*[0-9]{3}[\\s-]*[0-9]{4}");
        // "\\(*[0-9]{3}[)\\s-]*[0-9]{3}[\\s-]*[0-9]{4}"
        // "\\(*\\d{3}[)\\s-]*\\d{3}[\\s-]*\\d{4}"
        // "\\(*[0-9]{3}[)\\s-]*\\d{3}[\\s-]*\\p{Digit}{4}"

        Matcher phoneMatcher = phonePattern.matcher(phoneList);
        phoneMatcher.results().forEach(mr -> System.out.println(mr.group()));

        String htmlSnippets = """
                <H1>My Heading</h1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p style="abc">This is another paragraph about something else.</p>
                <h3 id="third">Summary</h3>
                <br/>
                <p>Testing</p>
                """;

        Pattern htmlPatter =
                Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)(</\\1>)*",
                        Pattern.CASE_INSENSITIVE);
        // Pattern.compile("<(\\w+)[^>]*>([^\\v</>]*)((?i)</\\1>)*")
        // Pattern.compile("<(a-zA-Z_0-9)[^>]*>([^\\v</>]*)((?i)</\\1>)*")
        Matcher m = htmlPatter.matcher(htmlSnippets);
        m.results()
                .filter(mr -> mr.group(1).toLowerCase().startsWith("h"))
                .forEach(mr -> System.out.println("Full Tag: " + mr.group(0) +
                        "\n\tType: " + mr.group(1) +
                        "\n\tText: " + mr.group(2)));
    }

    private static void emailChallenge() {
        Separator.separator();
        String emailList = """
                john.boy@valid.com
                john.boy@invalid
                jane.doe-smith@valid.co.uk
                jane_Doe1976@valid.co.uk
                bob-1964@valid.net
                bob!@invalid.com
                elaine@valid-test.com.au
                elaineinvalid1983@.com
                david@valid.io
                david@invalid..com""";

        Pattern partialPattern =
                Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");

        Matcher emailMatcher = partialPattern.matcher(emailList);
        emailMatcher.results()
                .forEach(mr -> System.out.printf("[username=%s, domain=%s]%n",
                        mr.group(1), mr.group(2)));

        Pattern emailPattern =
                Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
        String[] emailSamples = emailList.lines().toArray(String[]::new);

        for (String email : emailSamples) {
            Matcher eMatcher = emailPattern.matcher(email);
            boolean matched = eMatcher.matches();
            System.out.println(email + " is " + (matched ? "VALID" : "INVALID"));
            if (matched) {
                System.out.printf("[username=%s, domain=%s]%n",
                        eMatcher.group(1), eMatcher.group(2));
            } else {
                System.out.println();
            }

        }
    }
}
