package com.bifel.labs.output.excel;

import com.bifel.labs.output.result.ResultRecord;
import com.bifel.labs.output.result.ResultTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;

public class Excel {
    private final XSSFWorkbook workbook;

    public Excel() {
        this.workbook = new XSSFWorkbook();
    }

    public XSSFSheet createSheet(String name) {
        return workbook.createSheet(name);
    }

    public void save(String fileName) {
        try (var out = new FileOutputStream(new File(fileName))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableBounds createTable(XSSFSheet sheet, ResultTable table) {
        var sorterNameToColumn = new HashMap<String, Integer>();
        var elmCountToRow = new HashMap<Integer, XSSFRow>();

        table.sort(Comparator.comparingInt(ResultRecord::getNumberOfElements));
        var headerRow = sheet.createRow(0);
        Cell cell;
        XSSFRow row;

        for (int recIndex = 0, colIndex = 1, rowIndex = 1; recIndex < table.size(); recIndex++) {
            var record = table.get(recIndex);
            var sorterName = record.getSorterName();
            int elementCount = record.getNumberOfElements();

            if (sorterNameToColumn.get(sorterName) == null) {
                cell = headerRow.createCell(colIndex);
                cell.setCellValue(sorterName);
                sorterNameToColumn.put(sorterName, colIndex++);
            }

            if (elmCountToRow.get(elementCount) == null) {
                row = sheet.createRow(rowIndex++);
                cell = row.createCell(0);
                cell.setCellValue(elementCount);
                elmCountToRow.put(elementCount, row);

                cell = row.createCell(sorterNameToColumn.get(sorterName));
                cell.setCellValue(record.getTime());
                continue;
            }

            row = elmCountToRow.get(elementCount);
            cell = row.createCell(sorterNameToColumn.get(sorterName));
            cell.setCellValue(record.getTime());
        }
        return new TableBounds(1, 1, 7, 8)
                .setHeader(0, 1, 0, 7)
                .setSideHeader(1, 0, 7, 0);
    }

    public void createGraph(XSSFSheet sheet, TableBounds bounds) {
        var drawing = sheet.createDrawingPatriarch();
        var anchor = drawing.createAnchor(0, 0, 0, 0, 0, 10, 20, 35);

        var chart = drawing.createChart(anchor);
        var legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.RIGHT);

        var bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("elements");
        var leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("ticks");

        var data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        var x = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(bounds.getSideHeaderRow1(), bounds.getSideHeaderRow2(), bounds.getSideHeaderCol1(), bounds.getSideHeaderCol2()));

        var headRow = sheet.getRow(bounds.getHeaderRow1());
        for (int col = bounds.getBodyCol1(); col <= bounds.getBodyCol2(); col++) {
            var y = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(bounds.getBodyRow1(), bounds.getBodyRow2(), col, col));
            var series1 = (XDDFLineChartData.Series) data.addSeries(x, y);

            series1.setTitle(headRow.getCell(col).getStringCellValue(), null);
            series1.setMarkerStyle(MarkerStyle.NONE);
        }

        chart.plot(data);

    }
}
