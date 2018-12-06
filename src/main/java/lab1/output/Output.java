package lab1.output;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Output {

    public static final Comparator<ResultHolder> SORT_BY_TIME = Comparator.comparingInt(ResultHolder::getTime);
    public static final Comparator<ResultHolder> SORT_BY_FILLER_NAME = Comparator.comparing(ResultHolder::getFillerName);
    public static final Comparator<ResultHolder> SORT_BY_SORTER_NAME = Comparator.comparing(ResultHolder::getSorterName);
    
    private final List<ResultHolder> data;

    public Output(List<ResultHolder> data) {
        this.data = data;
    }

    public void createExcel(String fileName) {
        try {
            var workbook = new XSSFWorkbook();
            var spreadsheet = workbook.createSheet("results");

            for (var i = 0; i < data.size(); i++) {
                var row = spreadsheet.createRow(i);
                var resultArray = data.get(i).toArray();
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

    public void print() {
        for (ResultHolder result : data) {
            System.out.println(result);
        }
    }
    
    public void sort(Comparator<ResultHolder> sortBy){
        data.sort(sortBy);
    }

}
