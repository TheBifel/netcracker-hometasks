package lab1.sorters;

import lab1.sorters.help.SortType;
import lab1.sorters.help.Sorter;
import lab1.sorters.help.SorterClass;

@SorterClass(name = "Merge sort", type = SortType.WITH_PARAM)
public class MergeSort implements ArraySorter {

    private final ArraySorter sorter;

    public MergeSort(ArraySorter sorter) {
        this.sorter = sorter;
    }

    @Sorter
    @Override
    public void sort(int[] array) {
        divide(array);
    }

    public void divide(int[] array) {
        var m = array.length / 2;
        var arr1 = new int[m];
        var arr2 = new int[array.length - m];
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, m, arr2, 0, arr2.length);
        sorter.sort(arr1);
        sorter.sort(arr2);
        merge(array, arr1, arr2);
    }

    public void merge(int[] array, int[] arr1, int[] arr2) {
        var j = 0;
        var k = 0;

        for (int i = 0; i < array.length; i++) {
            if (j == arr1.length) {
                array[i] = arr2[k++];
            } else if (k == arr2.length) {
                array[i] = arr1[j++];
            } else if (arr1[j] > arr2[k]) {
                array[i] = arr2[k++];
            } else {
                array[i] = arr1[j++];
            }
        }

    }


}
