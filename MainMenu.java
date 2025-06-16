import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        simulationsetup setup = new simulationsetup();

        setup.loadWorlds();
        setup.sortWorlds();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Select existing world");
            System.out.println("2. Create new world");
            System.out.print("Choice: ");

            int choice = getIntInput(sc, 1, 2);

            if (choice == 1) {
                world selected = setup.selectWorld(sc);
                if (selected != null) {
                    System.out.println("Selected world: " + selected.getName());
                    gamemain game = new gamemain(selected, sc);
                    game.run();
                    break; 
                }
            } else {
                world created = setup.createWorld(sc);
                System.out.println("Created world: " + created.getName());
                
            }
        }

        sc.close();
    }

    private static int getIntInput(Scanner sc, int min, int max) {
        int val = -1;
        while (true) {
            String line = sc.nextLine();
            try {
                val = Integer.parseInt(line);
                if (val >= min && val <= max) return val;
                else System.out.printf("Please enter a number between %d and %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
            System.out.print("Choice: ");
        }
    }
}
      