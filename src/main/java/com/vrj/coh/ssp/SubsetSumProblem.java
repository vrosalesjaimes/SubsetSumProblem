package com.vrj.coh.ssp;

import java.util.ArrayList;
import java.util.Random;


public class SubsetSumProblem {

    private static Integer SEED;
    private static Integer[] set;
    private static Integer target;

    
    public static ArrayList<Integer> generateInitialSolution(ArrayList<Integer> numbers, long seed) {
        Random random = new Random(seed);
        ArrayList<Integer> initialSolution = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (random.nextBoolean()) {
                initialSolution.add(1);
            } else {
                initialSolution.add(0);
            }
        }

        return initialSolution;
    }

    private static void setAndTargetExtract(ArrayList<Integer> input){
       target = input.remove(input.size() - 1);
       set =  input.toArray(new Integer[input.size()]);
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
    

    }

}
