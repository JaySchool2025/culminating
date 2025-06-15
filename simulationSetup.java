import java.io.*;
import java.util.*;

public class simulationSetup {
    static ArrayList<World> simulations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Climate Simulator ---");
            System.out.println("1 - Load all simulations");
            System.out.println("2 - View simulations (sorted by temperature)");
            System.out.println("3 - Load single simulation");
            System.out.println("4 - Save a new simulation");
            System.out.println("5 - Quit");
            System.out.print("Choice: ");
            int choice = s.nextInt();
            s.nextLine(); 

            switch (choice) {
                case 1:
                    readsimulations();
                    break;
                case 2:
                    printsortedsimulations();
                    break;
                case 3:
                    System.out.print("Enter world name to load: ");
                    String name = s.nextLine();
                    World w = loadfile(name);
                    if (w != null) {
                        System.out.println("Loaded: " + w);
                    }
                    break;
                case 4:
                    savenewsimulation(s);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        s.close();
    }

    public static void readsimulations() {
        simulations.clear();
        File file = new File("worlds.txt");
        if (!file.exists()) {
            System.out.println("No simulations found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split("\\|");
                if (parts.length >= 9) {
                    String name = parts[0].split(":")[1];
                    int coal = Integer.parseInt(parts[1].split(":")[1]);
                    int gas = Integer.parseInt(parts[2].split(":")[1]);
                    int nuclear = Integer.parseInt(parts[3].split(":")[1]);
                    int green = Integer.parseInt(parts[4].split(":")[1]);
                    int photo = Integer.parseInt(parts[5].split(":")[1]);
                    int money = Integer.parseInt(parts[6].split(":")[1]);
                    int temp = Integer.parseInt(parts[7].split(":")[1]);
                    int mech = Integer.parseInt(parts[8].split(":")[1]);

                    simulations.add(new World(name, coal, gas, nuclear, green, photo, money, temp, mech));
                }
            }
            System.out.println("Simulations loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void printsortedsimulations() {
        if (simulations.isEmpty()) {
            System.out.println("No simulations to display.");
            return;
        }

        
        for (int i = 0; i < simulations.size() - 1; i++) {
            for (int j = 0; j < simulations.size() - i - 1; j++) {
                if (simulations.get(j).worldtemp < simulations.get(j + 1).worldtemp) {
                    World temp = simulations.get(j);
                    simulations.set(j, simulations.get(j + 1));
                    simulations.set(j + 1, temp);
                }
            }
        }

        System.out.println("\nSimulations sorted by temperature:");
        for (World w : simulations) {
            System.out.println(w);
        }
    }

    public static World loadfile(String desiredworld) {
        for (World w : simulations) {
            if (w.worldname.equalsIgnoreCase(desiredworld)) {
                return w;
            }
        }
        System.out.println("Simulation '" + desiredworld + "' not found.");
        return null;
    }

    public static void savenewsimulation(Scanner s) {
        System.out.print("World name: ");
        String name = s.nextLine();
        int coal = getvalidinput(s, "Coal plants (0-40): ", 0, 40);
        int gas = getvalidinput(s, "Gas plants (0-50): ", 0, 50);
        int nuclear = getvalidinput(s, "Nuclear plants (0-30): ", 0, 30);
        int green = getvalidinput(s, "Green energy plants (0-100): ", 0, 100);
        int photo = getvalidinput(s, "Photosynthesizing plants (0-10000): ", 0, 10000);

        World w = new World(name, coal, gas, nuclear, green, photo, 2500, 14, 0);
        simulations.add(w);
        saveworld(w);
        System.out.println("Simulation saved.");
    }

    public static int getvalidinput(Scanner s, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            input = s.nextInt();
            if (input >= min && input <= max) break;
            System.out.println("Enter value between " + min + " and " + max + ".");
        }
        return input;
    }

    public static void saveworld(World w) {
        try (FileWriter fw = new FileWriter("worlds.txt", true)) {
            fw.write("World:" + w.worldname +
                    "|Coalplant:" + w.coalplants +
                    "|Gasplant:" + w.naturalgasplants +
                    "|Nuclearplant:" + w.nuclearplants +
                    "|Greenplant:" + w.greenenergyplants +
                    "|PSplant:" + w.photoplants +
                    "|Money:" + w.totalcash +
                    "|WorldTemp:" + w.worldtemp +
                    "|TimeSincePDRMech:" + w.timeSincePDMech + "|\n");
        } catch (IOException e) {
            System.out.println("Error saving world.");
        }
    }
}
