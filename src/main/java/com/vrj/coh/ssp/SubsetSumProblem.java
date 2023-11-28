package com.vrj.coh.ssp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class SubsetSumProblem {

    private static Integer SEED;
    private static Integer[] set;
    private static Integer target;
    private final static int iteraciones = 1100000;
    private static Solution bestSolution;
    private static int sizeTabuList = 100000;
    private static double PROBABILITY = 0.04;

    
    public static byte[] generateInitialSolution(ArrayList<Integer> numbers, long seed, double probability) {
        Random random = new Random(seed);
        byte[] initialSolution = new byte[numbers.size()];
    
        for (int i = 0; i < numbers.size(); i++) {
            if (random.nextDouble() < probability) {
                initialSolution[i] = 1;
            } else {
                initialSolution[i] = 0;
            }
        }
    
        return initialSolution;
    }

    private static void setAndTargetExtract(ArrayList<Integer> input){
       target = input.remove(input.size() - 1);
       set =  input.toArray(new Integer[input.size()]);
    }

    private static void tabuSearch(Solution solution){
        int cost = solution.getCost();
        int length = solution.sizeOfByteMap();

        for(int i = 0; i < iteraciones; i++){
            solution.neighbor();  
            String neighbor = Arrays.toString(solution.getByteMap());
            
            if (!solution.getTabuListReciently().contains(neighbor)) {
                if(solution.getCost() < cost){
                    cost = solution.getCost();
                    if(cost < bestSolution.getCost()){
                        bestSolution = solution.clone();
                    }
                }

                if (solution.getCost() == 0 && solution.sizeOfByteMap() < length) {
                    if (cost <= bestSolution.getCost()) {
                        bestSolution = solution.clone();
                    }
                }
                solution.getTabuListReciently().add(neighbor);
            } else{
                solution.unFlip();
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
        byte[] initialSolution = generateInitialSolution(numbers, SEED, PROBABILITY);
        Solution solution = new Solution(initialSolution, set, SEED, target, sizeTabuList);
        bestSolution = solution.clone();
            
        tabuSearch(solution);   

        System.out.println("Costo: " + bestSolution.getCost()+ " Suma: " 
                          + bestSolution.getSum() + " Tamaño: " + bestSolution.sizeOfByteMap()
                        + " Target: " + target);
    }

}
