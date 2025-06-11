public class GameEconomy{
   
   int cCount
   int ngCount
   int nCount
   int gCount
   int psCount
   
   //c = coal, ng = natural gas, n = nuclear, g = green, ps = photosynthesising, En = energy, Ma = maintenance, Pen = penalty
   
   public void IncomeCalc(){
      double income = (((cCount * cEn) + (ngCount * ngEn) + (nCount * nEn) + (gCount * gEn)) * 100);
      // 100 = 100M
   }
   
   public void MaintenanceCalc(){
      double maintenance = ((cCount * cMa) + (ngCount * ngMa) + (nCount * nMa) + (gCount * gMa));
   }
   
   public void BalanceAndUpkeep(){
      double balance = (income * EnPen) - (maintenance * MaPen);
   }
   
   public void PenaltyTracker(){
      double EnPen;
      double MaPen;
      
      if(temp >= 17.2 && temp < 18.2){
         
        EnPen = FirstEnergy;
        MaPen = 1;
         
      }
      else if(temp >= 18.2 && temp < 18.7){
         
         EnPen = SecondEnergy;
         MaPen = FirstCost;
         
      }
      else if (temp >= 18.7){
         
         EnPen = ThirdEnergy;
         MaPen = SecondCost;
         
      }
      else{
         
         EnPen = 1;
         MaPen = 1;
      
      }
      
   }
   
   public void DebtEndingTracker(){
      boolean DebtEnd = false;
      
      if(playerbalance <= DebtEnding){
      
         DebtEnd = true;
         System.out.println("The player has reached a balance of $-10,000,000. The simulation will now end as the player has gone sufficiently into debt. Good luck, please try again.");
         System.exit(0);
         
      }
      else{
         DebtEnd = false;
      }
      
   }
   
}