package lab1.sorters;

public interface Sorter {

    void sort(int[] array);

    String getName();

    default void swap(int[] array, int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }
}
