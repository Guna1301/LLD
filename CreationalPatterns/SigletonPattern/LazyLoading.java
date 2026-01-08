public class LazyLoading {
    private static LazyLoading instance;

    // private constructor
    private LazyLoading() {
        // private constructor to prevent instantiation
    }

    public static LazyLoading getInstance() {
        if (instance == null) {
            instance = new LazyLoading();
        }
        return instance;
    }
}