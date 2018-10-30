package lab1.fillers;

import java.util.concurrent.ThreadLocalRandom;

public interface Filler {

    void fill(int[] arr, int min, int max);

    String getName();

    default void defaultFill(int[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
    }

    default void reverse(int[] array) {
        int i = 0;

        for(int j = array.length - 1; j > i; i++) {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }
}
