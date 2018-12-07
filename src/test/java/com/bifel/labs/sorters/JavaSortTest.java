package com.bifel.labs.sorters;


public class JavaSortTest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new JavaSort());

    }
}