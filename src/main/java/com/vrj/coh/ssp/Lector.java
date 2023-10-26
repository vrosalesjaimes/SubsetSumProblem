/**
 * Class that provides methods for reading numbers from a file.
 */
package com.vrj.coh.ssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lector {
    /* The name of the file. */
    private String fileName;

    /**
     * Constructor for the Lector class.
     *
     * @param fileName The name of the file from which numbers will be read.
     */
    public Lector(String fileName){
        this.fileName = fileName;
    }

    /**
     * Reads numbers from a file and stores them in a list of integers.
     *
     * @param fileName The name of the file containing numbers separated by commas.
     * @return An ArrayList of integers containing the numbers read from the file.
     */
    public ArrayList<Integer> readNumbersFromFile() {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    try {
                        int number = Integer.valueOf(tokens[i]);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        System.err.println("Error converting number: " + tokens[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return numbers;
    }
}
