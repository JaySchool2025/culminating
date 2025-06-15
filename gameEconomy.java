public class gameEconomy {
    public static final int MAX_COAL = 40;
    public static final int MAX_GAS = 50;
    public static final int MAX_NUCLEAR = 30;
    public static final int MAX_GREEN = 100;
    public static final int MAX_PS = 10000;

    public static final long COAL_COST = 250_000;
    public static final long GAS_COST = 220_000;
    public static final long NUCLEAR_COST = 400_000;
    public static final long GREEN_COST = 300_000;
    public static final long PS_COST = 20_000;

    public static final double COAL_ENERGY = 5.0;
    public static final double GAS_ENERGY = 4.5;
    public static final double NUCLEAR_ENERGY = 7.0;
    public static final double GREEN_ENERGY = 2.5;

    public static final double COAL_CO2 = 1.0;
    public static final double GAS_CO2 = 0.45;
    public static final double NUCLEAR_CO2 = 0.02;
    public static final double GREEN_CO2 = 0.0;
    public static final double PS_CO2 = -0.25;

    public static final long MONEY_PER_GW = 100_000;

    public static final double TEMP_INCREASE_PER_MT = 0.01;
    public static final double TEMP_DECREASE_PER_MT = 0.001;

    public static final double BASE_TEMPERATURE = 14.7;

    public static final double TEMP_PENALTY_1 = 17.2;
    public static final double TEMP_PENALTY_2 = 18.2;
    public static final double TEMP_PENALTY_3 = 18.7;

    public static final double TEMP_PS_DIE_1 = 16.7;
    public static final double TEMP_PS_DIE_2 = 17.7;
    public static final double TEMP_PS_REGROW = 16.2;

    public static final int PS_DIE_RATE_1 = 250;
    public static final int PS_DIE_RATE_2 = 500;
    public static final int PS_REGROW_RATE = 50;

    public static final int PS_DEATH_DELAY = 2;

    public static final double MAINT_MULT_1 = 1.1;
    public static final double MAINT_MULT_2 = 1.25;

    public static final double INCOME_MULT_1 = 0.9;
    public static final double INCOME_MULT_2 = 0.8;
    public static final double INCOME_MULT_3 = 0.6;

    public static final long DEBT_LIMIT = -10_000;
}
