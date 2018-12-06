package lab1.sorters;

public class QuickSortTest extends SortTest {

    @Override
    public void sort(int[] array) {
        selectSorter(array, new QuickSort());
    }
}