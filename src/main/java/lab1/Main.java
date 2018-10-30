package lab1;

import lab1.analyzer.Analyzer;
import lab1.fillers.*;
import lab1.output.Output;
import lab1.sorters.*;


public class Main {

    public static void main(String[] args) {
        var analyzer = new Analyzer(
                new Filler[]{
                        SimpleFiller.getInstance(),
                        SortedFiller.getInstance(),
                        SortedFillerX.getInstance(),
                        ReverseSortedFiller.getInstance(),
                }, new Sorter[]{
                        BubbleSortES.getInstance(),
                        BubbleSortSE.getInstance(),
                        JavaSort.getInstance(),
                        QuickSort.getInstance(),
                        new MergeSort(BubbleSortES.getInstance()),
                        new MergeSort(BubbleSortSE.getInstance()),
                        new MergeSort(JavaSort.getInstance()),
                        new MergeSort(QuickSort.getInstance())
                });


        var data = analyzer.analyze(1_000, false, 10);
        Output.sort(data, Output.SORT_BY_TIME);
        Output.print(data);
//        Output.createExcel("results.xlsx", data);
    }
}
