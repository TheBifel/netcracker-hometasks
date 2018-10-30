package lab1.fillers;

public class SimpleFiller implements Filler {

    private static SimpleFiller INSTANCE = new SimpleFiller();

    private SimpleFiller() {}

    public static SimpleFiller getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "SimpleFiller";
    }

    @Override
    public void fill(int[] arr, int min, int max) {
        defaultFill(arr, min, max);
    }
}
