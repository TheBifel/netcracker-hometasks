package lab1.fillers;

import java.util.Arrays;

public class SortedFiller implements Filler {

    private static SortedFiller INSTANCE = new SortedFiller();

    private SortedFiller() {}

    public static SortedFiller getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "SortedFiller";
    }

    @Override
    public void fill(int[] arr, int min, int max){
        defaultFill(arr, min, max);
        Arrays.sort(arr);
    }
}
