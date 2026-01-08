public class EagerLoading {
    private static final EagerLoading instance = new EagerLoading();

    // private constructor
    private EagerLoading() {
        // private constructor to prevent instantiation
    }

    public static EagerLoading getInstance() {
        return instance;
    }
}