package moo.proyecto.entrega2;

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
     * Objeto en escena situado sobre esta celda
     */
    private ObjetoEnEscena objeto;

    /**
     * Elimina el objeto que hay en esa celda. Es decir, lo pone a {@code null}
     */
    public void eliminaObjeto() {
        this.objeto = null;
    }

    /**
     * Constructor: inicializa el objeto Celda creado de tal forma que sea
     * una celda vacía, esto es, nada que dibujar
     *
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     */
    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Constructor: inicializa el objeta Celda que contiene un {@link ObjetoEnEscena}
     * dado
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @param objeto  el {@link ObjetoEnEscena} que contiene la celda
     */
    public Celda(int fila, int columna, ObjetoEnEscena objeto) {
        this.fila = fila;
        this.columna = columna;
        this.objeto = objeto;
    }

    /**
     * Retorna si el jugador pasado por parámetro puede pasar por la celda en cuestión.
     * @param j la instancia del objeto {@link Jugador}
     * @return {@code true} si puede pasar. {@code false} en caso contrario
     */
    public boolean puedePasar(Jugador j) {
        return objeto == null || objeto.puedePasar(j);
    }

    /**
     * Realiza la acción correspondiente que tiene lugar cuando el jugador pasa por esa celda
     * @param j  la instancia del objeto {@link Jugador}
     * @return {@code true} si el jugador ha llegado al final de un nivel (es decir, ha entrado
     *         por la puerta. {@code false} en caso contrario
     */
    public boolean pasa(Jugador j) {
        if (objeto == null) {
            return false;
        } else {
            return objeto.pasa(j, this);
        }
    }

    /**
     * Retorna el archivo de imagen del objeto situado en la celda
     * @return el archivo de imagen del objeto situado en la celda
     */
    public String getArchivoImagen() {
        if(objeto == null) {
            return null;
        } else {
            return objeto.getArchivoImagen();
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
