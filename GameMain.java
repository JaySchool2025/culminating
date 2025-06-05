import java.io.*;
import java.util.*;

public class GameMain{
   
   GameMain(){
      Scanner s = new Scanner(System.in);
   }
   
   public void FirstMessage(){
      
      System.out.printf("Welcome to the climate simulation./nBefore we start, would you like to go over some of the information/features of this game?");
      
      String confirmation = s.nextLine();
      confirmation = confirmation.toLowerCase();
      
      if(confirmation.equals("yes")){
         
         simulationsetup.informatives();
         
      }else if(confirmation.equals("no")){
         
         System.out.println("Okay, well thank you for playing, please enjoy the simulation.");
         OverviewTimePassage();
         
      }else{
         
         System.out.println("Please put either 'yes' or 'no' as an answer, anything else will not be accepted. The message will now repeat.");
         FirstMessage();
         
      }
   }
   
   public void OverviewTimePassage(/*a lot of values, good luck later me*/){
      // calculate total plants minus PS plants
      // depending on balance size, use values from link here: https://officespace.zendesk.com/hc/en-us/articles/115000593531-Scientific-Notation-Large-Numbers-Guide (M,B,T,Qa,Qi,Sx,Sp,Oc,No,etc.)
      
      System.out.println("Current Temperature: "/* + temperature*/ + "°C");
      System.out.println("Current Balance: $"/* + balance + character abbreviation*/);
      System.out.println("CO2 Emissions: "/* + co2 emissions*/ + " Mt/year");
      System.out.println("Income: $"/*+ income + character abbreviation*/);
      System.out.println("Total Energy Producing Plants: "/* + total plant count minus PS plant count*/);
      System.out.println("Photosynthesizing Plants: "/* + PS plant count*/);
      
      System.out.printf("/nWould you like to proceed to the next year, proceed by 5 years, or go to a specific menu?/n1 - Proceed 1 year./n2 - Proceed 5 years./n3 - Choose a specific menu.");
      
   }
   
   public void BalanceSheet(){
      
   }
   
   public void CO2Emission(){
      
   }
   
   public void PlantMerchant(){
      
   }
   
}