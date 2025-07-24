package main.java.com.keldorn;

import main.java.com.keldorn.dto.Seat;
import main.java.com.keldorn.dto.Seat2;
import main.java.com.keldorn.model.course.Student;
import main.java.com.keldorn.model.course.dto.Course;
import main.java.com.keldorn.util.Separator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static int counter;

    public static void main(String[] args) {
        streams();
        streamChallenge();
        streamsIntermediate();
        streamTerminal();
        streamingStudents();
    }

    private static void streams() {
        Separator.separator();
        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }

        Separator.separator();

//        List<String> firstOnes = bingoPool.subList(0, 15);
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        Separator.separator(true);

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        Separator.separator();

        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
//                .forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s -> System.out.print(s + " "));
        Separator.separator(true);

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
//                .forEach(System.out::println);
        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                        e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.rangeClosed(1, 100) // normal range is range(1, 100) is 1 - 99 (exclusive the end)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));
    }

    private static boolean isPrime(int wholeNumber) {
        if (wholeNumber <= 2) {
            return (wholeNumber == 2);
        }
        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    private static void streamChallenge() {
        Separator.separator(true);
        int seed = 1;
        final int SEED_INCREMENT = 15;
        var streamB = Stream.iterate(seed, n -> n + 1)
                .limit(15)
                .map(s -> "B" + s);
        seed += SEED_INCREMENT;

        var streamI = IntStream.rangeClosed(seed, seed + SEED_INCREMENT)
                .mapToObj(s -> "I" + s);
        seed += SEED_INCREMENT;

        int rSeed = seed;
        var streamN = Stream.generate(Main::getCounter)
                .limit(SEED_INCREMENT)
                .map(i -> "N" + (rSeed + i));
        seed += SEED_INCREMENT;

        final int seedMax = seed + SEED_INCREMENT;
        var streamG = Stream.iterate(seed, i -> i <= seedMax, i -> i + 1)
                .map(s -> "G" + s);
        seed += SEED_INCREMENT;

        final int nSeed = seed;
        String[] oLabels = new String[15];
        Arrays.setAll(oLabels, i -> "O" + (nSeed + i));
        var streamO = Arrays.stream(oLabels);

        var streamBI = Stream.concat(streamB, streamI);
        var streamNG = Stream.concat(streamN, streamG);
        var streamBING = Stream.concat(streamBI, streamNG);
        Stream.concat(streamBING, streamO)
                .forEach(System.out::println);

        Separator.separator();
        Stream.generate(() -> new Random().nextInt(rSeed, rSeed + SEED_INCREMENT))
                .distinct()
                .limit(SEED_INCREMENT)
                .map(i -> "N" + i)
                .sorted()
                .forEach(System.out::println);
    }

    private static int getCounter() {
        return counter++;
    }

    private static void streamsIntermediate() {
        Separator.separator();
        IntStream.iterate('A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
//                .filter(i -> Character.toUpperCase(i) > 'E')
//                .skip(5)
//                .dropWhile(i -> Character.toUpperCase(i) <= 'E')  // Once it become true it never checks again so in this case toUppercase does nothing.
//                .takeWhile(i -> i < 'a')
                .map(Character::toUpperCase)
                .distinct()
                .forEach(d -> System.out.printf("%c ", d));

        Separator.separator(true);
        Random random = new Random();
        Stream.generate(() -> random.nextInt('A', (int) 'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(d -> System.out.printf("%c ", d));

        Separator.separator(true);
        var stream = getSeatStream();
        stream.forEach(System.out::println);
    }

    private static Stream<Seat> getSeatStream() {
        int maxSeats = 100;
        int seatsInRow = 10;

        return Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("--> " + s))
//                .mapToDouble(Seat::price)
//                .boxed()
//                .map("%.2f"::formatted);
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));
    }

    private static void streamTerminal() {
        Separator.separator();
        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i + 3)
                .summaryStatistics();
        System.out.println("Result = " + result);

        final int currentYear = LocalDate.now().getYear();
        var leapYearData = IntStream
                .iterate(2000, i -> i <= currentYear, i -> i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();

        System.out.println("Leap Year Data = " + leapYearData);

        Seat2[] seats = new Seat2[100];
        Arrays.setAll(seats, i -> new Seat2((char) ('A' + i / 10), i % 10 + 1));
//        Arrays.asList(seats).forEach(System.out::println);
        long reservationCount = Arrays
                .stream(seats)
                .filter(Seat2::isReserved)
                .count();
        System.out.println("reservationCount = " + reservationCount);

        boolean hasBookings = Arrays
                .stream(seats)
                .anyMatch(Seat2::isReserved);

        System.out.println("hasBookings = " + hasBookings);

        boolean fullyBooked = Arrays
                .stream(seats)
                .allMatch(Seat2::isReserved);

        System.out.println("fullyBooked = " + fullyBooked);

        boolean eventWashedOut = Arrays
                .stream(seats)
                .noneMatch(Seat2::isReserved);

        System.out.println("eventWashedOut = " + eventWashedOut);
    }

    private static void streamingStudents() {
        Separator.separator();
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(10)
                .forEach(System.out::println);
    }
}
