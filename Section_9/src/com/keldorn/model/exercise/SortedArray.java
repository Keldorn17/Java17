package com.keldorn.model.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {
    public static int[] getIntegers(int size) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("Element %d contents %d%n", i, arr[i]);
        }
    }

    public static int[] sortIntegers(int[] arr) {
        int[] sortedArray = Arrays.copyOf(arr, arr.length);

        int temp;
        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[i] < sortedArray[j]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = temp;
                }
            }
        }
        return sortedArray;
    }
}
