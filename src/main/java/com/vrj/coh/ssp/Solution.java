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
    private static int lastOperation = 0; 
    private static int firstIndex = -1;
    private static int secondIndex = -1;

    public Solution(byte[] byteMap, Integer[] integersArray, Integer seed, int target, int sizeTabuList){
        this.byteMap = byteMap;
        this.integersArray = integersArray;
        this.target = target;
        random = new Random(seed);
        this.costFunction();
        this.tabuListReciently = new TabuList(sizeTabuList);
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
        double randomValue = random.nextDouble(1);

        if (randomValue < 0.9) {
                if (this.sum < this.target) {
                    int indexToAdd = random.nextInt(this.integersArray.length);
                    if (this.byteMap[indexToAdd] == 0) {
                        this.byteMap[indexToAdd] = 1;
                        lastOperation = 1;
                        firstIndex = indexToAdd;
                        this.costFunction();
                        return;
                    }
                }else {
                    int indexToRemove = random.nextInt(this.integersArray.length);
                    if (this.byteMap[indexToRemove] == 1) {
                        this.byteMap[indexToRemove] = 0;
                        lastOperation = 2;
                        firstIndex = indexToRemove;
                        this.costFunction();
                        return;
                    }
                } 
        }
        else {
            int index1 = random.nextInt(this.integersArray.length);
            int index2 = random.nextInt(this.integersArray.length);

            if (this.byteMap[index1] == 1 && this.byteMap[index2] == 0
            || this.byteMap[index1] == 0 && this.byteMap[index2] == 1) {
                this.byteMap[index1] = 0;
                this.byteMap[index2] = 1;
                lastOperation = 3;
                firstIndex = index1;
                secondIndex = index2;
                this.costFunction();
                return;
            }
        } 
    }

    public void unFlip(){
        switch (lastOperation) {
            case 1:
                this.byteMap[firstIndex] = 0;
                break;
            case 2:
                this.byteMap[firstIndex] = 1;
                break;
            case 3:
                this.byteMap[firstIndex] = (this.byteMap[firstIndex] == 0) ? (byte) 1 : 0;
                this.byteMap[secondIndex] = (this.byteMap[secondIndex] == 0) ? (byte) 1 : 0;
                break;
            default:
                break;
        }
    }

    public void flip(int index){
        this.byteMap[index] = (this.byteMap[index] == 0) ? (byte) 1 : 0;
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

    public String toString(){
        String s = "";

         for(int i=0; i < this.byteMap.length; i++){
            if(byteMap[i] == 1){
                s += (this.integersArray[i] + " ,");
            }
        }

        return s;
    }

    public Solution clone(){
        return new Solution(this.byteMap.clone(), integersArray, 0, this.target, this.cost);
    }
}
