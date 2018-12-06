package lab1;

import lab1.analyzer.Analyzer;
import lab1.output.Output;


public class Main {

    public static void main(String[] args) {
        var analyzer = new Analyzer("lab1");

        var result = analyzer.analyze(1_000, false, 10);
        Output.sort(result, Output.SORT_BY_TIME);
        Output.print(result);
//        Output.createExcel("results.xlsx", data);
    }
}
