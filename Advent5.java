import java.util.ArrayList;
import java.util.List;

public class Advent5
{
    // =======================================================
    // DAY 5
    // =======================================================

    public static void main(String[] args)
    {
        List<Integer> memory = new ArrayList<Integer>();

        int opCode    = -1;
        int ip        = 0;
        int result    = 0;

        final int maxOpCode     = 99;
        final int maxParamCount = 3;

        int [] params     = new int[maxParamCount+1];
        int opCodeSizes[] = new int[maxOpCode+1];
        int outputParam[] = new int[maxOpCode+1];

        final int opCodeAdd = 1;
        final int opCodeMult = 2;
        final int opCodeInput = 3;
        final int opCodeOutput = 4;      
        final int opCodeJumpIfTrue = 5;
        final int opCodeJumpIfFalse = 6;
        final int opCodeLessThan = 7;
        final int opCodeEquals = 8;
        final int opCodeEnd = 99;

        opCodeSizes[opCodeEnd] = 1;
        opCodeSizes[opCodeAdd]  = 4;
        opCodeSizes[opCodeMult]  = 4;
        opCodeSizes[opCodeInput]  = 2;
        opCodeSizes[opCodeOutput]  = 2;
        opCodeSizes[opCodeJumpIfTrue]  = 3;
        opCodeSizes[opCodeJumpIfFalse]  = 3;
        opCodeSizes[opCodeLessThan]  = 4;
        opCodeSizes[opCodeEquals]  = 4;

        outputParam[opCodeAdd] = 3;
        outputParam[opCodeMult] = 3;
        outputParam[opCodeInput] = 1;
        outputParam[opCodeOutput] = 1;
        outputParam[opCodeJumpIfTrue] = 0;
        outputParam[opCodeJumpIfFalse] = 0;
        outputParam[opCodeLessThan] = 3;
        outputParam[opCodeEquals] = 3;
        //
        int [] test1 = { 1002,4,3,4,33,99 };
        int [] test2 = { 1101,100,-1,4,0,99 };
        int [] test3 = { 3,9,8,9,10,9,4,9,99,-1,8 };
        int [] test4 = { 3,9,7,9,10,9,4,9,99,-1,8 };
        int [] test5 = { 3,3,1108,-1,8,3,4,3,99 } ;
        int [] test6 = { 3,3,1107,-1,8,3,4,3,99 };

        int [] test7 = { 3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 };
        int [] test8 = { 3,3,1105,-1,9,1101,0,0,12,4,12,99,1 };
        int [] test9 = { 3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104, 999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99 };

        int [] program = { 3,225,1,225,6,6,1100,1,238,225,104,0,101,71,150,224,101,-123,224,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,2,205,209,224,1001,224,-3403,224,4,224,1002,223,8,223,101,1,224,224,1,223,224,223,1101,55,24,224,1001,224,-79,224,4,224,1002,223,8,223,101,1,224,224,1,223,224,223,1,153,218,224,1001,224,-109,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,1002,201,72,224,1001,224,-2088,224,4,224,102,8,223,223,101,3,224,224,1,223,224,223,1102,70,29,225,102,5,214,224,101,-250,224,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,1101,12,52,225,1101,60,71,225,1001,123,41,224,1001,224,-111,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1102,78,66,224,1001,224,-5148,224,4,224,1002,223,8,223,1001,224,2,224,1,223,224,223,1101,29,77,225,1102,41,67,225,1102,83,32,225,1101,93,50,225,1102,53,49,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1107,677,677,224,1002,223,2,223,1005,224,329,101,1,223,223,7,677,677,224,1002,223,2,223,1005,224,344,1001,223,1,223,7,226,677,224,102,2,223,223,1006,224,359,101,1,223,223,1108,226,226,224,1002,223,2,223,1005,224,374,1001,223,1,223,8,226,677,224,1002,223,2,223,1006,224,389,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,404,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,419,101,1,223,223,1007,677,677,224,1002,223,2,223,1005,224,434,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,449,1001,223,1,223,1008,226,677,224,1002,223,2,223,1006,224,464,101,1,223,223,8,677,677,224,1002,223,2,223,1006,224,479,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,494,101,1,223,223,1107,226,677,224,1002,223,2,223,1006,224,509,101,1,223,223,107,226,226,224,1002,223,2,223,1006,224,524,1001,223,1,223,107,677,677,224,1002,223,2,223,1005,224,539,101,1,223,223,1007,226,226,224,102,2,223,223,1006,224,554,101,1,223,223,108,677,677,224,102,2,223,223,1005,224,569,101,1,223,223,107,677,226,224,102,2,223,223,1005,224,584,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,599,101,1,223,223,1108,677,226,224,1002,223,2,223,1006,224,614,101,1,223,223,8,677,226,224,102,2,223,223,1005,224,629,1001,223,1,223,1008,677,677,224,102,2,223,223,1006,224,644,101,1,223,223,1007,226,677,224,102,2,223,223,1005,224,659,101,1,223,223,108,226,677,224,102,2,223,223,1006,224,674,101,1,223,223,4,223,99,226 };
        //
        memory.clear();
        for(int o : program) // <============== LOAD PROGRAM
                memory.add(o);
        
        int input = 5;  // <=============== INPUT
        
        ip = 0;              
        boolean doLoop = true;
        while(doLoop)
        {
            try
            {
                String instStr = String.valueOf(memory.get(ip));
                String paramStr = "";
                String opCodeStr = null;
                if(instStr.length()<=2)
                {
                    opCodeStr = instStr;
                }
                else
                {
                    opCodeStr = instStr.substring(instStr.length()-2, instStr.length());
                    paramStr = instStr.substring(0, instStr.length()-2);
                }
                //
                while(paramStr.length()<maxParamCount)
                {
                    paramStr = "0" + paramStr;
                }
                paramStr += "0";

                // invert to restore sanity to parameter values
                StringBuilder builder = new StringBuilder();
                builder.append(paramStr);
                builder = builder.reverse();

                opCode = Integer.parseInt(opCodeStr);              
                for(int i=1; i<opCodeSizes[opCode]; i++)
                {
                    if(outputParam[opCode]==i || builder.charAt(i)=='1')
                    {
                        params[i] = memory.get(ip+i);
                    }
                    else if(builder.charAt(i)=='0')
                    {
                        params[i] = memory.get(memory.get(ip+i));
                    }
                }                  
            }
            catch(Throwable th)
            {
                System.out.println("exception");        
            }

            boolean advanceIp = true;
            switch(opCode)
            {
            case opCodeEnd:   
                // opCode 99
                doLoop = false;
                break;
            case opCodeAdd:
                // opCode 1
                // add param1+param2, output to param3
                result = params[1] + params[2];
                memory.set(params[3], result);
                break;
            case opCodeMult: 
                // opCode 2
                // multiple param1*param2, output to param3
                result = params[1] * params[2];
                memory.set(params[3], result);
                break;
            case opCodeInput:
                // opCode 3
                // set param1 from input
                memory.set(params[1], input);
                break;
            case opCodeOutput:
                // opCode 4
                // output param1
                result = memory.get(params[1]);
                System.out.println("output:" + result);          
                break;
            case opCodeJumpIfTrue:
                // opCode 5
                // set IP to param2's address if param1 is non-zero
                result = params[1];
                if(result!=0) 
                {
                    advanceIp = false;
                    ip = params[2];
                }
                break;
            case opCodeJumpIfFalse:
                // opCode 6
                // set IP to param2's address if param1 is zero
                result = params[1];
                if(result==0) 
                {
                    advanceIp = false;
                    ip = params[2];
                }
                break;
            case opCodeLessThan:
                // opCode 7
                // if param1<param2 then store 1 at param3 address, else store 0 at param3 address
                result = 0;
                if(params[1] < params[2])
                {
                    result = 1;
                }
                memory.set(params[3], result);
                break;
            case opCodeEquals:
                // opCode 8
                // if param1==param2 then store 1 at param3 address, else store 0 at param3 address
                result = 0;
                if(params[1] == params[2])
                {
                    result = 1;
                }
                memory.set(params[3], result);
                break;                  
            default:
                doLoop = false;
                System.out.println("unknown opcode: " + opCode + " at position:" + ip);        
            }

            if(advanceIp)
                ip += opCodeSizes[opCode];
        }

        System.out.println("memory:" + memory);
    }
}
