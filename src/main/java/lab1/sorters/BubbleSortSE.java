package lab1.sorters;

@SorterClass(name = "Bubble Sort SE")
public class BubbleSortSE
        extends BubbleSort
        implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            var isReady = true;

            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                    isReady = false;
                }
            }

            if (isReady) {
                return;
            }
        }
    }
}
