package lab1.sorters;

public abstract class BubbleSort {
    protected void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }
}
