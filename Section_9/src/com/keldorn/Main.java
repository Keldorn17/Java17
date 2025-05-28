package com.keldorn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        varArgs();

        int[] arr = {1, 2, 3, 4, 5, 6};
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));

        multipleArrays();
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

    private static void arrayReferences() {
        int[] myIntArray = new int[5];
        int[] anotherArray = myIntArray;

        anotherArray[0] = 2;
        modifyArray(myIntArray);

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));
    }

    private static void modifyArray(int[] array) {
        array[1] = 2;
    }

    private static void printText(String... textList) {
        for (String t : textList) {
            System.out.println(t);
        }
    }

    private static void varArgs() {
        String[] splitStrings = "Hello World again".split(" ");
        printText(splitStrings);

        System.out.println("-".repeat(20));
        printText("Hello", "World", "again");

        System.out.println("-".repeat(20));
        printText();

        String[] sArray = {"first", "second", "third", "fourth", "fifth"};
        System.out.println(String.join(", ", sArray));
    }

    private static int[] readIntegers() {
        Scanner scanner = new Scanner(System.in);
        String[] list = scanner.nextLine().split(",");
        int[] result = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            result[i] = Integer.parseInt(list[i].trim());
        }
        return result;
    }

    private static int findMin(int[] list) {
        int min = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
            }
        }
        return min;
    }

    private static void reverseArray(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[i];
            arr[i] = temp;
        }
    }

    private static void multipleArrays() {
        int[][] array2 = new int[4][4];
        System.out.println(Arrays.toString(array2));
        System.out.println("array2.length = " + array2.length);

        for (var outer : array2) {
            System.out.println(Arrays.toString(outer));
        }

        for (int i = 0; i < array2.length; i++) {
            var innerArray = array2[i];
            for (int j = 0; j < innerArray.length; j++) {
//                System.out.print(array2[i][j] + " ");
                array2[i][j] = (i * 10) + (j + 1);
            }
//            System.out.println();
        }
//        for (var outer : array2) {
//            for (var element : outer) {
//                System.out.println(element + " ");
//            }
//            System.out.println();
//        }
        System.out.println(Arrays.deepToString(array2));

        array2[1] = new int[] {10, 20, 30};
        System.out.println(Arrays.deepToString(array2));

        Object[] anyArray = new Object[3];
        System.out.println(Arrays.toString(anyArray));

        anyArray[0] = new String[] {"a", "b", "c"};
        System.out.println(Arrays.deepToString(anyArray));

        anyArray[1] = new String[][] {
                {"1", "2"},
                {"3", "4", "5"},
                {"6", "7", "8", "9"}
        };
        System.out.println(Arrays.deepToString(anyArray));

        anyArray[2] = new int[2][2][2];
        System.out.println(Arrays.deepToString(anyArray));

        for (var element : anyArray) {
            System.out.println("Element type = " + element.getClass().getSimpleName());
            System.out.println("Element toString() = " + element);
            System.out.println(Arrays.deepToString((Object[]) element));
        }
    }
}
