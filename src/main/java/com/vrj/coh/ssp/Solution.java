package com.vrj.coh.ssp;

import java.util.ArrayList;
import java.util.Random;

public class Solution {

    
    private Integer[] subset;
    private Long seed;
    private int target;
    private int sum;
    private int cost;
    private static Random random;
    

    public Solution(Integer[] initialSolution, Long seed){
        this.subset = initialSolution;
        random = new Random(seed);
        this.sum = 0;
        this.cost = 0;
    }

    public void sum(){
        int sumOfSet = 0;
        for(int i = 0; i < this.subset.length; i++){
            sum += subset[i];
        }
        this.sum = sumOfSet;
    }

    public int costFunction() {
        this.sum();
        return Math.abs(this.sum - target);
    }

    public void neighbor(){
        int indice = random.nextInt(subset.length);

        if()


    }
    
}
