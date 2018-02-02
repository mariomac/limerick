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

    private ContenidoCelda contenido; // si null, es que está vacía

    public Celda(int fila, int columna, ContenidoCelda contenido) {
        this.contenido = contenido;
        this.fila = fila;
        this.columna = columna;
    }

    public int pasa(int df, int dc) {
        if(contenido == null) {
            return Const.PASO_OK;
        }
        if(contenido.esCogible()) {
            return contenido.coger();
        }
        if(contenido.esEmpujable()) {
            return contenido.empujar(fila + df, columna + dc);
        }
        return Const.PASO_IMPOSIBLE;
    }

    public String getImagen() {
        if(contenido == null) {
            return null;
        }
        return contenido.getImagen();
    }

    public ContenidoCelda getContenido() {
        return contenido;
    }

    public void setContenido(ContenidoCelda contenido) {
        this.contenido = contenido;
    }
}
