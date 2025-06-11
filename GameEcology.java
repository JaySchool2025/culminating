public class GameEcology{
   int psTotal = psCount;
   double temp;
   double prevtemp;
   double co2;
   int TimeSincePDRMech;
   
   
   public void TempCalc(){
      
      if(co2 > 0){
         temp = prevtemp + (co2 / 100);
      }
      else if(co2 < 0){
         temp = prevtemp + (co2 / -1000);
      }
      else{
         temp = prevtemp;
      }
   }
   
   public void PlantDeathRegrowthMech(){
      if(TimeSincePDRMech >= 2 && temp >= 16.7 && temp < 17.7){
         psCount = psCount - 250;
         if(psCount < 0){
          psCount = 0;
         }
      }
      else if(TimeSincePDRMech >= 2 && temp >= 17.7){
         psCount = psCount - 500;
         if(psCount < 0){
          psCount = 0;
         }
      }
      else if(TimeSincePDRMech >= 2 && temp <= 16.2){
         psCount = psCount + 50;
         if(psCount > psTotal){
          psCount = psTotal;
         }
      }
   }
   
   
   public void TimeSincePDRMechInitiationCalc(){
      if(temp <= 16.2 && prevtemp <= 16.2 || temp >= 16.7 && prevtemp >= 16.7){
         TimeSincePDRMech = TimeSincePDRMech + 1;
      }
      else{
         TimeSincePDRMech = 0;
      }
      
   }
   
   public void OverheatEndingTracker(){
      boolean HeatEnd = false;
      
      if(temp <= TempEnding){
      
         HeatEnd = true;
         System.out.println("The player has reached a global temperature of 19.7°C. The simulation will now end as the player has overheated the world by enough to cause severe damage. Good luck, please try again.");
         System.exit(0);
         
      }
      else{
         HeatEnd = false;
      }
   }
   
}