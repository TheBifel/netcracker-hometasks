package lab1.output;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Output {

    public static final Comparator<ResultHolder> SORT_BY_TIME = Comparator.comparingInt(ResultHolder::getTime);
    public static final Comparator<ResultHolder> SORT_BY_FILLER_NAME = Comparator.comparing(ResultHolder::getFillerName);
    public static final Comparator<ResultHolder> SORT_BY_SORTER_NAME = Comparator.comparing(ResultHolder::getSorterName);


    public static void createExcel(String fileName, ResultHolder[] results) {
        try {
            var workbook = new XSSFWorkbook();
            var spreadsheet = workbook.createSheet("results");

            for (var i = 0; i < results.length; i++) {
                var row = spreadsheet.createRow(i);
                var resultArray = results[i].toArray();
                for (var j = 0; j < resultArray.length; j++) {
                    row.createCell(j).setCellValue(resultArray[j]);
                }
            }

            var out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void print(ResultHolder[] results) {
        for (ResultHolder result : results) {
            System.out.println(result);
        }
    }

    public static void sort(ResultHolder[] data, Comparator<ResultHolder> comparator) {
        Arrays.sort(data, comparator);
    }
}
