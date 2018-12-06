package lab1.sorters;

import java.util.Arrays;

@SorterClass(name = "Java sort")
public class JavaSort implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
