package com.vrj.coh.ssp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Solution {

    
    private Integer[] integersArray;
    private byte[] byteMap;
    private int target;
    private int sum;
    private int cost;
    private static int index;
    private static Random random;
    private Queue<byte[]> tabuListReciently = new LinkedList<>();
    private Set<TuplaListaTabu> tabuListNoAcceptiing = new HashSet<>();
    private int sizeTabuList;

    public Solution(byte[] byteMap, Integer[] integersArray, Integer seed, int target, int sizeTabuList){
        this.byteMap = byteMap;
        this.integersArray = integersArray;
        this.target = target;
        this.sizeTabuList = sizeTabuList;
        random = new Random(seed);
        this.cost = this.costFunction();
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
        this.sum();
    }

    private void checkSize(){
        if (this.tabuListReciently.size() > this.sizeTabuList) {
            tabuListReciently.remove();
        }
    }

    public boolean neighbor() {
        index = random.nextInt(integersArray.length);
        TuplaListaTabu tuple = new TuplaListaTabu(this.byteMap, index);
    
        if (this.tabuListNoAcceptiing.contains(tuple) || this.tabuListReciently.contains(this.byteMap)) {
           return false;
        }
    
        boolean foundNeighbor = false;
    
        if (tryChangeBit(index, integersArray[index] > 0 && this.byteMap[index] == 0, foundNeighbor)) {
            return true;
        }
    
        if (tryChangeBit(index, integersArray[index] < 0 && this.byteMap[index] == 1, foundNeighbor)) {
            return true;
        }
    
        tabuListNoAcceptiing.add(tuple.clone());
    
        return foundNeighbor;
    }

    public void unSwap(){
        if (this.byteMap[index] == 0) {
            this.byteMap[index] = 1;
        } else {
            this.byteMap[index] = 0;
        }
        this.modifiCost();
    }
    
    private boolean tryChangeBit(int index, boolean condition, boolean foundNeighbor) {
        if (condition) {
            this.byteMap[index] = (byte) ((this.byteMap[index] == 0) ? 1 : 0);
            tabuListReciently.add(this.byteMap.clone());
            this.modifiCost();
            foundNeighbor = true;
            this.checkSize();
        }
        return foundNeighbor;
    }
    
    
}
