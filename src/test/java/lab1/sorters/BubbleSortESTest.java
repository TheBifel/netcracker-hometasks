package lab1.sorters;

public class BubbleSortESTest extends SortTest{

    @Override
    public void sort(int[] array) {
        selectSorter(array, new BubbleSortES());
    }
}