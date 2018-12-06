package lab1.sorters;

@SorterClass(name = "Quick sort")
public class QuickSort
        extends BubbleSort
        implements ArraySorter {

    @Sorter
    @Override
    public void sort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

    private void qSort(int[] array, int start, int end) {
        if (array == null || array.length == 0)
            return;
        if (start >= end)
            return;
        var i = start;
        var j = end;
        var cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                swap(array, i, j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        qSort(array, start, cur);
        qSort(array,cur + 1, end);
    }
}
