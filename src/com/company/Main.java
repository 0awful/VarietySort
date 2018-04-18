package com.company;


public class Main {

    public static void main(String[] args) {

        // inspired by
        // https://i.redd.it/xmh10hvt4lr01.png

        Comparable[] testSet = {1,10,12,3,8,11,4,9,13,16,6,2,7,15,14,5};
        System.out.println(toString(testSet));
        Comparable[] finalSet = testCase(testSet);

        System.out.println(toString(finalSet));

        System.out.println("Using varietySort():");
        System.out.println(toString(varietySort(testSet)));

    }


    public static Comparable[] varietySort(Comparable[] array){
        // 1. cut the list in half.
        Comparable[][] splitSet =  split(array);

        // 2. Bubble sort the first half until the top of it is in the right order
        Comparable[] firstHalf = splitSet[0];

        Comparable[] firstHalfBubbleSorted = halfBubbleSort(firstHalf);

        // 3.1 cut this in half and insertion sort the first half of the first half array.
        Comparable[][] firstHalfSplit = split(firstHalfBubbleSorted);

        // We need to insertion sort this.
        Comparable[] firstQuarter = firstHalfSplit[0];
        insertionSort(firstQuarter);

        // we have successfully sorted the second quarter of the data already!
        Comparable[] secondQuarter = firstHalfSplit[1];

        // 6. we need to add the first two quarters together.
        Comparable[] firstHalfFinal = combine(firstQuarter,secondQuarter);
        // Now we need to look at the other half of the array.


        // 3.1 Meanwhile cut the remaining half in half
        // this leaves us with two quarters of the initial array
        // TODO: Refactor this to use paralellism. We're leaving performance on the table!!


        Comparable[] secondHalf = splitSet[1];
        Comparable[][] splitSecondHalf = split(secondHalf);


        // 4. Bogosort this quarter:
        Comparable[] thirdQuarter = splitSecondHalf[0];
        bogosort(thirdQuarter);

        // 5. quicksort this quarter.
        // quicksort is really complicated....
        // I'm skipping this and using bogosort here too. Because bogosort is OP
        Comparable[] fourthQuarter = splitSecondHalf[1];
        hoareQuicksort(fourthQuarter, 0, fourthQuarter.length -1);


        // 7. Merge sort third and fourth quarters
        Comparable[] combinedThirdAndFourth = combine(thirdQuarter, fourthQuarter);
        Comparable[] tempArray = new Comparable[combinedThirdAndFourth.length];
        bottomUpMergeSort(combinedThirdAndFourth, tempArray);

        // 7. Merge sort first and second half

        Comparable[] combinedWholeThing = combine(firstHalfFinal, combinedThirdAndFourth);
        Comparable[] tempFinal = new Comparable[combinedWholeThing.length];
        bottomUpMergeSort(combinedWholeThing, tempFinal);
        return combinedWholeThing;
    }


    public static Comparable[] testCase(Comparable[] array){
        // 1. cut the list in half.
        Comparable[][] splitSet =  split(array);

        // 2. Bubble sort the first half until the top of it is in the right order
        Comparable[] firstHalf = splitSet[0];

        Comparable[] firstHalfBubbleSorted = halfBubbleSort(firstHalf);
        String halfBubbleSorted = "[1, 3, 8, 4, 9, 10, 11, 12]";
        System.out.println(toString(firstHalfBubbleSorted) + " should be " + halfBubbleSorted);
        System.out.println(stringCompare(toString(firstHalfBubbleSorted), halfBubbleSorted));



        // 3.1 cut this in half and insertion sort the first half of the first half array.
        Comparable[][] firstHalfSplit = split(firstHalfBubbleSorted);

        // We need to insertion sort this.
        Comparable[] firstQuarter = firstHalfSplit[0];
        insertionSort(firstQuarter);
        String firstQuarterInsertionSorted = "[1, 3, 4, 8]";
        System.out.println(toString(firstQuarter) + " should be " + firstQuarterInsertionSorted);
        System.out.println(stringCompare(toString(firstQuarter), firstQuarterInsertionSorted));


        // we have successfully sorted the second quarter of the data already!
        Comparable[] secondQuarter = firstHalfSplit[1];

        // 6. we need to add the first two quarters together.
        Comparable[] firstHalfFinal = combine(firstQuarter,secondQuarter);
        String firstHalfSorted = "[1, 3, 4, 8, 9, 10, 11, 12]";
        System.out.println(toString(firstHalfFinal) + " should be " + firstHalfSorted);
        System.out.println(stringCompare(toString(firstHalfFinal), firstHalfSorted));
        // Now we need to look at the other half of the array.


        // 3.1 Meanwhile cut the remaining half in half
        // this leaves us with two quarters of the initial array
        // TODO: Refactor this to use paralellism. We're leaving performance on the table!!


        Comparable[] secondHalf = splitSet[1];
        Comparable[][] splitSecondHalf = split(secondHalf);


        // 4. Bogosort this quarter:
        Comparable[] thirdQuarter = splitSecondHalf[0];
        bogosort(thirdQuarter);
        String bogoOutput = "[2, 6, 13, 16]";
        System.out.println(toString(thirdQuarter) + " should be " + bogoOutput);
        System.out.println(stringCompare(toString(thirdQuarter), bogoOutput));

        // 5. quicksort this quarter.
        // quicksort is really complicated....
        // I'm skipping this and using bogosort here too. Because bogosort is OP
        Comparable[] fourthQuarter = splitSecondHalf[1];
        hoareQuicksort(fourthQuarter, 0, fourthQuarter.length -1);
        String fourthQuarterOutput = "[5, 7, 14, 15]";
        System.out.println(toString(fourthQuarter) + " should be " + fourthQuarterOutput);
        System.out.println(stringCompare(toString(fourthQuarter), fourthQuarterOutput));



        // 7. Merge sort third and fourth quarters
        Comparable[] combinedThirdAndFourth = combine(thirdQuarter, fourthQuarter);
        Comparable[] tempArray = new Comparable[combinedThirdAndFourth.length];
        bottomUpMergeSort(combinedThirdAndFourth, tempArray);
        String secondHalfMerged = "[2, 5, 6, 7, 13, 14, 15, 16]";
        System.out.println(toString(combinedThirdAndFourth) + " should be " + secondHalfMerged);
        System.out.println(stringCompare(toString(combinedThirdAndFourth), secondHalfMerged));

        // 7. Merge sort first and second half

        Comparable[] combinedWholeThing = combine(firstHalfFinal, combinedThirdAndFourth);
        Comparable[] tempFinal = new Comparable[combinedWholeThing.length];
        bottomUpMergeSort(combinedWholeThing, tempFinal);
        System.out.println("Now we merge sort the rest together and get...");
        return combinedWholeThing;
    }



    public static void hoareQuicksort(Comparable[] array, int low, int high) {
        if (low < high) {
            int p = hoarePartition(array, low, high);
            hoareQuicksort(array, low, p);
            hoareQuicksort(array, p+1, high);
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



    public static void bottomUpMergeSort(Comparable[] array, Comparable[] tempArray) {

        // we iterate through the array using fictitious array's of length N
        for (int width = 1; width < array.length; width = 2 * width){

            for (int i = 0; i < array.length; i = i + 2 * width) {

                bottomUpMergeHelper(array, tempArray, i, Math.min(i+width, array.length), Math.min(i+2*width, array.length));

            }

            copyArray(tempArray, array);

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

    private static void copyArray(Comparable[] copyFromArray, Comparable[] copyToArray){
        for (int i = 0; i < copyFromArray.length; i++){
            copyToArray[i] = copyFromArray[i];
        }
    }


    public static Comparable[] bogosort(Comparable[] array) {

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
                shuffle(array);
            }
        }

        return array;
    }

    public static Comparable[] bubbleSort(Comparable[] array ){

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

    private static Comparable[] halfBubbleSort(Comparable[] array){
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


    public static void insertionSort(Comparable[] array) {

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

    private static void shuffle(Comparable[] array){
        // The fisher yates shuffle algorithm

        int max = array.length;
        Comparable element;
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
    }

    private static Comparable[] combine(Comparable[] arr1, Comparable[] arr2){
        Comparable[] newArray = new Comparable[arr1.length + arr2.length];

        for (int i = 0; i < newArray.length; i++) {
            if (i < arr1.length) {
                newArray[i] = arr1[i];
            } else  {
                newArray[i] = arr2[i - arr1.length];
            }
        }

        return newArray;
    }

    private static String toString(Comparable[] array) {
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

    private static boolean stringCompare(String one, String two){
        char[] arr1 = one.toCharArray();
        char[] arr2 = two.toCharArray();

        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;


    }

    private static Comparable[][] split(Comparable[] array) {

        // allocate space for two halves of the array
        Comparable[] firstHalf = new Comparable[array.length/2];
        // this handles odd array lengths ensuring that we always have space for each element
        Comparable[] secondHalf = new Comparable[array.length - firstHalf.length];

        // iterate through and assign elements to the appropriate array.
        for (int i = 0; i< array.length; i++) {
            if (i < firstHalf.length) {
                firstHalf[i] = array[i];
            } else {
                secondHalf[i - firstHalf.length] = array[i];
            }

        }

        Comparable[][] arrayOfArrays = {firstHalf,secondHalf};

        return  arrayOfArrays;
    }
}
