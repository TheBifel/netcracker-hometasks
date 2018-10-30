package lab1.sorters;

import java.util.Arrays;

public class JavaSort implements Sorter {

    private static JavaSort INSTANCE = new JavaSort();

    private JavaSort() {
    }

    public static JavaSort getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "JavaSort";
    }

    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }
}
