package lab1.sorters;


public class BubbleSortSE implements Sorter {

    private static BubbleSortSE INSTANCE = new BubbleSortSE();

    private BubbleSortSE() {}

    public static BubbleSortSE getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "BubbleSortSE";
    }

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
