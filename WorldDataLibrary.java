public class WorldDataLibrary{
   
   public static void Coalplant(){
      double CO2 = 1.00; // Megatons of CO2
      double Energy = 5.0; // Gigawatts
      int Cost = 250; // Millions of dollars
   }
   
   public static void Gasplant(){
      double CO2 = 0.45; // Megatons of CO2
      double Energy = 4.5; // Gigawatts
      int Cost = 220; // Millions of dollars
   }
   
   public static void Nuclearplant(){
      double CO2 = 0.02; // Megatons of CO2
      double Energy = 7.0; // Gigawatts
      int Cost = 400; // Millions of dollars
   }
   
   public static void Greenplant(){
      double CO2 = 0.00; // Megatons of CO2
      double Energy = 2.5; // Gigawatts
      int Cost = 300; // Millions of dollars
   }
   
   public static void Photoplant(){
      double CO2 = -0.25; // Megatons of CO2
      double Energy = 0.0; // Gigawatts
      int Cost = 20; // Millions of dollars
   }
   
   public static void PenaltiesandPlantRates(){
      double FirstEnergy = 0.90; // Energy Income multiplier when global temp >= 17.2°C
      double SecondEnergy = 0.80; // Energy Income multiplier when global temp >= 18.2°C
      double ThirdEnergy = 0.60; // Energy Income multiplier when global temp >= 18.7°C
      
      double FirstCost = 1.10; // Maintenance Cost multiplier when global temp >= 18.2°C
      double SecondCost = 1.25; // Maintenance Cost multiplier when global temp >= 18.7°C
      
      int PlantRegrowth = 50; // # plants that regrow when global temp < 16.2°C for over 2 years in a row
      int FirstPlantDeath = 250; // # plants that die when global temp > 16.7°C for over 2 years in a row
      int SecondPlantDeath = 500; // # plants that die when global temp > 17.7°C for over 2 years in a row
   }
   
   public static void EndingValues(){
      int DebtEnding = -10; // # in Millions of dollars that bank balance must be to access debt ending
      double TempEnding = 19.7; // #°C that global temp must be to access temp ending
   }
   
}