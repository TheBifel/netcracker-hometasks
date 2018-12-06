package lab1.sorters;

import lab1.sorters.help.Sorter;
import lab1.sorters.help.SorterClass;

import java.util.Arrays;

@SorterClass(name = "Java sort")
public class JavaSort implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
