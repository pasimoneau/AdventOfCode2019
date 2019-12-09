
public class Advent4
{
    // =======================================================
    // DAY 4
    // =======================================================

    public static void main(String[] args)
    {
    
      int min = 240920;
      int max = 789857;
      
      int count = 0;
      
      for(int i=min; i<=max; i++)
      {
          String num = String.valueOf(i);
          
          boolean repeatingDigits = false;
          for(int j=0; j<=9; j++)
          {
              String twoDigitsStr   = "00";
              String threeDigitsStr = "000";
              if(j!=0) {
                  twoDigitsStr = String.valueOf(j*11);
                  threeDigitsStr = String.valueOf(j*111);
              }
              
              if(num.contains(twoDigitsStr) && !num.contains(threeDigitsStr))
              {
                  repeatingDigits = true;
                  break;
              }
          }
          
          if(!repeatingDigits)
              continue;
          
          char[] numChars = num.toCharArray();
          java.util.Arrays.sort(numChars);
          String sortedNum = new String(numChars);
          
          if(!sortedNum.equals(num))
              continue;
          
          count++;
      }
      
      System.out.println("count: " + count);
    }
}
