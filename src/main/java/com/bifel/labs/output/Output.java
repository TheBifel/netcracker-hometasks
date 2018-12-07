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
            var bounds = excel.createTable(sheet, table);
            excel.createGraph(sheet, bounds);
        }
        excel.save(fileName);
    }

    public void print() {
        for (ResultTable sheet : data) {
            System.out.println("Sheet");
            for (int i = 0; i < sheet.size(); i++) {
                System.out.println(sheet.get(i));
            }
        }
    }
}
