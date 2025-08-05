package main.java.com.keldorn.util;

public final class Separator {
    public static void separator(boolean lineBefore) {
        if (lineBefore) System.out.println();
        separator();
    }

    public static void separator() {
        separator(30);
    }

    public static void separator(int length) {
        separator('-', length);
    }

    public static void separator(Character character, int length) {
        System.out.println(character.toString().repeat(length));
    }

    public static void separatorWithHeader(String header) {
        separatorWithHeader('=', header);
    }

    public static void separatorWithHeader(Character character, String header) {
        separatorWithHeader(character, 10, header);
    }

    public static void separatorWithHeader(Character character, int length, String header) {
        String separator = character.toString().repeat(length);
        System.out.printf("%s %s %s%n", separator, header, separator);
    }
}
