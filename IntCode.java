import java.util.ArrayList;
import java.util.List;

public class IntCode
{
    final int opCodeAdd = 1;
    final int opCodeMult = 2;
    final int opCodeInput = 3;
    final int opCodeOutput = 4;      
    final int opCodeJumpIfTrue = 5;
    final int opCodeJumpIfFalse = 6;
    final int opCodeLessThan = 7;
    final int opCodeEquals = 8;
    final int opCodeEnd = 99;
    
    final int maxParamCount = 3;
    final int maxNumInputs = 2;
     
    int     ip           = 0;
    boolean done         = false;
    int     inputIndex   = 0;
    
    int [] params        = new int[maxParamCount+1];
    int opCodeSizes[]    = new int[opCodeEnd+1];
    int outputParam[]    = new int[opCodeEnd+1];
    List<Integer> memory = new ArrayList<Integer>();
    
    IntCode()
    {
        done = false;
        
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
        
        reset();
    }
    
    void inputProgram(int [] program)
    {
        memory.clear();
        for(int o : program) 
            memory.add(o);        
    }
    
    void reset()
    {
        ip = 0;
        done = false;
        inputIndex = 0;
    }
    
    boolean isDone()
    {
        return done;
    }
    
    void dumpMemory()
    {
        System.out.println("memory:" + memory);    
    }
    
    int run(int phase, int input)
    {
        int opCode    = 0;
        int result    = 0;
        int output    = input;
        
        int[] inputs = new int[maxNumInputs];
        inputs[0] = phase;
        inputs[1] = input;
        
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

                while(paramStr.length()<maxParamCount)
                {
                    paramStr = "0" + paramStr;
                }
                paramStr += "0"; // zeroeth parameter

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
                done = true;
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
                memory.set(params[1], inputs[inputIndex]);
                if(inputIndex==0)
                    inputIndex++;
                break;
            case opCodeOutput:
                // opCode 4
                // output param1 
                doLoop = false;
                output = memory.get(params[1]);
//                System.out.println("output:" + output);  
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
        
        return output;
    }

}
