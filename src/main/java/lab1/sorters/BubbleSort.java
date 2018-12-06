package lab1.sorters;

/**
 * Super class for all classes which use {@link #swap(int[], int, int)}
 *
 * @author Bohdan Ishchenko
 */
public abstract class BubbleSort {
    protected void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }
}
