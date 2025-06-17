/**
 * simulationsetup class manages the loading, saving, sorting,
 * selection, and creation of world objects for the simulation game.
 * It interacts with a persistent storage file ("worlds.txt") to
 * maintain the list of worlds across sessions.
 * 
 * @Author  Jayden&Ari
 * @Version 1.0
 */
import java.io.*;
import java.util.*;

public class simulationsetup {
    private List<world> worlds = new ArrayList<>();

    /**
     * Loads worlds from the file "worlds.txt" into the  list.
     * Each line is a world is parsed and converted into
     * a world object. 
     */
    public void loadWorlds() {
        worlds.clear();
        try (Scanner fileScanner = new Scanner(new File("worlds.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (!line.startsWith("World:")) continue;

                String[] parts = line.split("\\|");
                String name = "", coalS = "", gasS = "", nuclearS = "", greenS = "", psS = "", moneyS = "", tempS = "", timeS = "";
                for (String part : parts) {
                    part = part.trim();
                    if (part.startsWith("World:")) name = part.substring("World:".length()).trim();
                    else if (part.startsWith("Coalplant:")) coalS = part.substring("Coalplant:".length()).trim();
                    else if (part.startsWith("Gasplant:")) gasS = part.substring("Gasplant:".length()).trim();
                    else if (part.startsWith("Nuclearplant:")) nuclearS = part.substring("Nuclearplant:".length()).trim();
                    else if (part.startsWith("Greenplant:")) greenS = part.substring("Greenplant:".length()).trim();
                    else if (part.startsWith("PSplant:")) psS = part.substring("PSplant:".length()).trim();
                    else if (part.startsWith("Money:")) moneyS = part.substring("Money:".length()).trim();
                    else if (part.startsWith("WorldTemp:")) tempS = part.substring("WorldTemp:".length()).trim();
                    else if (part.startsWith("TimeSincePDRMech:")) timeS = part.substring("TimeSincePDRMech:".length()).trim();
                }
                try {
                    int coal = Integer.parseInt(coalS);
                    int gas = Integer.parseInt(gasS);
                    int nuclear = Integer.parseInt(nuclearS);
                    int green = Integer.parseInt(greenS);
                    int ps = Integer.parseInt(psS);
                    long money = Long.parseLong(moneyS);
                    double temp = Double.parseDouble(tempS);
                    int yearsSincePSDeath = Integer.parseInt(timeS);

                    worlds.add(new world(name, coal, gas, nuclear, green, ps, money, temp, yearsSincePSDeath));
                } catch (Exception e) {
                    System.out.println("Number format error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading worlds.txt");
        }
    }

    /**
     * saves all currently loaded worlds to the file "worlds.txt".
     * Each world is written as a single line using its data values.
     */
    public void saveWorlds() {
        try (PrintWriter writer = new PrintWriter("worlds.txt")) {
            for (world w : worlds) {
                writer.println(w.toDataString());
            }
        } catch (IOException e) {
            System.out.println("Error saving worlds.txt");
        }
    }

    /**
     * sorts the list of worlds alphabetically by their name.
     */
    public void sortWorlds() {
        worlds.sort(Comparator.comparing(world::getName));
    }

    /**
     * displays all loaded worlds and prompts the user to select one by number.
     * checks input and returns the selected world object or null if none.
     * 
     * @param sc scanner object for reading user input
     * @return the selected world or null if none available or selection aborted
     */
    public world selectWorld(Scanner sc) {
        if (worlds.isEmpty()) {
            System.out.println("No worlds available. Please create one.");
            return null;
        }
        for (int i = 0; i < worlds.size(); i++) {
            System.out.println((i + 1) + ". " + worlds.get(i).getName());
        }
        int choice;
        while (true) {
            System.out.print("Select world by number: ");
            String line = sc.nextLine();
            try {
                choice = Integer.parseInt(line);
                if (choice >= 1 && choice <= worlds.size()) {
                    return worlds.get(choice - 1);
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * creates a new world by asking the user for the world name and
     * initial amounts of various plant types. adds the new world to the list
     * and saves all worlds to the file.
     * 
     * @param sc scanner object for reading user input
     * @return the newly created world object
     */
    public world createWorld(Scanner sc) {
        System.out.print("Enter world name: ");
        String name = sc.nextLine();

        int coal = getValidInt(sc, "Coal plants (0-40): ", 0, 40);
        int gas = getValidInt(sc, "Gas plants (0-50): ", 0, 50);
        int nuclear = getValidInt(sc, "Nuclear plants (0-30): ", 0, 30);
        int green = getValidInt(sc, "Green plants (0-100): ", 0, 100);
        int ps = getValidInt(sc, "Photosynthesizing plants (0-10000): ", 0, 10000);

        long money = 10_000_000L; // Starting money: $10,000,000 (1 unit = $1000)
        double temp = 14.7;
        int timeSincePSDeath = 0;

        world newWorld = new world(name, coal, gas, nuclear, green, ps, money, temp, timeSincePSDeath);
        worlds.add(newWorld);
        saveWorlds();
        return newWorld;
    }

    /**
     * ask the user to enter an integer value within the specified range,
     * checking the input and repeating until a valid integer is given.
     * 
     * @param sc scanner object for reading user input
     * @param prompt the message to prompt the user
     * @param min the minimum valid integer (inclusive)
     * @param max the maximum valid integer (inclusive)
     * @return the valid integer entered by the user
     */
    private int getValidInt(Scanner sc, String prompt, int min, int max) {
        int val;
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                val = Integer.parseInt(line);
                if (val >= min && val <= max) return val;
                else System.out.println("Enter a value between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
}
