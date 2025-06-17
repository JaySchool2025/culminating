import java.util.Scanner;

/**
 * This class controls the main game functions and handles user play
 * like advancing years, buying/selling plants, and ending the game.
 * It manages game  transitions.
 * 
 * @author Jayden&Ari
 * @version 1.0
 */
public class gamemain {

    /** The world instance representing current world*/
    private world currentWorld;

    /** Scanner used to read user input. */
    private Scanner sc;

    /** 
     * constructs the game with a world and a scanner for user input.
     */
    public gamemain(world w, Scanner sc) {
        this.currentWorld = w;
        this.sc = sc;
    }

    /**
     * Runs the main game  until a game-over condition is met or the user exits.
     * Displays the current status, handles year progress, purchasing, and selling.
     */
    public void run() {
        while (true) {
            currentWorld.displayStatus();

            if (currentWorld.money < gameEconomy.DEBT_LIMIT) {
                System.out.println("You went $10 million into debt. Game over.");
                break;
            }

            if (currentWorld.worldtemp > 19.7) {
                System.out.println("Global temperature exceeded 19.7\u00B0C. Game over.");
                break;
            }

            System.out.println("Choose an option:");
            System.out.println("1. Advance one year");
            System.out.println("2. Buy plants");
            System.out.println("3. Sell plants");
            System.out.println("4. Exit game");
            System.out.print("Choice: ");

            int choice = getIntInput(1, 4);

            if (choice == 1) {
                currentWorld.advanceYear();
                long income = currentWorld.getYearlyIncome();
                long maintenance = currentWorld.getMaintenanceCost();
                currentWorld.money += (income - maintenance);
                System.out.printf("Year advanced. Net income: $%,d,000. Temperature: %.3f\u00B0C\n",
                                  (income - maintenance), currentWorld.worldtemp);
                currentWorld.saveToFile("worlds.txt");
            } else if (choice == 2) {
                buyOrSell(true);
            } else if (choice == 3) {
                buyOrSell(false);
            } else {
                System.out.println("Exiting game...");
                break;
            }
        }
    }

    /**
     * Reads an integer input from the user between a minimum and maximum value.
     * Retries until a valid number is entered.
     */
    private int getIntInput(int min, int max) {
        while (true) {
            String line = sc.nextLine();
            try {
                int val = Integer.parseInt(line.trim());
                if (val >= min && val <= max) return val;
                else System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            System.out.print("Choice: ");  
        }
    }

    /**
     * does buying or selling power plants based on user input.
     * Updates the world state and deducts or adds money.
     */
    private void buyOrSell(boolean isBuying) {
        System.out.println((isBuying ? "Buy" : "Sell") + " menu:");
        System.out.println("1. Coal Plant");
        System.out.println("2. Gas Plant");
        System.out.println("3. Nuclear Plant");
        System.out.println("4. Green Plant");
        System.out.println("5. Photosynthesizing Plant");
        System.out.print("Choice: ");

        int choice = getIntInput(1, 5);
        int current = 0, max = 0, limit = 0;
        long unitCost = 0;

        switch (choice) {
            case 1:
                current = currentWorld.coalplant;
                limit = gameEconomy.MAX_COAL;
                unitCost = gameEconomy.COAL_COST;
                break;
            case 2:
                current = currentWorld.gasplant;
                limit = gameEconomy.MAX_GAS;
                unitCost = gameEconomy.GAS_COST;
                break;
            case 3:
                current = currentWorld.nuclearplant;
                limit = gameEconomy.MAX_NUCLEAR;
                unitCost = gameEconomy.NUCLEAR_COST;
                break;
            case 4:
                current = currentWorld.greenplant;
                limit = gameEconomy.MAX_GREEN;
                unitCost = gameEconomy.GREEN_COST;
                break;
            case 5:
                current = currentWorld.psplant;
                limit = gameEconomy.MAX_PS;
                unitCost = gameEconomy.PS_COST;
                break;
        }

        if (isBuying) {
            max = limit - current;
            if (max <= 0) {
                System.out.println("You cannot buy any more of this plant type.");
                return;
            }
            System.out.printf("You can buy up to %d units. Cost per unit: $%,d,000\n", max, unitCost);
            System.out.print("How many do you want to buy? ");
            int amt = getIntInput(1, max);
            long cost = amt * unitCost;

            if (currentWorld.money < cost) {
                System.out.println("You don't have enough money.");
                return;
            }

            currentWorld.money -= cost;
            updatePlant(choice, amt);
            System.out.println("Purchase successful.");
        } else {
            max = current;
            if (max <= 0) {
                System.out.println("You don't have any of this plant type to sell.");
                return;
            }
            System.out.printf("You can sell up to %d units. Sell price per unit: $%,d,000\n", max, unitCost / 2);
            System.out.print("How many do you want to sell? ");
            int amt = getIntInput(1, max);
            long gain = amt * unitCost / 2;

            currentWorld.money += gain;
            updatePlant(choice, -amt);
            System.out.println("Sale successful.");
        }

        currentWorld.saveToFile("worlds.txt");
    }

    /**
     * Updates the number of plants in the world based on type and amount added or removed.
     */
    private void updatePlant(int type, int change) {
        switch (type) {
            case 1: currentWorld.coalplant += change; break;
            case 2: currentWorld.gasplant += change; break;
            case 3: currentWorld.nuclearplant += change; break;
            case 4: currentWorld.greenplant += change; break;
            case 5: currentWorld.psplant += change; break;
        }
    }
}
