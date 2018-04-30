package com.company;


import static com.company.VarietySort.varietySort;

public class Main {

    public static void main(String[] args) {

        // inspired by
        // https://i.redd.it/xmh10hvt4lr01.png

        Comparable[] testSet = {1,10,12,3,8,11,4,9,13,16,6,2,7,15,14,5};
        System.out.println(ArrayUtils.toString(testSet));

        System.out.print("Using varietySort(): ");
        System.out.println(ArrayUtils.toString(varietySort(testSet)));

    }



































}
