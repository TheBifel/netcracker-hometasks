package com.bifel.labs.sorters;


public class BubbleSortSETest extends SortTest {

    @Override
    public void sort(int[] array) {
        selectSorter(array, new BubbleSortSE());
    }
}