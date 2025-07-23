package main.java.com.keldorn.util;

public final class Separator {
    public static void separator() {
        separator(30);
    }

    public static void separator(boolean lineBefore) {
        if (lineBefore) {
            System.out.println();
        }
        separator(30);
    }

    public static void separator(int length) {
        separator('-', length);
    }

    public static void separator(Character separatorChar, int length) {
        System.out.println(separatorChar.toString().repeat(length));
    }
}
