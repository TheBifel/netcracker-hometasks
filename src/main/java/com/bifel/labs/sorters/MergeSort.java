package com.bifel.labs.sorters;

import com.bifel.labs.sorters.help.SorterType;
import com.bifel.labs.sorters.help.Sorter;
import com.bifel.labs.sorters.help.SorterClass;

/**
 * Class class which holds a child of {@link ArraySorter} marked as {@link SorterType#DEFAULT}
 *
 * @author Bohdan Ishchenko
 */
@SorterClass(name = "Merge sort", type = SorterType.WITH_PARAM)
public class MergeSort implements ArraySorter {

    /**
     * {@link ArraySorter} marked as {@link SorterType#DEFAULT}
     */
    private final ArraySorter sorter;

    /**
     *
     * @param sorter marked as {@link SorterType#DEFAULT}
     */
    public MergeSort(ArraySorter sorter) {
        this.sorter = sorter;
    }

    @Sorter
    @Override
    public void sort(int[] array) {
        divide(array);
    }

    /**
     * Divides array and sort both part using {@link #sorter#sort(int[])} and then merge it
     * @param array to sort
     */
    public void divide(int[] array) {
        var m = array.length / 2;
        var arr1 = new int[m];
        var arr2 = new int[array.length - m];
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, m, arr2, 0, arr2.length);
        sorter.sort(arr1);
        sorter.sort(arr2);
        merge(array, arr1, arr2);
    }

    /**
     * Method merge first and second part of array
     * @param array result
     * @param arr1 first part
     * @param arr2 second part
     */
    public void merge(int[] array, int[] arr1, int[] arr2) {
        var j = 0;
        var k = 0;

        for (int i = 0; i < array.length; i++) {
            if (j == arr1.length) {
                array[i] = arr2[k++];
            } else if (k == arr2.length) {
                array[i] = arr1[j++];
            } else if (arr1[j] > arr2[k]) {
                array[i] = arr2[k++];
            } else {
                array[i] = arr1[j++];
            }
        }

    }


}
