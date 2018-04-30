package com.company;

public class BubbleSort {
    public static Comparable[] halfSort(Comparable[] array){
        /// returns an array where the top half of the array are in the right order as a result of bubble sorting


        boolean sorting = true;
        while (sorting) {
            // check if sorted enough
            for (int j = array.length/2; j < array.length; j ++) {
                if (array[j-1].compareTo(array[j]) == -1) {
                    if (j == array.length-1) {
                        sorting = false;
                    }
                } else  {
                    break;
                }
            }

            // if it isn't we do the bubble sort pattern once
            if (sorting) {

                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i].compareTo(array[i + 1]) == 1) {
                        Comparable itemAtI = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = itemAtI;
                    }
                }

            }
        }
        return array;

    }

    public static Comparable[] sort(Comparable[] array ){

        // if you ever wanted to use the real bubble sort it is provided with the library.

        boolean sorting = true;
        while (sorting) {
            // check if sorted enough
            for (int j = 1; j < array.length; j ++) {
                if (array[j-1].compareTo(array[j]) == -1) {
                    if (j == array.length-1) {
                        sorting = false;
                    }
                } else  {
                    break;
                }
            }

            // if it isn't we do the bubble sort pattern once
            if (sorting) {

                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i].compareTo(array[i + 1]) == 1) {
                        Comparable itemAtI = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = itemAtI;
                    }
                }

            }
        }
        return array;
    }
}
