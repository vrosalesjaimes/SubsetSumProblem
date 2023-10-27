package com.vrj.coh.ssp;

import java.util.ArrayList;
import java.util.Random;


public class SubsetSumProblem {

    private static Integer SEED;
    private static Integer[] set;
    private static Integer target;
    private final static int iteraciones = 100000;
    private static Solution bestSolution;
    private static int sizeTabuList = 1000;

    
    public static byte[] generateInitialSolution(ArrayList<Integer> numbers, long seed) {
        Random random = new Random(seed);
        byte[] initialSolution = new byte[numbers.size()];

        for (int i = 0; i < numbers.size(); i++) {
            if (random.nextBoolean()) {
                initialSolution[i] = 1;
            } else {
                initialSolution[i]= 0 ;
            }
        }

        return initialSolution;
    }

    private static void setAndTargetExtract(ArrayList<Integer> input){
       target = input.remove(input.size() - 1);
       set =  input.toArray(new Integer[input.size()]);
    }

    private static void tabuSearch(Solution solution){
        for(int i = 0; i < iteraciones; i++){
            int cost = solution.getCost();
            boolean hayVecino = solution.neighbor();
            if (hayVecino) {
                if(cost < solution.getCost()){
                    System.out.println(cost);
                    if(solution.getCost() < bestSolution.getCost()){
                        bestSolution = solution;
                    }
                }
            } else{
                solution.unSwap();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorrecto. Debes proporcionar el nombre del archivo como argumento.");
            return;
        }

        String fileName = args[0];
        SEED = Integer.valueOf(args[1]);

        Lector lector = new Lector(fileName);

        ArrayList<Integer> numbers = lector.readNumbersFromFile();
        setAndTargetExtract(numbers);
        byte[] initialSolution = generateInitialSolution(numbers, SEED);

        Solution solution = new Solution(initialSolution, set, SEED, target, sizeTabuList);
        bestSolution = solution;
        tabuSearch(solution);
        System.out.println(bestSolution.getCost());
    }

}
