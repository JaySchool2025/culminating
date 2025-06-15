public class World {
    String worldname;
    int coalplants;
    int naturalgasplants;
    int nuclearplants;
    int greenenergyplants;
    int photoplants;
    int totalcash;
    int worldtemp;
    int timeSincePDMech;

    public World(String worldname, int coalplants, int naturalgasplants, int nuclearplants, int greenenergyplants, int photoplants, int totalcash, int worldtemp, int timeSincePDMech) {
        this.worldname = worldname;
        this.coalplants = coalplants;
        this.naturalgasplants = naturalgasplants;
        this.nuclearplants = nuclearplants;
        this.greenenergyplants = greenenergyplants;
        this.photoplants = photoplants;
        this.totalcash = totalcash;
        this.worldtemp = worldtemp;
        this.timeSincePDMech = timeSincePDMech;
    }

    public String toString() {
        return "World: " + worldname + " | Temp: " + worldtemp + "°C";
    }
}
