package com.keldorn.model.exercise;

import java.util.Scanner;

public class MinimumElement {
    public static int readInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter count: ");
        return scanner.nextInt();
    }

    public static int[] readElements(int num) {
        Scanner scanner = new Scanner(System.in);
        int[] result = new int[num];
        for (int i = 0; i < result.length; i++) {
            System.out.print("Enter a number: ");
            result[i] = scanner.nextInt();
        }
        return result;
    }

    public static int findMin(int[] list) {
        int min = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
            }
        }
        return min;
    }
}
