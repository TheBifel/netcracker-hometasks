package com.bifel.labs.sorters;

import com.bifel.labs.sorters.help.Sorter;
import com.bifel.labs.sorters.help.SorterClass;
import com.bifel.labs.sorters.help.SorterType;

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
     *
     * @param array to sort
     */
    public void divide(int[] array) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int numberOfThreads = availableProcessors < 2 ? 2 : availableProcessors;
        var spittedArrays = splitArray(numberOfThreads, array);
        sortInThreads(numberOfThreads, spittedArrays);
        deepMerge(spittedArrays, array);
    }

    private void deepMerge(int[][] arrays, int[] resultArray) {
        int[] temp;
        int resultNumberOfArrays;
        for (int arrayLen = arrays.length / 2; arrayLen > 1; arrayLen /= 2) {
            resultNumberOfArrays = -1;
            for (int i = 0; i < arrayLen + 1; i += 2) {
                temp = new int[arrays[i].length + arrays[i + 1].length];
                merge(temp, arrays[i], arrays[i + 1]);
                arrays[i] = null;
                arrays[i + 1] = null;
                arrays[++resultNumberOfArrays] = temp;
            }
        }

        merge(resultArray, arrays[0], arrays[1]);
    }

    private int[][] splitArray(int numberOfThreads, int[] array) {
        final int[][] spittedArrays = new int[numberOfThreads][];

        var len = array.length / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            spittedArrays[i] = new int[len];
            if (i == numberOfThreads - 1 && array.length % numberOfThreads != 0) {
                spittedArrays[i] = new int[array.length - len * i];
            }
            System.arraycopy(array, i * len, spittedArrays[i], 0, spittedArrays[i].length);
        }
        return spittedArrays;
    }

    private void sortInThreads(int numberOfThreads, int[][] spittedArrays) {
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            var tempForAnonymousClassRules = spittedArrays[i];
            threads[i] = new Thread(() -> sorter.sort(tempForAnonymousClassRules));
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                threads[i] = null; // Help GC
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method merge first and second part of array
     *
     * @param array result
     * @param arr1  first part
     * @param arr2  second part
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
