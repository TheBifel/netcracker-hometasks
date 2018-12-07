package com.bifel.labs.sorters;

import com.bifel.labs.sorters.ArraySorter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public abstract class SortTest {

    @Test
    public void baseSort() {
        int[] array = {4, 66, 7, 6, 67, 9, 6797, 34, 53, 43, 53, 465, 34653, 65,};
        int[] result = array.clone();

        sort(array);
        Arrays.sort(result);
        assertArrayEquals(array, result);
    }

    @Test(expected = NullPointerException.class)
    public void sortNull() {
        sort(null);
    }

    protected void selectSorter(int[] array, ArraySorter sorter) {
        sorter.sort(array);
    }

    public abstract void sort(int[] array);

}
