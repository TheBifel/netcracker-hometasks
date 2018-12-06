package lab1.sorters;

public class MergeBubbleSortSETest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new BubbleSortSE()));
    }
}
