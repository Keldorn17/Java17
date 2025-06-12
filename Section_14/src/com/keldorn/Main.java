package com.keldorn;

import java.util.*;
import java.util.function.*;

class PlainOld {
    private static int last_id = 1;
    private final int id;

    public PlainOld() {
        id = PlainOld.last_id++;
        System.out.println("Creating a PlainOld Object: id = " + id);
    }

    public int getId() {
        return id;
    }
}

public class Main {
    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    record Person2(String first) {
        public String last(String s) {
            return first + " " + s.substring(0, s.indexOf(" "));
        }
    }

    public static void main(String[] args) {
        lambdaIntro();
        lambdaExpressions();
        miniLambdaChallenges();
        lambdaChallenge();
        methodReferences();
        methodReferencesChallenge();
        chainingLambdas();
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

    private static void lambdaChallenge() {
        separator();
        String[] firstNames = {"Bob", "John", "Alex", "Anna", "Jessica"};
        List<String> names = new ArrayList<>(List.of(firstNames));
        System.out.println(Arrays.toString(firstNames));

        Arrays.setAll(firstNames, i -> firstNames[i].toUpperCase());
        System.out.println(Arrays.toString(firstNames));

        Arrays.setAll(firstNames, i -> firstNames[i] += " " + getRandomChar('A', 'Z') + ".");
        Arrays.asList(firstNames).forEach(System.out::println);

        names.replaceAll(s -> {
            StringBuilder reverse = new StringBuilder();
            reverse.append(s).append(" ");
            for (int i = 0; i < s.length(); i++) {
                if (i == 0) {
                    reverse.append(Character.toUpperCase(s.charAt(s.length() - 1)));
                    continue;
                }
                if (i == s.length() - 1) {
                    reverse.append(Character.toLowerCase(s.charAt(0)));
                    continue;
                }
                reverse.append(s.charAt(s.length() - 1 - i));
            }
            return reverse.toString();
        });
        names.forEach(s -> System.out.println("Name with reverse of first name is " + s));

        names.removeIf(s -> {
            String[] name = s.split(" ");
            return name[0].equals(name[1]);
        });

        names.forEach(System.out::println);
    }

    private static char getRandomChar(char startChar, char endChar) {
        Random random = new Random();
        return (char) random.nextInt(startChar, endChar + 1);
    }

    private static void methodReferences() {
        separator();
        List<String> list = new ArrayList<>(List.of(
                "Anna", "Bob", "Chuck", "Dave"
        ));
        list.forEach(System.out::println);
        calculator(Integer::sum, 10, 25);
        calculator(Double::sum, 2.5, 7.5);

        Supplier<PlainOld> reference1 = PlainOld::new;
//        Supplier<PlainOld> reference1 = () -> new PlainOld();
        PlainOld newPojo = reference1.get();

        System.out.println("Getting array");
        PlainOld[] pojo1 = seedArray(PlainOld::new, 10);

        calculator(String::concat, "Hello ", "World");

        UnaryOperator<String> u1 = String::toUpperCase;
        System.out.println(u1.apply("Hello"));

        String result = "Hello".transform(u1);
        System.out.println("Result = " + result);

        result = result.transform(String::toLowerCase);
        System.out.println("Result = " + result);

        Function<String, Boolean> f0 = String::isEmpty;
        boolean resultBoolean = result.transform(f0);
        System.out.println("Result = " + resultBoolean);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }

    private static void methodReferencesChallenge() {
        separator();
        String[] names = {"Bob", "John", "Alex", "Anna", "Jessica"};

        Person2 person = new Person2("Tim");

        List<UnaryOperator<String>> list = new ArrayList<>(List.of(
                String::toUpperCase,
                s -> s + (" " + getRandomChar('A', 'Z') + "."),
                s -> s + (" " + reverse(s, 0, s.indexOf(" "))),
                Main::reverse,
                String::new,
//                s -> new String("Howdy " + s),
                String::valueOf,
                person::last,
                (new Person2("Mary"))::last
        ));

        applyChanges(names, list);
    }

    private static void applyChanges(String[] names, List<UnaryOperator<String>> stringFunctions) {
        List<String> backedByArray = Arrays.asList(names);
        for (var function : stringFunctions) {
            backedByArray.replaceAll(s -> s.transform(function));
            System.out.println(Arrays.toString(names));
        }
    }

    private static String reverse(String s) {
        return reverse(s, 0, s.length());
    }

    private static String reverse(String s, int start, int end) {
        return new StringBuilder(s.substring(start, end)).reverse().toString();
    }

    private static void chainingLambdas() {
        separator();
        String name = "Tim";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));

        Function<String, String> lastName = s -> s.concat(" Buchalka");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(name));

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));

        Function<String, String[]> f0 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f0.apply(name)));

        Function<String, String> f1 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(f1.apply(name));

        Function<String, Integer> f2 = uCase
                .andThen(s -> s.concat(" Buchalka"))
                .andThen(s -> s.split(" "))
                .andThen(s -> String.join(", ", s))
                .andThen(String::length);

        System.out.println(f2.apply(name));

        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0
                .andThen(s -> System.out.print(" - "))
                .andThen(s1));

        Predicate<String> p1 = s -> s.equals("TIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Tim");
        Predicate<String> p3 = s -> s.startsWith("T");
        Predicate<String> p4 = s -> s.endsWith("e");

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name));

        record Person(String firstName, String lastName) {}

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));

        list.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        list.forEach(System.out::println);

        separator();
        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);

        separator();
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println);

        separator();
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName)
                .reversed());
        list.forEach(System.out::println);
    }
}
