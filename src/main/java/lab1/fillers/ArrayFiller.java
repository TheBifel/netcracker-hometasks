package lab1.fillers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class used to fill arrays
 *
 * @author Bohdan Ishchenko
 */
@FillerClass
public abstract class ArrayFiller {

    /**
     * @param array is a array which will be reversed
     */
    private static void reverse(int[] array) {
        int i = 0;

        for (int j = array.length - 1; j > i; i++) {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    /**
     * Method fills array just randomly
     *
     * @param arr array to fill
     * @param min minimum random number
     * @param max maximum random number
     */
    @Filler(name = "Simple filler")
    public static void simpleFiller(int[] arr, int min, int max) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
    }

    /**
     * Method fills array just randomly and then sort it from low to high
     *
     * @param arr array to fill
     * @param min minimum random number
     * @param max maximum random number
     */
    @Filler(name = "Sorted filler")
    public static void sortedFiller(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
    }

    /**
     * Method fills array just randomly and then sort it from high to low
     *
     * @param arr array to fill
     * @param min minimum random number
     * @param max maximum random number
     */
    @Filler(name = "Reverse sorted filler")
    public static void reverseSortedFiller(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
        reverse(arr);
    }

    /**
     * Method fills array randomly and then sort it from low to high
     * and then sets last random number
     *
     * @param arr array to fill
     * @param min minimum random number
     * @param max maximum random number
     */
    @Filler(name = "Sorted filler X")
    public static void sortedFillerX(int[] arr, int min, int max) {
        simpleFiller(arr, min, max);
        Arrays.sort(arr);
        arr[arr.length - 1] = ThreadLocalRandom.current().nextInt(min, max);
    }

}
