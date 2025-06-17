/**
 * is a simulated world with various types of power plants,
 * economy, and environmental parameters/values.
 */
public class world {
    /** The name of the world. */
    public String name;

    /** Number of coal power plants. */
    public int coalplant;

    /** Number of gas power plants. */
    public int gasplant;

    /** Number of nuclear power plants. */
    public int nuclearplant;

    /** Number of green power plants. */
    public int greenplant;

    /** Number of photosynthesizing plants (PS). */
    public int psplant;

    /** The  money in this world (in thousands). */
    public long money;

    /** The current temperature of the world. */
    public double worldtemp;

    /** Years since photosynthesizing plant death started. */
    public int yearsSincePSDeath;

    /**
     * makes a world object with specified parameters.
     */
    public world(String name, int coalplant, int gasplant, int nuclearplant, int greenplant, int psplant, long money, double worldtemp, int yearsSincePSDeath) {
        this.name = name;
        this.coalplant = coalplant;
        this.gasplant = gasplant;
        this.nuclearplant = nuclearplant;
        this.greenplant = greenplant;
        this.psplant = psplant;
        this.money = money;
        this.worldtemp = worldtemp;
        this.yearsSincePSDeath = yearsSincePSDeath;
    }

    /**
     * calculates total energy production (in GW).
     * @return total gigawatts generated from all energy sources
     */
    public double getTotalEnergyGW() {
        return coalplant * gameEconomy.COAL_ENERGY +
               gasplant * gameEconomy.GAS_ENERGY +
               nuclearplant * gameEconomy.NUCLEAR_ENERGY +
               greenplant * gameEconomy.GREEN_ENERGY;
    }

    /**
     * calculates total CO2 emissions.
     * @return total CO2 in megatons
     */
    public double getTotalCO2() {
        return coalplant * gameEconomy.COAL_CO2 +
               gasplant * gameEconomy.GAS_CO2 +
               nuclearplant * gameEconomy.NUCLEAR_CO2 +
               greenplant * gameEconomy.GREEN_CO2 +
               psplant * gameEconomy.PS_CO2;
    }

    /**
     * finds the annual maintenance cost for all power plants,
     * factoring in temp penalties.
     * @return yearly maintenance cost in thousands
     */
    public long getMaintenanceCost() {
        double multiplier = 1.0;
        if (worldtemp > gameEconomy.TEMP_PENALTY_3) multiplier = gameEconomy.MAINT_MULT_2;
        else if (worldtemp > gameEconomy.TEMP_PENALTY_2) multiplier = gameEconomy.MAINT_MULT_1;
        return (long) ((coalplant * gameEconomy.COAL_COST +
                        gasplant * gameEconomy.GAS_COST +
                        nuclearplant * gameEconomy.NUCLEAR_COST +
                        greenplant * gameEconomy.GREEN_COST +
                        psplant * gameEconomy.PS_COST) * multiplier);
    }

    /**
     * calculates the yearly income based on energy production and
     * environmental conditions.
     * @return annual income in thousands
     */
    public long getYearlyIncome() {
        double incomeMultiplier = 1.0;
        if (worldtemp > gameEconomy.TEMP_PENALTY_3) incomeMultiplier = gameEconomy.INCOME_MULT_3;
        else if (worldtemp > gameEconomy.TEMP_PENALTY_2) incomeMultiplier = gameEconomy.INCOME_MULT_2;
        else if (worldtemp > gameEconomy.TEMP_PENALTY_1) incomeMultiplier = gameEconomy.INCOME_MULT_1;

        double energyProduced = getTotalEnergyGW();
        return (long)(energyProduced * gameEconomy.MONEY_PER_GW * incomeMultiplier);
    }

    /**
     * simulates one year of world progression, updating temperature,
     * money, and photosynthesizing plant health.
     */
    public void advanceYear() {
        double netCO2 = getTotalCO2();
        double tempChange = (netCO2 > 0)
            ? netCO2 * gameEconomy.TEMP_INCREASE_PER_MT
            : netCO2 * gameEconomy.TEMP_DECREASE_PER_MT;

        worldtemp += tempChange;

        long income = getYearlyIncome();
        long cost = getMaintenanceCost();
        money += income - cost;

        if (worldtemp > gameEconomy.TEMP_PS_DIE_1) yearsSincePSDeath++;
        else if (worldtemp < gameEconomy.TEMP_PS_REGROW) yearsSincePSDeath--;

        if (yearsSincePSDeath < 0) yearsSincePSDeath = 0;

        if (worldtemp > gameEconomy.TEMP_PS_DIE_2 && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            psplant -= gameEconomy.PS_DIE_RATE_2;
        } else if (worldtemp > gameEconomy.TEMP_PS_DIE_1 && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            psplant -= gameEconomy.PS_DIE_RATE_1;
        } else if (worldtemp < gameEconomy.TEMP_PS_REGROW && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            psplant += gameEconomy.PS_REGROW_RATE;
        }

        if (psplant < 0) psplant = 0;
        if (psplant > gameEconomy.MAX_PS) psplant = gameEconomy.MAX_PS;
    }

    /**
     * puts the world's data as a single-line string for saving.
     * @return aseparated data string
     */
    public String toFileFormat() {
        return "World:" + name + "|Coalplant:" + coalplant + "|Gasplant:" + gasplant + "|Nuclearplant:" + nuclearplant +
               "|Greenplant:" + greenplant + "|PSplant:" + psplant + "|Money:" + money + "|WorldTemp:" + worldtemp +
               "|TimeSincePDRMech:" + yearsSincePSDeath + "|";
    }

    /**
     * displays the current state of the world to the console.
     */
    public void displayStatus() {
        System.out.printf("World: %s\n", name);
        System.out.printf("Temperature: %.2f\u00B0C\n", worldtemp);
        System.out.printf("Money: $%,d,000\n", money);
        System.out.printf("Plants - Coal: %d, Gas: %d, Nuclear: %d, Green: %d, Photosynth: %d\n",
                          coalplant, gasplant, nuclearplant, greenplant, psplant);
        System.out.printf("Yearly Maintenance Cost: $%,d,000\n", getMaintenanceCost());
        System.out.printf("Yearly Income (energy sales): $%,d,000\n", getYearlyIncome());
    }

    /**
     * returns the string representation of this world for saving.
     * @return string for data file
     */
    public String toDataString() {
        return "World:" + name +
               "|Coalplant:" + coalplant +
               "|Gasplant:" + gasplant +
               "|Nuclearplant:" + nuclearplant +
               "|Greenplant:" + greenplant +
               "|PSplant:" + psplant +
               "|Money:" + money +
               "|WorldTemp:" + worldtemp +
               "|TimeSincePDRMech:" + yearsSincePSDeath + "|";
    }

    /**
     * gets the name of the world.
     * @return the world name
     */
    public String getName() {
        return name;
    }
}

