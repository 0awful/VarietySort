package com.company;

public class MergeSort {

    public static void sort(Comparable[] array, Comparable[] tempArray) {

        // we iterate through the array using fictitious array's of length N
        for (int width = 1; width < array.length; width = 2 * width){

            for (int i = 0; i < array.length; i = i + 2 * width) {

                bottomUpMergeHelper(array, tempArray, i, Math.min(i+width, array.length), Math.min(i+2*width, array.length));

            }

            ArrayUtils.copyArray(tempArray, array);

        }


    }

    private static void bottomUpMergeHelper(Comparable[] array, Comparable[] tempArray, int indexLeft, int indexRight, int indexEnd){
        int i = indexLeft;
        int j = indexRight;

        for (int k = indexLeft; k < indexEnd; k++){

            if (i < indexRight &&  (j >= indexEnd || (array[i].compareTo(array[j])) < 0) ) {
                tempArray[k] = array[i];
                i++;
            } else {
                tempArray[k] = array[j];
                j++;
            }
        }
    }
}
