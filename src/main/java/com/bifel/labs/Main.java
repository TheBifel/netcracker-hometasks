package com.bifel.labs;

import com.bifel.labs.analyzer.Analyzer;
import com.bifel.labs.output.Output;


public class Main {

    public static void main(String[] args) {
        var analyzer = new Analyzer("com.bifel.labs");

        var output = new Output(analyzer.analyze(Analyzer.DEFAULT_ELEMENT_COUNT_FOR_TESTS, false, 10));
        output.print();
        output.generateExcel("results.xlsx");
    }
}
