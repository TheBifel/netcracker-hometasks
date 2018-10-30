package lab1.analyzer;

import lab1.fillers.Filler;
import lab1.output.ResultHolder;
import lab1.sorters.Sorter;

import static java.lang.System.nanoTime;

public class Analyzer {
    private Filler[] fillers;
    private Sorter[] sorters;

    public Analyzer(Filler[] fillers, Sorter[] sorters) {
        this.fillers = fillers;
        this.sorters = sorters;
    }

    public ResultHolder[] analyze(int elementCount, boolean withColdStart, int numberOfTests) {
        var result = new ResultHolder[sorters.length * fillers.length];
        var arr = new int[elementCount];
        var i = 0;
        for (var sorter : sorters) {
            for (var filler : fillers) {
                if (!withColdStart) {
                    prepareSorter(sorter, filler);
                }
                var testStart = nanoTime();
                for (int repeat = 0; repeat < numberOfTests; repeat++) {
                    filler.fill(arr, 0, elementCount);
                    sorter.sort(arr);
                }
                result[i++] = new ResultHolder((int) ((nanoTime() - testStart) / numberOfTests),
                        sorter.getName(),
                        filler.getName());
            }
        }
        return result;
    }

    public ResultHolder[] analyze(int elementCount) {
        return analyze(elementCount, false, 10);
    }

    private void prepareSorter(Sorter sorter, Filler filler) {
        var arr = new int[100];
        for (int i = 0; i < 5; i++) {
            filler.fill(arr, 0, 100);
            sorter.sort(arr);
        }
    }
}

