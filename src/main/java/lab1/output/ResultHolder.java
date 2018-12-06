package lab1.output;

/**
 * Class used to holding result of analysing
 *
 * @author Bohdan Ishchenko
 */
public class ResultHolder {
    private int time;
    private String sorterName;
    private String fillerName;

    public int getTime() {
        return time;
    }

    public String getSorterName() {
        return sorterName;
    }

    public String getFillerName() {
        return fillerName;
    }

    public ResultHolder(int time, String sorterName, String fillerName) {
        this.time = time;
        this.sorterName = sorterName;
        this.fillerName = fillerName;
    }

    @Override
    public String toString() {
        return ResultFormatter.defaultFormat().format(time, sorterName, fillerName);
    }

    public String[] toArray() {
        return new String[]{
                String.valueOf(time),
                sorterName,
                fillerName,
        };
    }

    public String format(ResultFormatter formatter) {
        return formatter.format(time, sorterName, fillerName);
    }

    /**
     * Functional interface for custom and pretty result formatting
     */
    @FunctionalInterface
    public interface ResultFormatter {

        String format(int time, String sorterName, String fillerName);

        static ResultFormatter defaultFormat() {
            return (time, sorterName, fillerName) ->
                    String.format("Sorter - %28s | %-25s - filler | time = %10d", sorterName, fillerName, time);
        }
    }
}
