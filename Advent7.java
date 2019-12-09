import java.util.concurrent.TimeUnit;

public class Advent7
{
    static final int ampCount = 5;
    
    static final int minPhase = 5;
    static final int maxPhase = 9;
    static final int defaultInput = 0;

    static final int[] part1_test1 = { 3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0 }; // result 43210 - phase 43210
    static final int[] part1_test2 = { 3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0 }; // result 54321 - phase 01234
    static final int[] part1_test3 = { 3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0 }; // result 65210 - phase 10432
    static final int[] program = { 3,8,1001,8,10,8,105,1,0,0,21,42,63,76,101,114,195,276,357,438,99999,3,9,101,2,9,9,102,5,9,9,1001,9,3,9,1002,9,5,9,4,9,99,3,9,101,4,9,9,102,5,9,9,1001,9,5,9,102,2,9,9,4,9,99,3,9,1001,9,3,9,1002,9,5,9,4,9,99,3,9,1002,9,2,9,101,5,9,9,102,3,9,9,101,2,9,9,1002,9,3,9,4,9,99,3,9,101,3,9,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99 };

    static final int[] part2_test1 = { 3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5 }; // 139,629,729 - phase 98765
    static final int[] part2_test2 = { 3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10 }; // 18216 - phase 98765
    
    public static void main(String[] args)
    {
        IntCode[] amps = new IntCode[ampCount];
        int maxOutput = Integer.MIN_VALUE;

        for(int i=0; i<ampCount; i++)
        {
            amps[i] = new IntCode();
        }

        for(int phaseA = minPhase; phaseA<=maxPhase; phaseA++)
        {
            for(int phaseB = minPhase; phaseB<=maxPhase; phaseB++)
            {
                if(phaseA==phaseB)
                    continue;

                for(int phaseC = minPhase; phaseC<=maxPhase; phaseC++)
                {
                    if(phaseC==phaseA || phaseC==phaseB)
                        continue;

                    for(int phaseD = minPhase; phaseD<=maxPhase; phaseD++)
                    {
                        if(phaseD==phaseA || phaseD==phaseB || phaseD==phaseC)
                            continue;

                        for(int phaseE = minPhase; phaseE<=maxPhase; phaseE++)
                        {
                            if(phaseE==phaseA || phaseE==phaseB || phaseE==phaseC || phaseE==phaseD)
                                continue;

                            System.out.println("Phases:" + phaseA + phaseB + phaseC + phaseD + phaseE);                                

                            // reset state
                            int output = 0;
                            for(int ampIndex=0; ampIndex<ampCount; ampIndex++) 
                            {
                                amps[ampIndex].reset();
                                amps[ampIndex].inputProgram(program); // <============= LOAD PROGRAM
                            }

                            // begin feedback loop
                            boolean feedback = true;
                            while(feedback)
                            {
                                output = amps[0].run(phaseA, output); 
                                output = amps[1].run(phaseB, output);
                                output = amps[2].run(phaseC, output);
                                output = amps[3].run(phaseD, output);
                                int tmp_output = amps[4].run(phaseE, output);
                                
                                if(amps[4].isDone()) 
                                {
                                    feedback = false;
                                    System.out.println("COMPLETE:" + output);
                                }
                                else
                                {
                                    output = tmp_output;
                                    System.out.println("CONTINUE:" + output);                                    
                                }
                            }
                            
                            if(output>maxOutput) 
                            {
                                maxOutput = output;
                                System.out.println("MaxOutput:" + maxOutput + ", Phases:" + phaseA + phaseB + phaseC + phaseD + phaseE);
                            }
                        }                        
                    }
                }
            }
        }

        System.out.println("MaxOutput:" + maxOutput);
    }
}
