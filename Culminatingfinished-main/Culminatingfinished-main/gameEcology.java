/**
 * This class stores static environment values used in a climate simulation game.
 * These include global temperature limits, photosynthesis conditionals,
 * and CO2 emissions/removal from different gas plants.
 *
 * @author Jayden&Ari
 * @version 1.0
 */
public class gameEcology {

    /** The base global average temperature in C. */
    public static final double BASE_TEMP = 14.0;

    /** temperature increase in C per megatonne of CO2 added. */
    public static final double TEMP_INCREASE_PER_MT = 0.00005;

    /** Temperature decrease in C per megatonne of CO2 removed. */
    public static final double TEMP_DECREASE_PER_MT = 0.00002;

    /** Temperature (C) where photosynthesis starts dying (stage 1). */
    public static final double PHOTOSYN_DEATH_TEMP1 = 40.0;

    /** Temperature (C) where photosynthesis dies faster (stage 2). */
    public static final double PHOTOSYN_DEATH_TEMP2 = 45.0;

    /** Years of delay before photosynthesis death begins. */
    public static final int PHOTOSYN_DELAY_YEARS = 10;

    /** Annual death rate (%) of photosynthesis in stage 1. */
    public static final double PHOTOSYN_DEATH_RATE1 = 1.0;

    /** Annual death rate (%) of photosynthesis in stage 2. */
    public static final double PHOTOSYN_DEATH_RATE2 = 2.0;

    /** Temperature (C) below which photosynthesis can regrow. */
    public static final double PHOTOSYN_REGROW_TEMP = 35.0;

    /** Annual regrowth rate (%) of photosynthesis. */
    public static final double PHOTOSYN_REGROW_RATE = 1.5;

    /** Maximum temperature (C) in the simulation. */
    public static final double MAX_TEMP = 50.0;

    /** CO2 emissions (MT) per amount of coal energy used. */
    public static final double COAL_CO2 = 2.3;

    /** CO2 emissions (MT) per amount of natural gas energy used. */
    public static final double GAS_CO2 = 1.8;

    /** CO2 emissions (MT) per amount of nuclear energy used. */
    public static final double NUCLEAR_CO2 = 0.0;

    /** CO₂ emissions (MT) per unit of green energy used. */
    public static final double GREEN_CO2 = 0.0;

    /** CO2 removed (MT) by photosynthesis. */
    public static final double PHOTOSYN_CO2 = -0.5;
}

