package com.company;

public class BogoSort {
    public static Comparable[] sort(Comparable[] array) {

        boolean sorting = true;
        while (sorting) {
            // check if it is sorted
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1].compareTo(array[i]) == -1) {
                    if (i == array.length - 1) {
                        sorting = false;
                    }
                } else {
                    break;
                }
            }

            if (sorting) {
                FisherYatesShuffle.shuffle(array);
            }
        }

        return array;
    }
}
