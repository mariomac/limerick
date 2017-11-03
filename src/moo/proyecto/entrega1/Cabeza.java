package moo.proyecto.entrega1;

/**
 *
 * @author mmacias
 */

/**
 * Clase para gestionar al personaje que se moverá por el tablero.
 *
 */
public class Cabeza {

    private int fila;

    private int columna;

    private int subidas = 0;

    public Cabeza(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public boolean puedeSubir() {
        return subidas < Const.MAX_SUBIDA;
    }

    public void incrementaSubidas() {
        subidas++;
    }
    public void resetSubidas() {
        subidas = 0;
    }
}
