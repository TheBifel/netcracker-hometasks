package lab1.sorters;

public class MergeBubbleSortESTest extends SortTest {
    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new BubbleSortES()));
    }
}
