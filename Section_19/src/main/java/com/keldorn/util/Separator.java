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
}
