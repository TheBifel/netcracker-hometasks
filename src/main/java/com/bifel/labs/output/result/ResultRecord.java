package com.bifel.labs.output.result;

/**
 * Class used to holding result of one test
 *
 * @author Bohdan Ishchenko
 */
public class ResultRecord {
    private final int time;
    private final int numberOfElements;
    private final String sorterName;
    private final String fillerName;

    public int getTime() {
        return time;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public String getSorterName() {
        return sorterName;
    }

    public String getFillerName() {
        return fillerName;
    }

    public ResultRecord(int time, int numberOfElements, String sorterName, String fillerName) {
        this.time = time;
        this.numberOfElements = numberOfElements;
        this.sorterName = sorterName;
        this.fillerName = fillerName;
    }

    @Override
    public String toString() {
        return ResultFormatter.defaultFormat().format(time, numberOfElements, sorterName, fillerName);
    }

    public String[] toArray() {
        return new String[]{
                String.valueOf(time),
                String.valueOf(numberOfElements),
                sorterName,
                fillerName,
        };
    }

    public String format(ResultFormatter formatter) {
        return formatter.format(time, numberOfElements, sorterName, fillerName);
    }

    /**
     * Functional interface for custom and pretty result formatting
     */
    @FunctionalInterface
    public interface ResultFormatter {

        String format(int time, int numberOfElements, String sorterName, String fillerName);

        static ResultFormatter defaultFormat() {
            return (time, numberOfElements, sorterName, fillerName) ->
                    String.format("Sorter - %28s | %-25s - filler | time = %10d | elements = %10d", sorterName, fillerName, time, numberOfElements);
        }
    }
}
