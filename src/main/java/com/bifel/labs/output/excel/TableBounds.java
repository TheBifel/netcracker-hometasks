package com.bifel.labs.output.excel;

public class TableBounds {

    private int bodyRow1;
    private int bodyCol1;
    private int bodyRow2;
    private int bodyCol2;
    private int headerRow1;
    private int headerCol1;
    private int headerRow2;
    private int headerCol2;
    private int sideHeaderRow1;
    private int sideHeaderCol1;
    private int sideHeaderRow2;
    private int sideHeaderCol2;

    public TableBounds(int row1, int col1, int row2, int col2) {
        this.bodyRow1 = row1;
        this.bodyCol1 = col1;
        this.bodyRow2 = row2;
        this.bodyCol2 = col2;
    }

    public TableBounds setHeader(int row1, int col1, int row2, int col2) {
        headerRow1 = row1;
        headerCol1 = col1;
        headerRow2 = row2;
        headerCol2 = col2;

        return this;
    }

    public TableBounds setSideHeader(int row1, int col1, int row2, int col2) {
        sideHeaderRow1 = row1;
        sideHeaderCol1 = col1;
        sideHeaderRow2 = row2;
        sideHeaderCol2 = col2;

        return this;
    }

    public int[] getSideHeader() {
        return new int[]{
                sideHeaderRow1,
                sideHeaderCol1,
                sideHeaderRow2,
                sideHeaderCol2
        };
    }

    public int[] getHeader() {
        return new int[]{
                headerRow1,
                headerCol1,
                headerRow2,
                headerCol2
        };
    }

    public int[] getBody() {
        return new int[]{
                bodyRow1,
                bodyCol1,
                bodyRow2,
                bodyCol2
        };
    }

    public int getBodyRow1() {
        return bodyRow1;
    }

    public int getBodyCol1() {
        return bodyCol1;
    }

    public int getBodyRow2() {
        return bodyRow2;
    }

    public int getBodyCol2() {
        return bodyCol2;
    }

    public int getHeaderRow1() {
        return headerRow1;
    }

    public int getHeaderCol1() {
        return headerCol1;
    }

    public int getHeaderRow2() {
        return headerRow2;
    }

    public int getHeaderCol2() {
        return headerCol2;
    }

    public int getSideHeaderRow1() {
        return sideHeaderRow1;
    }

    public int getSideHeaderCol1() {
        return sideHeaderCol1;
    }

    public int getSideHeaderRow2() {
        return sideHeaderRow2;
    }

    public int getSideHeaderCol2() {
        return sideHeaderCol2;
    }
}
