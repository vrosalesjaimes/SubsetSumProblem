package com.vrj.coh.ssp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * This class represents a Tabu List.
 */
public class TabuList {
    private Queue<String> tabuList;
    private Set<String> tabuSet;
    private int sizeTabuList;

    /**
     * Constructs a TabuList with the specified size.
     * @param sizeTabuList The size of the Tabu List.
     */
    public TabuList(int sizeTabuList) {
        this.sizeTabuList = sizeTabuList;
        this.tabuList = new LinkedList<>();
        this.tabuSet = new HashSet<>();
    }

    /**
     * Gets the Tabu List.
     * @return The Tabu List.
     */
    public Queue<String> getTabuList() {
        return tabuList;
    }

    /**
     * Sets the Tabu List.
     * @param tabuList The Tabu List to set.
     */
    public void setTabuList(Queue<String> tabuList) {
        this.tabuList = tabuList;
    }

    /**
     * Gets the Tabu Set.
     * @return The Tabu Set.
     */
    public Set<String> getTabuSet() {
        return tabuSet;
    }

    /**
     * Sets the Tabu Set.
     * @param tabuSet The Tabu Set to set.
     */
    public void setTabuSet(Set<String> tabuSet) {
        this.tabuSet = tabuSet;
    }

    /**
     * Gets the size of the Tabu List.
     * @return The size of the Tabu List.
     */
    public int getSizeTabuList() {
        return sizeTabuList;
    }

    /**
     * Sets the size of the Tabu List.
     * @param sizeTabuList The size of the Tabu List to set.
     */
    public void setSizeTabuList(int sizeTabuList) {
        this.sizeTabuList = sizeTabuList;
    }

    /**
     * Adds a solution to the Tabu List.
     * @param solution The solution to add.
     */
    public void add(String solution) {
        if (tabuList.size() == sizeTabuList) {
            String removed = tabuList.remove();
            tabuSet.remove(removed);
        }
        tabuList.add(solution);
        tabuSet.add(solution);
    }

    /**
     * Checks if the Tabu List contains a solution.
     * @param solution The solution to check.
     * @return True if the solution is in the Tabu List, false otherwise.
     */
    public boolean contains(String solution) {
        return tabuSet.contains(solution);
    }
}
