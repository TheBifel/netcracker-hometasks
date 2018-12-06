package lab1.sorters;

public class MergeQuickSortTest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new QuickSort()));
    }
}
