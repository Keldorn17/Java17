package com.keldorn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

    }

    private static void arrayTest() {
        double[] myDoubleArray = new double[10];
        myDoubleArray[2] = 2.5;
        System.out.println(myDoubleArray[2]);

        int[] firstFive = {1, 2, 3, 4, 5};
        System.out.println("first = " + firstFive[0]);
        int arrayLength = firstFive.length;
        System.out.println("Length of array = " + arrayLength);
        System.out.println("last = " + firstFive[arrayLength - 1]);

        int[] newArray;
//        newArray = new int[]{5, 4, 3, 2 ,1};
        newArray = new int[5];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = newArray.length - i;
        }

        for (var i : newArray) {
            System.out.print(i + " ");
        }

        System.out.println("\n" + Arrays.toString(newArray));
        Object objectVariable = newArray;
        if (objectVariable instanceof int[]) {
            System.out.println("objectVariable is really an int array");
        }

        Object[] objectArray = new Object[3];
        objectArray[0] = "Hello";
        objectArray[1] = new StringBuilder("World");
        objectArray[2] = newArray;
    }

    private static int[] getRandomArray(int len) {
        Random random = new Random();
        int[] newInt = new int[len];
        for (int i = 0; i < len; i++) {
            newInt[i] = random.nextInt(100);
        }

        return newInt;
    }

    private static void usingArrays() {
        int[] firstArray = getRandomArray(10);
        System.out.println(Arrays.toString(firstArray));
        Arrays.sort(firstArray);
        System.out.println(Arrays.toString(firstArray));

        int[] secondArray = new int[10];
        System.out.println(Arrays.toString(secondArray));
        Arrays.fill(secondArray, 5);
        System.out.println(Arrays.toString(secondArray));

        int[] thirdArray = getRandomArray(10);
        System.out.println(Arrays.toString(thirdArray));

        int[] fourthArray = Arrays.copyOf(thirdArray, thirdArray.length);
        System.out.println(Arrays.toString(fourthArray));

        Arrays.sort(fourthArray);
        System.out.println(Arrays.toString(thirdArray));
        System.out.println(Arrays.toString(fourthArray));

        int[] smallerArray = Arrays.copyOf(thirdArray, 5);
        System.out.println(Arrays.toString(smallerArray));

        int[] largerArray = Arrays.copyOf(thirdArray, 15);
        System.out.println(Arrays.toString(largerArray));

        String[] sArray = {"Able", "Jane", "Mark", "Ralph", "David"};
        Arrays.sort(sArray);
        System.out.println(Arrays.toString(sArray));
        if (Arrays.binarySearch(sArray, "Mark") >= 0) {
            System.out.println("Found Mark in the list");
        }

        int[] s1 = {1, 2, 3, 4, 5};
        int[] s2 = {1, 2, 3, 4, 5};

        if (Arrays.equals(s1, s2)) {
            System.out.println("Arrays are equal");
        } else {
            System.out.println("Arrays are not equal");
        }
    }

    private static void descendingSort(int len) {
        int[] arr = getRandomArray(len);
        System.out.println(Arrays.toString(arr));

        int[] arrDes = Arrays.stream(arr).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println(Arrays.toString(arrDes));
    }

    private static void sortIntegers(int[] array) {
        System.out.println(Arrays.toString(array));
        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean flag = true;
        int temp;
        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    flag = true;
                    System.out.println("----->" + Arrays.toString(sortedArray));
                }
            }
            System.out.println("-->" + Arrays.toString(sortedArray));
        }
    }
}
