package lab1.fillers;

import lab1.sorters.QuickSort;

import java.util.Arrays;

public class ReverseSortedFiller implements Filler{

    private static ReverseSortedFiller INSTANCE = new ReverseSortedFiller();

    private ReverseSortedFiller() {}

    public static ReverseSortedFiller getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "ReverseSortedFiller";
    }

    @Override
    public void fill(int[] arr, int min, int max){
        defaultFill(arr, min, max);
        Arrays.sort(arr);
        reverse(arr);
    }
}
