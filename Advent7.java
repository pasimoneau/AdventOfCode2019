public class Advent7
{
    static final int maxPhase = 5;
    static final int defaultInput = 0;

    static final int[] test1 = { 3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0 }; // result 43210 - phase 43210
    static final int[] test2 = { 3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0 }; // result 54321 - phase 01234
    static final int[] test3 = { 3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0 }; // result 65210 - phase 10432
    static final int[] program = { 3,8,1001,8,10,8,105,1,0,0,21,42,63,76,101,114,195,276,357,438,99999,3,9,101,2,9,9,102,5,9,9,1001,9,3,9,1002,9,5,9,4,9,99,3,9,101,4,9,9,102,5,9,9,1001,9,5,9,102,2,9,9,4,9,99,3,9,1001,9,3,9,1002,9,5,9,4,9,99,3,9,1002,9,2,9,101,5,9,9,102,3,9,9,101,2,9,9,1002,9,3,9,4,9,99,3,9,101,3,9,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99 };

    public static void main(String[] args)
    {
        IntCode[] intCodes = new IntCode[maxPhase];
        int maxOutput = Integer.MIN_VALUE;

        for(int i=0; i<maxPhase; i++)
        {
            intCodes[i] = new IntCode();
            intCodes[i].inputProgram(program); // <============= LOAD PROGRAM
        }

        for(int phase0 = 0; phase0<maxPhase; phase0++)
        {
            for(int phase1 = 0; phase1<maxPhase; phase1++)
            {
                if(phase0==phase1)
                    continue;

                for(int phase2 = 0; phase2<maxPhase; phase2++)
                {
                    if(phase2==phase0 || phase2==phase1)
                        continue;

                    for(int phase3 = 0; phase3<maxPhase; phase3++)
                    {
                        if(phase3==phase0 || phase3==phase1 || phase3==phase2)
                            continue;

                        for(int phase4 = 0; phase4<maxPhase; phase4++)
                        {
                            if(phase4==phase0 || phase4==phase1 || phase4==phase2 || phase4==phase3)
                                continue;

                            System.out.println("Phases:" + phase0 + phase1 + phase2 + phase3 + phase4);

                            int output = intCodes[4].run(phase4, intCodes[3].run(phase3, intCodes[2].run(phase2, intCodes[1].run(phase1, intCodes[0].run(phase0, defaultInput)))));
                            if(output>maxOutput) 
                            {
                                maxOutput = output;
                                System.out.println("MaxOutput:" + maxOutput + ", Phases:" + phase0 + phase1 + phase2 + phase3 + phase4);
                            }
                        }                        
                    }
                }
            }
        }

        System.out.println("MaxOutput:" + maxOutput);
    }
}
