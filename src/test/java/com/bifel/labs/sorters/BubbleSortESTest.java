package com.bifel.labs.sorters;

public class BubbleSortESTest extends SortTest{

    @Override
    public void sort(int[] array) {
        selectSorter(array, new BubbleSortES());
    }
}