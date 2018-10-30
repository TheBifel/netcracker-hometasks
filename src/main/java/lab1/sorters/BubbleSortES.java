package lab1.sorters;

public class BubbleSortES implements Sorter {

    private static BubbleSortES INSTANCE = new BubbleSortES();

    public static BubbleSortES getInstance() {
        return INSTANCE;
    }

    private BubbleSortES() {}

    @Override
    public String getName() {
        return "BubbleSortES";
    }

    @Override
    public void sort(int[] array) {

        for (int i = array.length - 1; i >= 0; i--) {
            var isReady = true;

            for (int j = array.length - 2; j >= array.length - i - 1; j--) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isReady = false;
                }
            }

            if (isReady) {
                return;
            }
        }
    }
}
