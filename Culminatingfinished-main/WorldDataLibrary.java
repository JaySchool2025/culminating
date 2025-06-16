public class WorldDataLibrary {
    public static final int MAX_COAL = 40;
    public static final int MAX_GAS = 50;
    public static final int MAX_NUCLEAR = 30;
    public static final int MAX_GREEN = 100;
    public static final int MAX_PHOTOSYN = 10000;

    
    public static int bounds(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
