import java.io.*;
import java.util.*;

public class GameMain{
   
   Scanner s = new Scanner(System.in);
   
   public void FirstMessage(){
      
      System.out.printf("Welcome to the climate simulation.\nBefore we start, would you like to go over some of the information/features of this game?\n");
      
      String confirmation = s.nextLine();
      confirmation = confirmation.toLowerCase();
      
      if(confirmation.equals("yes")){
         
         System.out.print("WIP collaboration with sim setup file");
         OverviewTimePassage();
         
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
      
      //if(/*balance < 0*/){
      
        // System.out.printf("\n\nBalance is negative. Exact value: \d. Please be wary of this."/* + balance*/);
      
      //}
      //if(/*co2 emissions >= 1*/){
         
      //   System.out.printf("\n\nCO2 Emissions are above 1. Exact value: \.2f. Please be wary of this."/* + co2 emissions*/);
         
      //}
      //if(/*temperature >= 15.2*/){
      
      //   System.out.printf("\n\nTemperature is above 15.2°C. Exact value: \.1f. Please be wary of this."/* + temperature*/);
      
      //}
      
      System.out.printf("\nWould you like to proceed to the next year, proceed by 5 years, or go to a specific menu?\n1 - Proceed 1 year.\n2 - Proceed 5 years.\n3 - Choose a specific menu.\n");
      int choice = s.nextInt();
      
      if(choice == 1){
         
         System.out.println("proceed 1 year, call methods to proceed");
         OverviewTimePassage();
         
      }
      else if(choice == 2){
         
         System.out.println("proceed 5 years, call methods to proceed 5 times");
         OverviewTimePassage();
         
      }
      else if(choice == 3){
         boolean loop = true;
         while(loop = true){
         System.out.printf("\nPlease choose the menu that you would like to go to.\n1 - Detailed Balance Sheet of Energy Income and Plant Maintenance.\n2 - Detailed Sheet for CO2 Emissions and Absorption.\n3 - Menu for buying & selling Plants (All Types).\n4 - Return to Overview.\n");
         int menuchoice = s.nextInt();
         
         if(menuchoice == 1){
            
            loop = false;
            BalanceSheet();
            
         }
         else if(menuchoice == 2){
            
            loop = false;
            CO2Emission();
            
         }
         else if(menuchoice == 3){
            
            loop = false;
            PlantMerchant();
            
         }
         else if(menuchoice == 4){
            
            loop = false;
            OverviewTimePassage();
            
         }
         else{
            
            System.out.println("Please enter a value between 1 and 4. The input you provided is invalid. The question will be asked again.");
         
         }
         }
      }
      else{
         
         System.out.println("Please enter a value between 1 and 3. The input you provided is invalid. The question will be asked again.");
         OverviewTimePassage();
         
      }
   }
   
   public void BalanceSheet(){
      System.out.println("Placeholder Balance Sheet");
      OverviewTimePassage();
   }
   
   public void CO2Emission(){
      System.out.println("Placeholder CO2 Emissions");
      OverviewTimePassage();
   }
   
   public void PlantMerchant(){
      System.out.println("Placeholder Plant Merchant");
      OverviewTimePassage();
   }
   
   public static void main (String[] args){
      GameMain g = new GameMain();
      g.FirstMessage();
   }
   
}