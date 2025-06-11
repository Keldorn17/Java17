package com.keldorn;

import java.util.*;
import java.util.function.*;

public class Main {
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        lambdaIntro();
        lambdaExpressions();
        miniLambdaChallenges();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void lambdaIntro() {
        separator();
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie", "Brown")
        ));

        // Using anonymous class
        var comparatorLastName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        System.out.println(people);

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName.compareTo(o2.lastName);
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);
    }

    private static void lambdaExpressions() {
        separator();
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"
        ));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("-----");
        list.forEach((myString) -> System.out.println(myString));
//        list.forEach(s -> System.out.println(s));
//        list.forEach(System.out::println);
        System.out.println("-----");
        final String prefix = "nato";
        list.forEach((myString) -> {
            char first = myString.charAt(0);
            System.out.println(prefix + " " + myString + " means " + first);
        });

        int result = calculator((a, b) -> a + b, 5, 2);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator(
                (a, b) -> a.toUpperCase() + " " + b.toUpperCase(),
                "Ralph", "Kramden");

        var coords = Arrays.asList(
                new double[]{47.2160, -95.2348},
                new double[]{29.1566, -89.2495},
                new double[]{35.1556, -90.0659}
        );

        coords.forEach(s -> System.out.println(Arrays.toString(s)));

        BiConsumer<Double, Double> p1 = (lat, lng) ->
                System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng);

        var firstPoint = coords.getFirst();
        processPoint(firstPoint[0], firstPoint[1], p1);

        System.out.println("------");
        coords.forEach(s -> processPoint(s[0], s[1], p1));
        System.out.println("------");
        coords.forEach(s -> processPoint(s[0], s[1], (lat, lng) ->
                System.out.printf("[lat:%.3f lon:%.3f]%n", lat, lng)));

        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(System.out::println);

        list.addAll(List.of("echo", "easy", "earnest"));
        list.forEach(System.out::println);

        System.out.println("------");
        list.removeIf(s -> s.startsWith("ea"));
        list.forEach(System.out::println);

        System.out.println("------");
        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        list.forEach(System.out::println);

        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.fill(emptyStrings, "");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> (i + 1) + ". ");
        System.out.println(Arrays.toString(emptyStrings));

        Arrays.setAll(emptyStrings, (i) -> (i + 1) + ". "
                + switch (i) {
            case 0 -> "one";
            case 1 -> "two";
            case 2 -> "three";
            default -> "";
        });
        System.out.println(Arrays.toString(emptyStrings));

        String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
        String[] randomList = randomlySelectedValues(15, names,
                () -> new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(randomList));
    }

    private static <T> T calculator(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of operation: " + result);
        return result;
    }

    private static <T> void processPoint(T t1, T t2, BiConsumer<T, T> consumer) {
        consumer.accept(t1, t2);
    }

    private static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {
        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }
        return selectedValues;
    }

    private static void miniLambdaChallenges() {
        separator();
        Consumer<String> printTheParts = s -> {
            String[] parts = s.split(" ");
            for (var part : parts) {
                System.out.println(part);
            }
        };

        printTheParts.accept("Let's split this up into an array");

        Consumer<String> printWordsForEach = s -> {
            String[] parts = s.split(" ");
            Arrays.asList(parts).forEach(System.out::println);
        };

        printWordsForEach.accept("Let's split this up into an array");

        Consumer<String> printWordsConcise = s ->
                Arrays.asList(s.split(" ")).forEach(System.out::println);

        printWordsConcise.accept("Let's split this up into an array");

        // Function<String, String> is also a good choice
        UnaryOperator<String> supplier = (String source) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(supplier.apply("1234567890"));
        System.out.println(everySecondCharacter("1234567890", supplier));

        Supplier<String> iLoveJava = () -> "I love java!";
        System.out.println(iLoveJava.get());
    }

    // private static String everySecondCharacter(String parameter, Function<String, String function>)
    private static <T, R> R everySecondCharacter(T parameter, Function<T, R> function) {
        return function.apply(parameter);
    }
}
