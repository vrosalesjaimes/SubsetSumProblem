package com.vrj.coh.ssp;

import java.util.Arrays;

public class TuplaListaTabu {
    private byte[] mapaDeBits;
    private int valorEntero;

    public TuplaListaTabu(byte[] mapaDeBits, int valorEntero) {
        this.mapaDeBits = mapaDeBits;
        this.valorEntero = valorEntero;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(mapaDeBits) ^ Integer.hashCode(valorEntero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TuplaListaTabu other = (TuplaListaTabu) obj;
        return Arrays.equals(mapaDeBits, other.mapaDeBits) && valorEntero == other.valorEntero;
    }
}
