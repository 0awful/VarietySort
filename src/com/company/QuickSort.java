package com.company;

public class QuickSort {
    public static void sort(Comparable[] array, int low, int high) {
        if (low < high) {
            int p = hoarePartition(array, low, high);
            sort(array, low, p);
            sort(array, p+1, high);
        }
    }

    private static int hoarePartition(Comparable[] array, int low, int high){
        Comparable pivotValue = array[low];
        int i = low - 1;
        int j = high + 1;
        while(true) {
            do {
                i ++;
            } while (array[i].compareTo(pivotValue) == -1);

            do {
                j --;
            } while (array[j].compareTo(pivotValue) == 1);

            if (i >= j) {
                return j;
            }

            Comparable elementToSwitch = array[i];
            array[i] = array[j];
            array[j] = elementToSwitch;

        }


    }
}
