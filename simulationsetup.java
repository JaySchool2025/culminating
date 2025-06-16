<<<<<<< HEAD
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;

// Class name corrected: no parentheses in class declaration
class World {
    int coalplants;
    int naturalgasplants;
    int nuclearplants;
    int greenenergyplants;
    int photoplants;
    int totalcash;
    int TimeSincePDRMech;
    String worldname;

    // Constructor fixed: missing assignments corrected
    public World(int coalplants, int naturalgasplants, int nuclearplants, int greenenergyplants, int photoplants, int totalcash, String worldname, int TimeSincePDRMech) {
        this.coalplants = coalplants;
        this.naturalgasplants = naturalgasplants;
        this.nuclearplants = nuclearplants;
        this.greenenergyplants = greenenergyplants;
        this.photoplants = photoplants;
        this.totalcash = totalcash;
        this.worldname = worldname;
        this.TimeSincePDRMech = TimeSincePDRMech;
    }
}


public class simulationsetup {
    Scanner s = new Scanner(System.in); 
    boolean breaker = true;
    boolean paramchecker = true;
    int[] worldstats = new int[5];
    public static void main(String[] args) {
       simulationsetup s = new simulationsetup();
       s.filemenuopen();
    }
    
    public void filemenuopen(){
    
    while(true){ 
        System.out.println("Welcome to the climate change simulator, do you have a saved file?");
        String answer = s.nextLine();
        answer = answer.toLowerCase(); 

        if (answer.equals("yes")) {
            loadfile();
        }

        if (answer.equals("no")) {
            System.out.println("Opening menu.");
        }

        System.out.printf("\nWould you like to \n1 - start new simulation \n2 - load simulations \n3 - view all your simulations current status \n4 - save simulation to file \n5 - run simulation \n6 - information \n7 - quit\n");
        int option = s.nextInt();
        s.nextLine(); 
        
        if (option == 1) {
            simsetup(); 
        }

        if (option == 2) {
            System.out.println("What simulation would you like to load?");
            String loadworldname = s.nextLine();
            worldloader(loadworldname);
        
        }

        if (option == 3) {
            readsimulations();
        }


        if (option == 5) {
            System.out.println("Insert runsimulation here");
        }

        if (option == 6) {
            informatives();
        }

        if (option == 7) {
            System.out.println("quitting program");
            break;
        }

        s.close(); 
    
      }
    }
    
    public void simsetup() {
        // add limitations, 40 coal, 50 natural, 30 nuclear, 100 green, 100000 photo plants.
        int upperbound;
        
        System.out.println("What would you like the name of this world to be?");
        String worldnames = s.nextLine();
       
        System.out.println("How many coal plants would you like to have (0-40)?");  
        upperbound = 40;
        lowerupper(0, upperbound);    
        
        System.out.println("How many nuclear plants would you like?(0-30)");
        upperbound = 30;
        lowerupper(2, upperbound);
        
        System.out.println("How many green energy plants would you like?(0-100)");
        upperbound = 100;
        lowerupper(3, upperbound);
        
        System.out.println("How many natural gas plants would you like?(0-50)");
        upperbound = 50;
        lowerupper(1, upperbound);
        
        System.out.println("How many photosynthesizing plants would you like to add?(0-10000)");
        upperbound = 10000;
        lowerupper(4, upperbound);
        
        World Newworld = new World(worldstats[0], worldstats[1], worldstats[2], worldstats[3], worldstats[4], 2500, worldnames);
        initialsavesim(worldstats, name); 
    }
    
    public void lowerupper(int arrayslot, int bound) {
      
      
      int amount;
      boolean paramchecker = true;
      int lowerbound = 0;
      while(paramchecker){
         amount = s.nextInt();
         if(amount >= 0 && amount <= bound){
            worldstats[arrayslot] = amount;
            paramchecker = false;
            
         }
         
         else{
         System.out.println("Please enter value between 0 and " + bound);
         
         }
      }   
      
   }
   
    public static void readsimulations() {
        String filepath = "worlds.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null) {
               System.out.println(line);
            }
         }
         
         catch (IOException e){
            e.printStackTrace();
         }
         
     }      

    public static void worldloader(String desiredword) {
        float[6] worldstats;
        int counter;
        boolean namechecker = true;
        String filepath = "worlds.txt";
        Scanner scanner = new Scanner(filepath);
        
        while(scanner.hasNextLine && namechecker){
           String line;
           line = scanner.Next();
          
           if(line.contains("World:" + desiredword)){
            namechecker = false;
            
            Scanner worldinput = new Scanner(line) 
            worldinput.useDelimiter("\\s*[:|]\\s*");
           
             while(worldinput.hasNext()) {
              String Label = worldinput.next();
              
              if(label.equals("World")){
               String worldname = worldinput.next();
              }
              
              else { 
               if(worldInput.hasNextFloat()){
               float value = worldInput.nextFloat();
               worldloadeddata.add(value);
               }
               else{
               worldinput.Next();
               }
            } 

           } 
           worldInput.close();        
        }
            
    }

    scanner.close();
    System.out.println("Values loaded");
    
  }
  
  
    
    public static void initialsavesim(int[] arr, String name) {
        Filewriter worldwriter = new Filewriter ("worlds.txt");
            worldwriter.write("World:" + Newworld.worldnames + 
            "|Coal Plants:" + Newworld.coalplants + 
            "|Natural Gas Plants:" + Newworld.naturalgasplants + 
            "|Nuclear Plants:" + Newworld.nuclearplants + 
            "|Green Plants:" + Newworld.greenenergyplants + 
            "|Photosynthesising Plants:" + Newworld.photoplants + 
            "|Total Money:" + Newworld.totalcash + 
            "|World Temp(Celsius):14.7" + 
            "|Time Since Plant Death/Regrowth Mechanic:0|");
            worldwriter.write(System.lineseparator());
            worldwriter.close();
                          
    }


    public static void informatives() {
        System.out.println("Plant information:");
        System.out.println("Coal Plant: +1.0 megatons CO₂/year — produces 5 gigawatts/year — costs $250M yearly to maintain.");
        System.out.println("Natural Gas Plant: +0.45 megatons CO₂/year — produces 4.5 gigawatts/year — costs $220M yearly to maintain.");
        System.out.println("Nuclear Plant: +0.02 megatons CO₂/year — produces 7.0 gigawatts/year — costs $400M yearly to maintain.");
        System.out.println("Green Energy Plant: +0.00 megatons CO₂/year — produces 2.5 gigawatts/year — costs $300M yearly to maintain.");
        System.out.println("Photosynthesizing Plant: −0.25 megatons CO₂/year — costs $20M yearly to maintain.");
        System.out.println("");
        System.out.println("Ecological information:");
        System.out.println("If global temperature > 16.7°C, Photosynthesizing Plants die at 250 per year.");
        System.out.println("If global temperature > 17.7°C, Photosynthesizing Plants die at 500 per year.");
        System.out.println("If global temperature < 16.2°C, Photosynthesizing Plants regrow at 50 per year.");
        System.out.println("2 year delay on Photosynthesizing Plant death/regrowth.");
        System.out.println("If global temperature > 19.7°C, simulation ends.");
        System.out.println("");
        System.out.println("Economic information:");
        System.out.println("1 gigawatt = $100M.");
        System.out.println("The player may buy additional plants (all types) during pauses.");
        System.out.println("If unable to upkeep maintenance costs, the player may choose which plants to rid themselves of during pauses between sessions.");
        System.out.println("If global temperature increases above 17.2°C, -10% income from energy production.");
        System.out.println("If global temperature increases above 18.2°C, -20% income from energy production, +10% maintenance costs.");
        System.out.println("If global temperature increases above 18.7°C, -40% income from energy production, +25% maintenance costs.");
        System.out.println("If player goes $10M into debt, simulation ends.");
    }
}
=======
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
>>>>>>> 0c65a6dc8838de1caf17cbd8a239e0f3780096f6
