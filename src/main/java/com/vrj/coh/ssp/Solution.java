package com.vrj.coh.ssp;

import java.util.Random;
import java.util.Set;

public class Solution {

    
    private Integer[] integersArray;
    private byte[] bitsMap;
    private int target;
    private int sum;
    private int cost;
    private static int index;
    private static Random random;
    private Set<byte[]> tabuListReciently;
    private Set<TuplaListaTabu> tabuListNoAcceptiing;

    public Solution(Integer[] initialSolution, Long seed){
        this.integersArray = initialSolution;
        random = new Random(seed);
        this.sum = 0;
        this.cost = 0;
    }

    public Integer[] getIntegersArray() {
        return integersArray;
    }

    public void setIntegersArray(Integer[] integersArray) {
        this.integersArray = integersArray;
    }

    public byte[] getBitsMap() {
        return bitsMap;
    }

    public void setBitsMap(byte[] bitsMap) {
        this.bitsMap = bitsMap;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<byte[]> getTabuListReciently() {
        return tabuListReciently;
    }

    public void setTabuListReciently(Set<byte[]> tabuListReciently) {
        this.tabuListReciently = tabuListReciently;
    }

    public Set<TuplaListaTabu> getTabuListNoAcceptiing() {
        return tabuListNoAcceptiing;
    }

    public void setTabuListNoAcceptiing(Set<TuplaListaTabu> tabuListNoAcceptiing) {
        this.tabuListNoAcceptiing = tabuListNoAcceptiing;
    }

    public void sum(){
        int sumOfSet = 0;
        for(int i = 0; i < this.integersArray.length; i++){
            sum += integersArray[i];
        }
        this.sum = sumOfSet;
    }

    public int costFunction() {
        this.sum();
        return Math.abs(this.sum - target);
    }

    public void modifiCost(){
        this.sum += integersArray[index];
        this.cost = Math.abs(this.sum - target);
    }

    public boolean neighbor() {
        index = random.nextInt(integersArray.length);
        TuplaListaTabu tuple = new TuplaListaTabu(this.bitsMap, index);
    
        if (this.tabuListNoAcceptiing.contains(tuple) || this.tabuListReciently.contains(this.bitsMap)) {
            return false;
        }
    
        boolean foundNeighbor = false;
    
        if (tryChangeBit(index, integersArray[index] > 0 && this.bitsMap[index] == 0, foundNeighbor)) {
            return true;
        }
    
        if (tryChangeBit(index, integersArray[index] < 0 && this.bitsMap[index] == 1, foundNeighbor)) {
            return true;
        }
    
        tabuListNoAcceptiing.add(tuple);
    
        return foundNeighbor;
    }
    
    private boolean tryChangeBit(int index, boolean condition, boolean foundNeighbor) {
        if (condition) {
            this.bitsMap[index] = (byte) ((this.bitsMap[index] == 0) ? 1 : 0);
            tabuListReciently.add(this.bitsMap);
            modifiCost();
            foundNeighbor = true;
        }
        return foundNeighbor;
    }
    
    
}
