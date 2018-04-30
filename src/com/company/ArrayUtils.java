package com.company;

public class ArrayUtils {
    public static Comparable[] combine(Comparable[] arr1, Comparable[] arr2){
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

    public static String toString(Comparable[] array) {
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

    public static Comparable[][] split(Comparable[] array) {

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

    public static boolean stringCompare(String one, String two){
        char[] arr1 = one.toCharArray();
        char[] arr2 = two.toCharArray();

        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }

        return true;


    }

    public static void copyArray(Comparable[] copyFromArray, Comparable[] copyToArray){
        for (int i = 0; i < copyFromArray.length; i++){
            copyToArray[i] = copyFromArray[i];
        }
    }
}
