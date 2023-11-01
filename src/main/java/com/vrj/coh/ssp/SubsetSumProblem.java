package com.vrj.coh.ssp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class SubsetSumProblem {

    private static Integer SEED;
    private static Integer[] set;
    private static Integer target;
    private final static int iteraciones = 10;
    private static Solution bestSolution;
    private static int sizeTabuList = 1000000;
    private static double PROBABILITY = 0.04;

    
    public static byte[] generateInitialSolution(ArrayList<Integer> numbers, long seed, double probability) {
        Random random = new Random(seed);
        byte[] initialSolution = new byte[numbers.size()];
    
        for (int i = 0; i < numbers.size(); i++) {
            if (random.nextDouble(1) < probability) {
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
                solution.getTabuListReciently().add(neighbor);
            } else{
                solution.unFlip();
            }
        }
    }

    private static void tabuSearchRestricted(Solution solution){
        int cost = solution.getCost();

        for(int i = 0; i < iteraciones; i++){
            boolean hayVecino = solution.neighborRestrict();

            if (hayVecino && !solution.getProhibedList().contains(Arrays.toString(solution.getByteMap()))
                          && !solution.getTabuListReciently().contains(Arrays.toString(solution.getByteMap()))) {
                if(solution.getCost() < cost){
                    cost = solution.getCost();
                    if(cost < bestSolution.getCost()){
                        bestSolution = solution.clone();
                    }
                }
                solution.getProhibedList().add(Arrays.toString(solution.getByteMap()));
            } else{
                //solution.unFlip();
                
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso incorrecto. Debes proporcionar el nombre del archivo como argumento.");
            return;
        }

        String bandera = args[0];
        String fileName = args[1];
        SEED = Integer.valueOf(args[2]);

        Lector lector = new Lector(fileName);

        ArrayList<Integer> numbers = lector.readNumbersFromFile();
        setAndTargetExtract(numbers);
        byte[] initialSolution = generateInitialSolution(numbers, SEED, PROBABILITY);
        Solution solution = new Solution(initialSolution, set, SEED, target, sizeTabuList);
        bestSolution = solution.clone();
        if (bandera.equals("-n")) {
            tabuSearch(solution);   
        }

        else if(bandera.equals("-r")){
            tabuSearchRestricted(solution);
        }
        else{
            return;
        }

        System.out.println(SEED + "," + bestSolution.getCost()+ "," + bestSolution.getSum() + "," + bestSolution.sizeOfByteMap());
    }

}
