package lab1.sorters;

public class MergeJavaSortTest extends SortTest {

    @Override
    public void sort(int[] array) {
        selectSorter(array, new MergeSort(new JavaSort()));
    }
}