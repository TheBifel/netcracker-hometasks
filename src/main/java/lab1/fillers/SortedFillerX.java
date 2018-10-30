package lab1.fillers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortedFillerX implements Filler {

    private static SortedFillerX INSTANCE = new SortedFillerX();

    private SortedFillerX() {}

    public static SortedFillerX getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "SortedFillerX";
    }

    @Override
    public void fill(int[] arr, int min, int max){
        defaultFill(arr, min, max);
        Arrays.sort(arr);
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }
}
