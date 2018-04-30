package com.company;

public class FisherYatesShuffle {
    public static void shuffle(Comparable[] array){
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
}
