package com.vrj.coh.ssp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Solution {

    
    private Integer[] integersArray;
    private byte[] byteMap;
    private int target;
    private int sum;
    private int cost;
    private static int index;
    private static Random random;
    private Queue<byte[]> tabuListReciently = new LinkedList<>();
    private int sizeTabuList;

    public Solution(byte[] byteMap, Integer[] integersArray, Integer seed, int target, int sizeTabuList){
        this.byteMap = byteMap;
        this.integersArray = integersArray;
        this.target = target;
        this.sizeTabuList = sizeTabuList;
        random = new Random(seed);
        this.costFunction();
    }

    public Integer[] getIntegersArray() {
        return integersArray;
    }

    public void setIntegersArray(Integer[] integersArray) {
        this.integersArray = integersArray;
    }

    public byte[] getbyteMap() {
        return byteMap;
    }

    public void setbyteMap(byte[] byteMap) {
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

    public Queue<byte[]> getTabuListReciently() {
        return tabuListReciently;
    }

    public void setTabuListReciently(Queue<byte[]> tabuListReciently) {
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

    private void checkSize(){
        if (this.tabuListReciently.size() > this.sizeTabuList) {
            tabuListReciently.remove();
        }
    }

    public void neighbor(){
        index = random.nextInt(integersArray.length);
        boolean value = random.nextBoolean();

        if(value){
            this.byteMap[index] = 1;
        } else {
            this.byteMap[index] = 0;
        }

        this.costFunction();
        this.tabuListReciently.add(this.byteMap.clone());
        this.checkSize();
    }

    public void unSwap(){
        if (this.byteMap[index] == 0) {
            this.byteMap[index] = 1;
        } else {
            this.byteMap[index] = 0;
        }
        this.costFunction();
    }

    public Solution clone(){
        return new Solution(this.byteMap.clone(), this.integersArray, random.nextInt(), this.target, this.sizeTabuList);
    }

    

    public int size(){ 
        int numUnos = 0;
        for(byte b : this.byteMap){
            if(b == 1){
                numUnos++;
            }
        }
        return numUnos;
    }
}
