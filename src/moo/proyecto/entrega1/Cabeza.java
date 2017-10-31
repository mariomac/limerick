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
}
