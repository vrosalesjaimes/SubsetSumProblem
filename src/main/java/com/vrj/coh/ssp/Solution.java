package com.vrj.coh.ssp;

import java.util.Random;

public class Solution {

    private Integer[] integersArray;
    private byte[] byteMap;
    private int target;
    private int sum;
    private int cost;
    private static int index;
    private static Random random;
    private TabuList tabuListReciently;
    private TabuList prohibedList;

    public Solution(byte[] byteMap, Integer[] integersArray, Integer seed, int target, int sizeTabuList){
        this.byteMap = byteMap;
        this.integersArray = integersArray;
        this.target = target;
        random = new Random(seed);
        this.costFunction();
        this.tabuListReciently = new TabuList(sizeTabuList);
        this.prohibedList = new TabuList(sizeTabuList);
    }

    public Integer[] getIntegersArray() {
        return integersArray;
    }

    public void setIntegersArray(Integer[] integersArray) {
        this.integersArray = integersArray;
    }

    public byte[] getByteMap() {
        return byteMap;
    }

    public void setByteMap(byte[] byteMap) {
        this.byteMap = byteMap;
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

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Solution.index = index;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        Solution.random = random;
    }

    public TabuList getTabuListReciently() {
        return tabuListReciently;
    }

    public void setTabuListReciently(TabuList tabuListReciently) {
        this.tabuListReciently = tabuListReciently;
    }

    public TabuList getProhibedList() {
        return prohibedList;
    }

    public void setProhibedList(TabuList prohibedList) {
        this.prohibedList = prohibedList;
    }

    public void sum(){
        int sumOfSet = 0;
        for(int i = 0; i < this.integersArray.length; i++){
            if (this.byteMap[i] == 1) {
                sumOfSet += integersArray[i];
            }
        }
        this.sum = sumOfSet;
    }

    public void costFunction() {
        this.sum();
        this.cost = Math.abs(this.sum - target);
    }

    public void neighbor(){
        index = random.nextInt(integersArray.length);
        boolean probability = random.nextBoolean();

        if(probability){
            this.byteMap[index] = 1;
        } else {
            this.byteMap[index] = 0;
        }

        this.costFunction();
    }

    public void unFlip(){
        if (this.byteMap[index] == 0) {
            this.byteMap[index] = 1;
        } else {
            this.byteMap[index] = 0;
        }
        this.costFunction();
    }

    public int sizeOfByteMap(){ 
        int numUnos = 0;
        for(byte b : this.byteMap){
            if(b == 1){
                numUnos++;
            }
        }
        return numUnos;
    }

    public Solution clone(){
        return new Solution(this.byteMap.clone(), integersArray, 0, this.target, this.cost);
    }
}
