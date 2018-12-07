package com.bifel.labs.sorters;

import com.bifel.labs.sorters.help.Sorter;
import com.bifel.labs.sorters.help.SorterClass;

import java.util.Arrays;

/**
 * Class class which contains {@link Arrays#sort(int[])} algorithm
 *
 * @author Bohdan Ishchenko
 */
@SorterClass(name = "Java sort")
public class JavaSort implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
