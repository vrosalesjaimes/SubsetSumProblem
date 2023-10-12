package com.vrj.coh.ssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;



public class SubsetSumProblem {

    public static ArrayList<Double> readNumbersFromFile(String fileName) {
        ArrayList<Double> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    try {
                        double number = Double.parseDouble(tokens[i]);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir el número: " + tokens[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return numbers;
    }

    public static ArrayList<Double> generateInitialSolution(ArrayList<Double> numbers, long seed) {
        Random random = new Random(seed);
        ArrayList<Double> initialSolution = new ArrayList<>();

        for (Double number : numbers) {
            if (random.nextBoolean()) {
                initialSolution.add(number);
            }
        }

        return initialSolution;
    }

    public static double costFunction(ArrayList<Double> subset, double target) {
        double subsetSum = 0;
        for (Double num : subset) {
            subsetSum += num;
        }
        
        return Math.abs(subsetSum - target);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorrecto. Debes proporcionar el nombre del archivo como argumento.");
            return;
        }

        String fileName = args[0];
        long seed = Long.parseLong(args[1]);

        ArrayList<Double> numbers = readNumbersFromFile(fileName);
        ArrayList<Double> initialSolution = generateInitialSolution(numbers, seed);
        
    }

}
