import java.util.ArrayList;
import java.util.List;

public class Advent2
{
    public static void main(String[] args)
    {
        
    // =======================================================
    // DAY 2
    // =======================================================

   int [] test1 = {1,9,10,3,2,3,11,0,99,30,40,50};  
   int [] test2 = {1,0,0,0,99};
   int [] test3 = {2,3,0,3,99};
   int [] test4 = {2,4,4,5,99,0};
   int [] test5 = {1,1,1,4,99,5,6,0,99};
   List<Integer> memory = new ArrayList<Integer>();
   
   int opCode = -1;
   int param1 = -1;
   int param2 = -1;
   int param3 = -1;    
   int val1 = -1;
   int val2 = -1;
   int instructionPointer = 0;
   int result;
//
   int [] program = { 
           1,0,0,3, 
           1,1,2,3, 
           1,3,4,3, 
           1,5,0,3, 
           2,9,1,19,
           1,5,19,23,
           2,9,23,27,
           1,27,5,31,
           2,31,13,35,
           1,35,9,39,
           1,39,10,43,
           2,43,9,47,
           1,47,5,51,
           2,13,51,55,
           1,9,55,59,
           1,5,59,63,
           2,6,63,67,
           1,5,67,71,
           1,6,71,75,
           2,9,75,79,
           1,79,13,83,
           1,83,13,87,
           1,87,5,91,
           1,6,91,95,
           2,95,13,99,
           2,13,99,103,
           1,5,103,107,
           1,107,10,111,
           1,111,13,115,
           1,10,115,119,
           1,9,119,123,
           2,6,123,127,
           1,5,127,131,
           2,6,131,135,
           1,135,2,139,
           1,139,9,0,
           99,2,14,0,
           0 };
   
   int finalAnswer = 19690720;
   
   for(int noun=1; noun<50; noun++)
   {
       for(int verb=1; verb<50; verb++)
       {
           System.out.println("noun:" + noun + ", verb:" + verb);             
//
           memory.clear();
           for(int o : program)
               memory.add(o);
           
           instructionPointer = 0;
           memory.set(1, noun);
           memory.set(2, verb);
           
           boolean doLoop = true;
           while(doLoop)
           {
               try
               {
                   opCode = memory.get(instructionPointer);
                   param1 = memory.get(instructionPointer+1);
                   param2 = memory.get(instructionPointer+2);
                   param3 = memory.get(instructionPointer+3); 
                   val1 = memory.get(param1);
                   val2 = memory.get(param2);
               }
               catch(Throwable th)
               {
                   System.out.println("exception");        
               }
                        
               switch(opCode)
               {
               case 1:
                   result = val1+val2;
                   memory.set(param3, result);
//                       System.out.println("prog[" + param1 + "]=" + val1 + "+" + "prog[" + param2 + "]=" + val2 + " = " + result + " to position:" + param3);        
                   break;
               case 2: 
                   result = val1*val2;
                   memory.set(param3, result);
//                       System.out.println("prog[" + param1 + "]=" + val1 + "*" + "prog[" + param2 + "]=" + val2 + " = " + result + " to position:" + param3);        
                   break;
               case 99 : 
                   doLoop = false;
                   break;
               default:
                   doLoop = false;
                   System.out.println("unknown opcode: " + opCode + " at position:" + instructionPointer);        
               }
               instructionPointer += 4;
           }
//
           int answer = memory.get(0);
           System.out.println("answer:" + answer + ", final state: " + memory.toString());             
           
           if(memory.get(0)==finalAnswer) {
               System.out.println("ANSWER:" + (100*noun) + verb);                              
               return;
           }
       }
   }
  }
   
}
