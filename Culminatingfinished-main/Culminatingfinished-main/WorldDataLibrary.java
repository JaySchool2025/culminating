/**
 * class that stores statics related to the maximum allowed
 * numbers of different types of power plants and provides  functions
 * for keeping values within bounds.
 */
public class WorldDataLibrary {

    /** Maximum number of coal plants allowed. */
    public static int MAX_COAL = 40;

    /** Maximum number of gas plants allowed. */
    public static int MAX_GAS = 50;

    /** Maximum number of nuclear plants allowed. */
    public static int MAX_NUCLEAR = 30;

    /** Maximum number of green plants allowed. */
    public static int MAX_GREEN = 100;

    /** Maximum number of photosynthesizing (PS) plants allowed. */
    public static int MAX_PHOTOSYN = 10000;

    /**
     * checks a value within a specified range.
     *
     * @param value the value to bound
     * @param min the minimum allowable value
     * @param max the maximum allowable value
     * @return the value kept between min and max
     */
    public static int bounds(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
