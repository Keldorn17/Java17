package main.java.com.keldorn;

import main.java.com.keldorn.util.Separator;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static int counter;

    public static void main(String[] args) {
        streams();
        streamChallenge();
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
}
