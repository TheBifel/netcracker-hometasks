package com.bifel.labs.output;

import com.bifel.labs.output.excel.Excel;
import com.bifel.labs.output.result.ResultTable;

import java.util.List;

public class Output {

    private final List<ResultTable> data;

    public Output(List<ResultTable> data) {
        this.data = data;
    }

    public void generateExcel(String fileName) {
        var excel = new Excel();
        for (ResultTable table : data) {
            var sheet = excel.createSheet(table.getName());
            var bounds = excel.createTable(sheet, table, 2, 2);
            excel.createGraph(sheet, bounds, 10, 0, 35, 20);
        }
        excel.save(fileName);
    }

    public void print() {
        for (ResultTable table : data) {
            System.out.println("Table");
            for (int i = 0; i < table.size(); i++) {
                System.out.println(table.get(i));
            }
        }
    }
}
