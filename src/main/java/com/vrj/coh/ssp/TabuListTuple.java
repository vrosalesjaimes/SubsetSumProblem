package com.vrj.coh.ssp;

import java.util.Arrays;

/**
 * This class represents a Tabu List Tuple.
 */
public class TabuListTuple {
    private byte[] bitMap;
    private int index;

    /**
     * Constructs a TabuListTuple with the given bit map and integer value.
     * @param bitMap The bit map to set.
     * @param index  The integer value to set.
     */
    public TabuListTuple(byte[] bitMap, int index) {
        this.bitMap = bitMap;
        this.index = index;
    }

    /**
     * Gets the bit map of this tuple.
     * @return The bit map.
     */
    public byte[] getBitMap() {
        return bitMap;
    }

    /**
     * Sets the bit map of this tuple.
     * @param bitMap The bit map to set.
     */
    public void setBitMap(byte[] bitMap) {
        this.bitMap = bitMap;
    }

    /**
     * Gets the integer index of this tuple.
     * @return The integer index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the integer index of this tuple.
     * @param index The integer index to set.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Calculates the hash code for this tuple.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        String s = this.toString();
        return s.hashCode();
    }

    /**
     * Checks if this tuple is equal to another object.
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TabuListTuple other = (TabuListTuple) obj;
        return Arrays.equals(bitMap, other.bitMap) && index == other.index;
    }

    /**
     * Returns a string representation of this tuple, concatenating the bit map and the index.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return Arrays.toString(bitMap) + index;
    }
}



