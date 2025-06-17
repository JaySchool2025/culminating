/**
 * This class defines static values related to the economic and environmental systems 
 * in the climate simulation game. It includes the limits, the costs, the energy outputs, 
 * CO2 amounts, temperature penalties, and growth/downturn values.
 * 
 * @author Jayden&Ari
 * @version 1.0
 */

public class gameEconomy {

    /** Maximum amount of coal energy allowed. */
    public static final int MAX_COAL = 40;

    /** Maximum amount of gas energy allowed. */
    public static final int MAX_GAS = 50;

    /** Maximum amount of nuclear energy allowed. */
    public static final int MAX_NUCLEAR = 30;

    /** Maximum amount of green energy allowed. */
    public static final int MAX_GREEN = 100;

    /** Maximum amount of photosynthesis allowed. */
    public static final int MAX_PS = 10000;

    /** Cost of coal energy. */
    public static final long COAL_COST = 250_000;

    /** Cost of gas energy. */
    public static final long GAS_COST = 220_000;

    /** Cost of nuclear energy. */
    public static final long NUCLEAR_COST = 400_000;

    /** Cost of green energy. */
    public static final long GREEN_COST = 300_000;

    /** Cost of photosynthesis. */
    public static final long PS_COST = 20_000;

    /** Amount of energy produced by coal. */
    public static final double COAL_ENERGY = 5.0;

    /** Amount of energy produced by gas. */
    public static final double GAS_ENERGY = 4.5;

    /** Amount of energy produced by nuclear. */
    public static final double NUCLEAR_ENERGY = 7.0;

    /** Amount of energy produced by green sources. */
    public static final double GREEN_ENERGY = 2.5;

    /** Amount of CO2 emitted by coal. */
    public static final double COAL_CO2 = 1.0;

    /** Amount of CO2 emitted by gas. */
    public static final double GAS_CO2 = 0.45;

    /** Amount of CO2 emitted by nuclear. */
    public static final double NUCLEAR_CO2 = 0.02;

    /** Amount of CO2 emitted by green sources. */
    public static final double GREEN_CO2 = 0.0;

    /** Amount of CO2 removed by photosynthesis. */
    public static final double PS_CO2 = -0.25;

    /** Money earned per gigawatt of energy. */
    public static final long MONEY_PER_GW = 100_000;

    /** Temperature increase per megatonne of CO2 added. */
    public static final double TEMP_INCREASE_PER_MT = 0.01;

    /** Temperature decrease per megatonne of CO2 removed. */
    public static final double TEMP_DECREASE_PER_MT = 0.001;

    /** Starting global temperature. */
    public static final double BASE_TEMPERATURE = 14.7;

    /** First penalty temperature limit. */
    public static final double TEMP_PENALTY_1 = 17.2;

    /** Second penalty temperature limit. */
    public static final double TEMP_PENALTY_2 = 18.2;

    /** Third penalty temperature limit. */
    public static final double TEMP_PENALTY_3 = 18.7;

    /** First temperature at which photosynthesis begins to die. */
    public static final double TEMP_PS_DIE_1 = 16.7;

    /** Second temperature at which photosynthesis dies faster. */
    public static final double TEMP_PS_DIE_2 = 17.7;

    /** Temperature below which photosynthesis regrows. */
    public static final double TEMP_PS_REGROW = 16.2;

    /** Photosynthesis death rate at first limit. */
    public static final int PS_DIE_RATE_1 = 250;

    /** Photosynthesis death rate at second limit. */
    public static final int PS_DIE_RATE_2 = 500;

    /** Rate at which photosynthesis regrows. */
    public static final int PS_REGROW_RATE = 50;

    /** Years of delay before photosynthesis begins to die. */
    public static final int PS_DEATH_DELAY = 2;

    /** Maintenance cost multiplier at first penalty. */
    public static final double MAINT_MULT_1 = 1.1;

    /** Maintenance cost multiplier at second penalty. */
    public static final double MAINT_MULT_2 = 1.25;

    /** Income multiplier at first penalty. */
    public static final double INCOME_MULT_1 = 0.9;

    /** Income multiplier at second penalty. */
    public static final double INCOME_MULT_2 = 0.8;

    /** Income multiplier at third penalty. */
    public static final double INCOME_MULT_3 = 0.6;

    /** Maximum allowed debt limit. */
    public static final long DEBT_LIMIT = -10_000;
}
