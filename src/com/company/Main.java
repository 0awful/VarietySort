package com.company;

public class Main {

    public static void main(String[] args) {

        // inspired by
        // https://i.redd.it/xmh10hvt4lr01.png


        int[] testSet = {1,10,12,3,8,11,4,9,13,16,6,2,7,15,14,5};
        System.out.println(toString(testSet));
        int[] finalSet = varietySort(testSet);

        System.out.println(toString(finalSet));
    }


    private static int[][] split(int[] array) {

        // allocate space for two halves of the array
        int[] firstHalf = new int[array.length/2];
        // this handles odd array lengths ensuring that we always have space for each element
        int[] secondHalf = new int[array.length - firstHalf.length];

        // iterate through and assign elements to the apropriate array.
        for (int i = 0; i< array.length; i++) {
            if (i < firstHalf.length) {
                firstHalf[i] = array[i];
            } else {
                secondHalf[i - firstHalf.length] = array[i];
            }

        }

        int[][] arrayOfArrays = {firstHalf,secondHalf};

        return  arrayOfArrays;
    }



    public static int[] varietySort(int[] array){
        // 1. cut the list in half.
        int[][] splitSet =  split(array);

        // 2. Bubble sort the first half until the top of it is in the right order
        int[] firstHalf = splitSet[0];

        int[] firstHalfBubbleSorted = halfBubbleSort(firstHalf);
        System.out.println(toString(firstHalfBubbleSorted));



        // 3.1 cut this in half and insertion sort the first half of the first half array.
        int[][] firstHalfSplit = split(firstHalfBubbleSorted);

        // We need to insertion sort this.
        int[] firstQuarter = firstHalfSplit[0];
        insertionSort(firstQuarter);
        System.out.println(toString(firstQuarter));


        // we have successfully sorted the second quarter of the data already!
        int[] secondQuarter = firstHalfSplit[1];

        // 6. we need to add the first two quarters together.
        int[] firstHalfFinal = combine(firstQuarter,secondQuarter);
        System.out.println(toString(firstHalfFinal));
        // Now we need to look at the other half of the array.


        // 3.1 Meanwhile cut the remaining half in half
        // this leaves us with two quarters of the initial array
        // TODO: Refactor this to use paralellism. We're leaving performance on the table!!


        int[] secondHalf = splitSet[1];
        int[][] splitSecondHalf = split(secondHalf);


        // 4. Bogosort this quarter:
        int[] thirdQuarter = splitSecondHalf[0];
        System.out.println(toString(thirdQuarter));
        bogosort(thirdQuarter);
        System.out.println(toString(thirdQuarter));

        // 5. quicksort this quarter.
        // quicksort is really complicated....
        // I'm skipping this and using bogosort here too. Because bogosort is OP
        int[] fourthQuarter = splitSecondHalf[1];
        System.out.println(toString(fourthQuarter));
        bogosort(fourthQuarter);
        System.out.println(toString(fourthQuarter));


        // 7. Merge sort third and fourth quarters
        int[] secondHalfFinal = mergeSort(thirdQuarter,fourthQuarter);

        // 7. Merge sort first and second half
        int[] finalArray = mergeSort(firstHalfFinal, secondHalfFinal);

        return finalArray;
    }



    public static int[] mergeSort(int[] arr1, int[] arr2){
        // not the real merge sort. Simple merges two arrays while sorting them. The real merge sort is scary.
        // instead we're relying on the fact that we know that our arrays going in are sorted. Merging sorted arrays is
        // easy!

        int[] mergedArray = new int[arr1.length + arr2.length];

        int indexArr1 = 0;
        int indexArr2 = 0;

        for (int i = 0; i < mergedArray.length; i++ ) {
            if (indexArr1 >= arr1.length && indexArr2 < arr2.length) {
                mergedArray[i] = arr2[indexArr2];
                indexArr2 ++;
            } else if (indexArr2 >= arr2.length && indexArr1 < arr1.length) {
                mergedArray[i] = arr1[indexArr1];
                indexArr1 ++;
            } else if (indexArr1 < arr1.length && indexArr2 < arr2.length && arr1[indexArr1] < arr2[indexArr2]) {
                mergedArray[i] = arr1[indexArr1];
                indexArr1 ++;
            } else if (indexArr2 < arr2.length) {
                mergedArray[i] = arr2[indexArr2];
                indexArr2 ++;
            }
        }

        return mergedArray;
    }

    public static int[] bogosort(int[] array) {

        boolean sorting = true;
        while (sorting) {
        // check if it is sorted
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] < array[i]) {
                    if (i == array.length - 1) {
                        sorting = false;
                    }
                } else {
                break;
                }
            }

            if (sorting) {
                shuffle(array);
            }
        }

        return array;
    }


    private static int[] halfBubbleSort(int[] array){
        /// returns an array where the top half of the array are in the right order as a result of bubble sorting


        boolean sorting = true;
        while (sorting) {
            // check if sorted enough
            for (int j = array.length/2; j < array.length; j ++) {
                if (array[j-1] < array [j]) {
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
                    if (array[i] > array[i + 1]) {
                        int itemAtI = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = itemAtI;
                    }
                }

            }
        }
        return array;

    }


    public static int[] insertionSort(int[] array) {

        int i = 1;
        while (i < array.length) {
            int j = i;
            while (j > 0 && array[j-1] > array[j]) {
                int itemAtJ = array[j];
                array[j] = array[j - 1];
                array[j - 1] = itemAtJ;
                j = j-i;
            }
            i ++;
        }

        return array;
    }

    private static int[] shuffle(int[] array){
        // probably terrible implementation of the fisher yates shuffle algorithm

        int max = array.length;
        int element;
        int index;


        // performance might be able to be increased by tracking and checking against moved items.
        // either way this shuffles the array.
        while (max > 0) {
            // reduce this to avoid index out of bounds
            max --;
            // find a value between 0 and the max value
            Double almostIndex = Math.floor(Math.random() * max);
            index = almostIndex.intValue();

            // find the item at the last place in the array
            element = array[max];
            // replace the item at the last place in the array with the item at the randomly selected index
            array[max] = array[index];
            // replace the item at the index with the item at the last place in the array.
            array[index] = element;
        }

        return array;
    }

    private static int[] combine(int[] arr1, int[] arr2){
        int[] newArray = new int[arr1.length + arr2.length];

        for (int i = 0; i < newArray.length; i++) {
            if (i < arr1.length) {
                newArray[i] = arr1[i];
            } else  {
                newArray[i] = arr2[i - arr1.length];
            }
        }

        return newArray;
    }

    private static String toString(int[] array) {
        String string = "[";

        for (int i=0;i<array.length;i++){
            if (i == array.length -1) {

                string = string + array[i] + "]";
            } else {
                string = string + array[i] + ", ";

            }
        }

        return string;
    }
}
