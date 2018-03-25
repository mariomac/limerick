package moo.proyecto.entrega1;

/**
 * Clase para gestionar las operaciones relativas a las diferentes celdas que componen cada
 * {@link Nivel}
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
     * Tipo de celda, cuyos valores podrán ser {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA}, {@link Const#CELDA_PARED},
     * {@link Const#CELDA_CAJA} o {@link Const#CELDA_VACIA}
     */
    private char tipo;

    /**
     * Nivel en el que está contenida la celda. Será de utilidad cuando las cajas tengan que
     * indagar en las casillas contínuas para ver si se pueden desplazar hacia la izquierda,
     * o hasta dónde pueden caer.
     */
    private Nivel nivel;

    /**
     * Constructor de una celda posicionada en una fila y columna dadas,
     * con un contenido dado, que podrá ser del tipo {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA}, {@link Const#CELDA_PARED},
     * {@link Const#CELDA_CAJA} o {@link Const#CELDA_VACIA}.
     * @param nivel Nivel en el que está contenida la celda.
     * @param fila Fila en que la celda está situada.
     * @param columna Columna en que la celda está situada.
     * @param tipo Tipo de celda, que podrá ser {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA}, {@link Const#CELDA_PARED},
     * {@link Const#CELDA_CAJA} o {@link Const#CELDA_VACIA}
     */
    public Celda(Nivel nivel, int fila, int columna, char tipo) {
        this.nivel = nivel;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * <p>Realiza las acciones pertinentes a cuando la cabeza de la serpiente esté intentando pasar
     * hacia la celda.</p>
     * <p>Los parámetros <code>df, dc</code> indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia abajo.
     *  Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia la izquierda.</p>
     * <p>Por ejemplo:</p>
     * <ul>
     *     <li>Si la celda está vacía, no hará nada especial.</li>
     *     <li>Si en la celda hay una manzana, realizará la opción de cogerla.</li>
     *     <li>Si en la celda hay una caja, realizará la acción de empujarla
     *     hacia la posición dada por <code>(fila+df, columna+df)</code>.</li>
     * </ul>
     * <p>Retornará además el resultado del intento de paso:</p>
     * <ul>
     *     <li>{@link Const#PASO_OK}: si la cabeza ha podido pasar (porque la celda está vacía,
     *     o porque había una caja que se ha podido mover).</li>
     *     <li>{@link Const#PASO_FIN_NIVEL}: si la cabeza ha llegado al fin de nivel (es decir,
     *     ha cogido una manzana).</li>
     *     <li>{@link Const#PASO_IMPOSIBLE}: si la cabeza no ha podido pasar, ya sea porque había
     *     una pared o porque había un objeto movible (caja) que no se podía mover ya que había
     *     algún otro objeto en la celda donde se pretendía empujar.</li>
     * </ul>
     * @param df Dirección de la serpiente, en filas. Un -1 indica que se intenta mover una
     *           fila hacia arriba; un 0, indica que la cabeza no cambiará de fila.
     * @param dc Dirección de la serpiente, en columnas. Un -1 indica que la serpiente se quiere
     *           mover una columna hacia la izquierda; un +1, indica que quiere moverse hacia la
     *           derecha; un 0, que no cambiará de columna.
     * @return El resultado del intento de paso ({@link Const#PASO_OK}, {@link Const#PASO_FIN_NIVEL}
     *         o {@link Const#PASO_IMPOSIBLE})
     */
    public int intentaPasar(int df, int dc) {
        switch(tipo) {
            case Const.CELDA_VACIA:
                return Const.PASO_OK;
            case Const.CELDA_MANZANA:
                return Const.PASO_FIN_NIVEL;
            case Const.CELDA_CAJA:
                return empujar(fila + df, columna + dc);
            default:
                return Const.PASO_IMPOSIBLE;
        }
    }

    /**
     * Realiza la acción resultante de empujar el elemento contenido en la celda hacia las filas
     * y columnas dadas por los argumentos <code>haciaFila</code> y <code>haciaColumna</code>:
     * <ul>
     *     <li>Si en la celda hay algo que no sea una caja, retorna {@link Const#PASO_IMPOSIBLE}</li>
     *     <li>Si en la posición de destino del elemento empujado (haciaFila, haciaColumna) ya hay
     *     algún otro objeto, retornará {@link Const#PASO_IMPOSIBLE}, ya que la serpiente sólo tiene
     *     fuerza para arrastrar un solo objeto.</li>
     *     <li>Si no hay nada, empujará el objeto hacia la posición (haciaFila, haciaColumna).</li>
     *     <li>Si lo ha empujado y en esa posición no hay suelo, hará descender el objeto hasta que
     *     encuentre un suelo (simulando el efecto de que la caja cae).</li>
     * </ul>
     *
     * Si el objeto se ha podido empujar, retornará {@link Const#PASO_OK}.
     *
     * @param haciaFila fila hacia la que se empuja el elemento
     * @param haciaColumna columna hacia la que se empuja el elemento
     * @return {@link Const#PASO_OK} si se ha podido mover el objeto empujado, o
     * (@link Const#PASO_IMPOSIBLE) en caso contrario.
     */
    public int empujar(int haciaFila, int haciaColumna) {
        if (tipo != Const.CELDA_CAJA) {
            return Const.PASO_IMPOSIBLE;
        }
        if (!nivel.getCelda(haciaFila, haciaColumna).isVacia()) {
            return Const.PASO_IMPOSIBLE;
        }
        while(nivel.getCelda(haciaFila + 1, haciaColumna).isVacia()) {
            haciaFila++;
        }
        nivel.getCelda(haciaFila,haciaColumna).setTipo(tipo);
        return Const.PASO_OK;
    }

    /**
     * Retorna la imagen perteneciente al objeto contenido en la celda, o <code>null</code> si la
     * celda está vacía.
     * @return la imagen perteneciente al objeto contenido en la celda, o <code>null</code> si la
     * celda está vacía.
     */
    public String getImagen() {
        switch(tipo) {
            case Const.CELDA_PARED:
                return Const.ARCHIVO_PARED;
            case Const.CELDA_CABEZA:
                if(nivel.getCabeza().isLimiteAltura()) {
                    return Const.ARCHIVO_CABEZA_ROJA;
                } else {
                    return Const.ARCHIVO_CABEZA;
                }
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
     * Returna <code>true</code> si la celda está vacía. <code>false</code> en caso contrario.
     * @return <code>true</code> si la celda está vacía. <code>false</code> en caso contrario.
     */
    public boolean isVacia() {
        return tipo == Const.CELDA_VACIA;
    }

    /**
     * Modifica el tipo del contenido de la celda.
     * @param tipo Nuevo tipo de la celda
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
