import java.io.*;
import java.util.*;

public class simulationsetup {
    private List<world> worlds = new ArrayList<>();

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

    public void saveWorlds() {
        try (PrintWriter writer = new PrintWriter("worlds.txt")) {
            for (world w : worlds) {
                writer.println(w.toDataString());
            }
        } catch (IOException e) {
            System.out.println("Error saving worlds.txt");
        }
    }

    public void sortWorlds() {
        worlds.sort(Comparator.comparing(world::getName));
    }

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

    public world createWorld(Scanner sc) {
        System.out.print("Enter world name: ");
        String name = sc.nextLine();

        int coal = getValidInt(sc, "Coal plants (0-40): ", 0, 40);
        int gas = getValidInt(sc, "Gas plants (0-50): ", 0, 50);
        int nuclear = getValidInt(sc, "Nuclear plants (0-30): ", 0, 30);
        int green = getValidInt(sc, "Green plants (0-100): ", 0, 100);
        int ps = getValidInt(sc, "Photosynthesizing plants (0-10000): ", 0, 10000);

        long money = 10000000L; // Starting $10B, 1 unit = $1000
        double temp = 14.7;
        int timeSincePSDeath = 0;

        world newWorld = new world(name, coal, gas, nuclear, green, ps, money, temp, timeSincePSDeath);
        worlds.add(newWorld);
        saveWorlds();
        return newWorld;
    }

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
