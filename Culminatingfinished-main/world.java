public class world {
    public String name;
    public int coalplant, gasplant, nuclearplant, greenplant, psplant;
    public long money;
    public double worldtemp;
    public int yearsSincePSDeath;

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

    public double getTotalEnergyGW() {
        return coalplant * gameEconomy.COAL_ENERGY +
               gasplant * gameEconomy.GAS_ENERGY +
               nuclearplant * gameEconomy.NUCLEAR_ENERGY +
               greenplant * gameEconomy.GREEN_ENERGY;
    }

    public double getTotalCO2() {
        return coalplant * gameEconomy.COAL_CO2 +
               gasplant * gameEconomy.GAS_CO2 +
               nuclearplant * gameEconomy.NUCLEAR_CO2 +
               greenplant * gameEconomy.GREEN_CO2 +
               psplant * gameEconomy.PS_CO2;
    }

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

    public long getYearlyIncome() {
        double incomeMultiplier = 1.0;
        if (worldtemp > gameEconomy.TEMP_PENALTY_3) incomeMultiplier = gameEconomy.INCOME_MULT_3;
        else if (worldtemp > gameEconomy.TEMP_PENALTY_2) incomeMultiplier = gameEconomy.INCOME_MULT_2;
        else if (worldtemp > gameEconomy.TEMP_PENALTY_1) incomeMultiplier = gameEconomy.INCOME_MULT_1;

        double energyProduced = getTotalEnergyGW();
        return (long)(energyProduced * gameEconomy.MONEY_PER_GW * incomeMultiplier);
    }

    public void advanceYear() {
        double netCO2 = getTotalCO2();
        double tempChange = 0.0;
        if (netCO2 > 0) tempChange = netCO2 * gameEconomy.TEMP_INCREASE_PER_MT;
        else tempChange = netCO2 * gameEconomy.TEMP_DECREASE_PER_MT;

        worldtemp += tempChange;

        long income = getYearlyIncome();
        long cost = getMaintenanceCost();
        money += income - cost;

        if (worldtemp > gameEconomy.TEMP_PS_DIE_1) yearsSincePSDeath++;
        else if (worldtemp < gameEconomy.TEMP_PS_REGROW) yearsSincePSDeath--;

        if (yearsSincePSDeath < 0) yearsSincePSDeath = 0;

        if (worldtemp > gameEconomy.TEMP_PS_DIE_2 && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            int dieAmount = gameEconomy.PS_DIE_RATE_2;
            psplant -= dieAmount;
            if (psplant < 0) psplant = 0;
        } else if (worldtemp > gameEconomy.TEMP_PS_DIE_1 && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            int dieAmount = gameEconomy.PS_DIE_RATE_1;
            psplant -= dieAmount;
            if (psplant < 0) psplant = 0;
        } else if (worldtemp < gameEconomy.TEMP_PS_REGROW && yearsSincePSDeath > gameEconomy.PS_DEATH_DELAY) {
            int regrowAmount = gameEconomy.PS_REGROW_RATE;
            psplant += regrowAmount;
            if (psplant > gameEconomy.MAX_PS) psplant = gameEconomy.MAX_PS;
        }
    }

    public String toFileFormat() {
        return "World:" + name + "|Coalplant:" + coalplant + "|Gasplant:" + gasplant + "|Nuclearplant:" + nuclearplant +
               "|Greenplant:" + greenplant + "|PSplant:" + psplant + "|Money:" + money + "|WorldTemp:" + worldtemp +
               "|TimeSincePDRMech:" + yearsSincePSDeath + "|";
    }

    public void displayStatus() {
        System.out.printf("World: %s\n", name);
        System.out.printf("Temperature: %.2f\u00B0C\n", worldtemp);
        System.out.printf("Money: $%,d,000\n", money);
        System.out.printf("Plants - Coal: %d, Gas: %d, Nuclear: %d, Green: %d, Photosynth: %d\n", coalplant, gasplant, nuclearplant, greenplant, psplant);
        System.out.printf("Yearly Maintenance Cost: $%,d,000\n", getMaintenanceCost());
        System.out.printf("Yearly Income (energy sales): $%,d,000\n", getYearlyIncome());
    }
    
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

public String getName() {
    return name;
}

}
