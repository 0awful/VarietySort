package com.company;

public class VarietySort {
    public static Comparable[] varietySort(Comparable[] array){
        // 1. cut the list in half.
        Comparable[][] splitSet =  ArrayUtils.split(array);

        // 2. Bubble sort the first half until the top of it is in the right order
        Comparable[] firstHalf = splitSet[0];

        Comparable[] firstHalfBubbleSorted = BubbleSort.halfSort(firstHalf);

        // 3.1 cut this in half and insertion sort the first half of the first half array.
        Comparable[][] firstHalfSplit = ArrayUtils.split(firstHalfBubbleSorted);

        // We need to insertion sort this.
        Comparable[] firstQuarter = firstHalfSplit[0];
        InsertionSort.sort(firstQuarter);

        // we have successfully sorted the second quarter of the data already!
        Comparable[] secondQuarter = firstHalfSplit[1];

        // 6. we need to add the first two quarters together.
        Comparable[] firstHalfFinal = ArrayUtils.combine(firstQuarter,secondQuarter);
        // Now we need to look at the other half of the array.


        // 3.1 Meanwhile cut the remaining half in half
        // this leaves us with two quarters of the initial array
        // TODO: Refactor this to use paralellism. We're leaving performance on the table!!


        Comparable[] secondHalf = splitSet[1];
        Comparable[][] splitSecondHalf = ArrayUtils.split(secondHalf);


        // 4. Bogosort this quarter:
        Comparable[] thirdQuarter = splitSecondHalf[0];
        BogoSort.sort(thirdQuarter);

        // 5. quicksort this quarter.
        // quicksort is really complicated....
        // I'm skipping this and using bogosort here too. Because bogosort is OP
        Comparable[] fourthQuarter = splitSecondHalf[1];
        QuickSort.sort(fourthQuarter, 0, fourthQuarter.length -1);


        // 7. Merge sort third and fourth quarters
        Comparable[] combinedThirdAndFourth = ArrayUtils.combine(thirdQuarter, fourthQuarter);
        Comparable[] tempArray = new Comparable[combinedThirdAndFourth.length];
        MergeSort.sort(combinedThirdAndFourth, tempArray);

        // 7. Merge sort first and second half

        Comparable[] combinedWholeThing = ArrayUtils.combine(firstHalfFinal, combinedThirdAndFourth);
        Comparable[] tempFinal = new Comparable[combinedWholeThing.length];
        MergeSort.sort(combinedWholeThing, tempFinal);
        return combinedWholeThing;
    }
}
