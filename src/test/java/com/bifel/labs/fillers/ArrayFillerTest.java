package com.bifel.labs.fillers;

import com.bifel.labs.fillers.ArrayFiller;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayFillerTest {
    private int[] array;

    @Before
    public void init() {
        array = new int[100];
    }

    @Test
    public void simpleFiller() {
        ArrayFiller.simpleFiller(array, 1, 20);
        for (int i = 1; i < array.length; i++) {
            assertNotEquals(array[i], 0);
        }
    }

    @Test
    public void reverseSortedFiller() {
        ArrayFiller.reverseSortedFiller(array, 1, 20);
        for (int i = 1; i < array.length; i++) {
            assertNotEquals(array[i], 0);
        }
    }

    @Test
    public void sortedFiller() {
        ArrayFiller.sortedFiller(array, 1, 20);
        for (int i = 1; i < array.length; i++) {
            assertNotEquals(array[i], 0);
        }
    }

    @Test
    public void sortedFillerX() {
        ArrayFiller.sortedFillerX(array, 1, 20);
        for (int i = 1; i < array.length; i++) {
            assertNotEquals(array[i], 0);
        }
    }

    @Test(expected = NullPointerException.class)
    public void sortedFillerXWithNull() {
        ArrayFiller.sortedFillerX(null, 1, 20);
    }

    @Test(expected = NullPointerException.class)
    public void simpleFillerWithNull() {
        ArrayFiller.simpleFiller(null, 1, 20);
    }

    @Test(expected = NullPointerException.class)
    public void reverseSortedFillerWithNull() {
        ArrayFiller.reverseSortedFiller(null, 1, 20);
    }

    @Test(expected = NullPointerException.class)
    public void sortedFillerWithNull() {
        ArrayFiller.sortedFiller(null, 1, 20);
    }
}