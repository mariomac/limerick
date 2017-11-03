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

    public Celda(char tipo) {

        this.tipo = tipo;
    }

    public boolean puedePasar(Cabeza j, Celda celdaIzq, Celda celdaDer) {
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
                        && celdaDer != null && celdaDer.tipo == Const.CELDA_VACIA)
                        || (j.getColumna() > columna
                        && celdaIzq != null && celdaIzq.tipo == Const.CELDA_VACIA);
            default:
                return false;
        }
    }

    /**
     * Retorna el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     *
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
