package moo.proyecto.entrega1;

/**
 * Clase para gestionar objetos Celda, que conformarán el recinto de juego.
 */
public class Celda {
    /**
     * Fila en la que se encuentra la celda
     */
    private int fila;
    /**
     * Columna en la que se encuentra la celda
     */
    private int columna;

    /**
     * Tipo de celda, cuyos valores podran ser {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA},
     * {@link Const#CELDA_VACIA}, {@link Const#CELDA_PARED}, {@link Const#CELDA_CAJA}
     */
    private char tipo;

    public void vaciar() {
        this.tipo = Const.CELDA_VACIA;
    }

    /**
     * Constructor: inicializa el objeta Celda que contiene un objeto del tipo dado.
     * dado
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @param tipo  el tipo de objeto que contiene la celda
     */
    public Celda(int fila, int columna, char tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    /**
     * Retorna si el jugador pasado por parámetro puede pasar por la celda en cuestión. Podrá
     * pasar si en la celda no hay ningún objeto, o si hay una caja que puede desplazarse en la
     * dirección donde el jugador va (porque la siguiente casilla está vacía).
     * @param j la instancia del {@link Cabeza}
     * @return {@code true} si puede pasar. {@code false} en caso contrario
     */
    public boolean puedePasar(Cabeza j, Celda[][] mapaCeldas) {
        switch (tipo) {
            case Const.CELDA_VACIA:
            case Const.CELDA_MANZANA:
                return true;
            case Const.CELDA_PARED:
            case Const.CELDA_CUERPO:
                return false;
            case Const.CELDA_CAJA:
                // Si la serpiente viene por la izquierda, puede pasar solo si a la derecha no hay nada
                // Si la serpiente viene por la derecha, puede pasar solo si a la izquierda no hay nada
                return (j.getColumna() < columna
                        && mapaCeldas[fila][columna + 1].tipo == Const.CELDA_VACIA)
                       || (j.getColumna() > columna
                           && mapaCeldas[fila][columna - 1].tipo == Const.CELDA_VACIA);
            default:
                return false;
        }
    }

    /**
     * Realiza la acción correspondiente que tiene lugar cuando el jugador pasa por esa celda
     * @param j  la instancia del objeto {@link Cabeza}
     * @return {@code true} si el jugador ha llegado al final de un nivel (es decir, ha entrado
     *         por la puerta. {@code false} en caso contrario
     */
    public boolean pasa(Cabeza j, Celda[][] mapaCeldas) {
        switch (tipo) {
            case Const.CELDA_MANZANA:
                return true;
            case Const.CELDA_CAJA:
                if (j.getColumna() < columna) {
                    mapaCeldas[fila][columna + 1].tipo = Const.CELDA_CAJA;
                } else if (j.getColumna() > columna) {
                    mapaCeldas[fila][columna - 1].tipo = Const.CELDA_CAJA;
                }
        }
        mapaCeldas[j.getFila()][j.getColumna()].tipo = Const.CELDA_CUERPO;
        mapaCeldas[fila][columna].tipo = Const.CELDA_CABEZA;
        j.setPosicion(fila, columna);
        return false;
    }

    /**
     * Retorna el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     * @return el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     */
    public String getArchivoImagen() {
        switch (tipo) {
            case Const.CELDA_PARED:
                return Const.ARCHIVO_PARED;
            case Const.CELDA_CABEZA:
                return Const.ARCHIVO_CABEZA;
            case Const.CELDA_CAJA:
                return Const.ARCHIVO_CAJA;
            case Const.CELDA_CUERPO:
                return Const.ARCHIVO_CUERPO;
            case Const.CELDA_MANZANA:
                return Const.ARCHIVO_MANZANA;
            default:
                return null;
        }
    }
    /**
     * Método que devuelve la fila de la celda
     *
     * @return fila en la que se encuentra la celda
     */
    int getFila() {
        return fila;
    }

    /**
     * Método que devuelve la columna de la celda
     *
     * @return columna en la que se encuentra la celda
     */
    int getColumna() {
        return columna;
    }

}
