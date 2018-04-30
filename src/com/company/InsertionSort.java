package com.company;

public class InsertionSort {
    public static void sort(Comparable[] array) {

        int i = 1;
        while (i < array.length) {
            int j = i;
            while (j > 0 && array[j-1].compareTo(array[j]) == 1) {
                Comparable itemAtJ = array[j];
                array[j] = array[j - 1];
                array[j - 1] = itemAtJ;
                j = j-i;
            }
            i ++;
        }
    }
}
