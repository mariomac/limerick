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
     * Contenido de la celda. Si es <code>null</code>, representa que la celda está vacía.
     */
    private ContenidoCelda contenido; // si null, es que está vacía

    /**
     * Constructor de una celda posicionada en una fila y columna dadas, con un contenido dado
     * (puede ser <code>null</code> si la celda está vacía)
     * @param fila Fila en que la celda está situada.
     * @param columna Columna en que la celda está situada.
     * @param contenido {@link ContenidoCelda} para dicha celda.
     */
    public Celda(int fila, int columna, ContenidoCelda contenido) {
        this.contenido = contenido;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * <p>Realiza las acciones pertinentes a cuando la cabeza de la serpiente esté intentando pasar
     * hacia la celda.</p>
     * <p>Los parámetros <code>df, dc</code> indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia la
     * derecha. Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia arriba.</p>
     * <p>Por ejemplo:</p>
     * <ul>
     *     <li>Si la celda está vacía, no hará nada especial.</li>
     *     <li>Si en la celda hay un contenido <i>cogible</i>, realizará la acción de cogerlo
     *     (método {@link ContenidoCelda#coger()}.</li>
     *     <li>Si en la celda hay un contenido <i>empujable</i>, realizará la acción de empujarlo
     *     hacia la posición dada por <code>(fila+df, columna+df)</code> (método
     *     {@link ContenidoCelda#empujar(int, int)}</li>
     * </ul>
     * <p>Retornará además el resultado del intento de paso:</p>
     * <ul>
     *     <li>{@link Const#PASO_OK}: si la cabeza ha podido pasar (porque la celda está vacía,
     *     o porque había una caja que se ha podido mover).</li>
     *     <li>{@link Const#PASO_FIN_NIVEL}: si la cabeza ha llegado al fin de nivel (es decir,
     *     ha cogido una manzana).</li>
     *     <li>{@link Const#PASO_IMPOSIBLE}: si la cabeza no ha podido pasar, ya sea porque había
     *     una pared o porque había un objeto movible (caja) que no se podía mover ya que había
     *     algún otro objeto en la celda donde se empujó.</li>
     * </ul>
     * @param df Dirección de la serpiente, en columnas. Un -1 indica que se intenta mover una
     *           columna hacia arriba; un 0, indica que la cabeza no cambiará de fila.
     * @param dc Dirección de la serpiente, en columnas. Un -1 indica que la serpiente se quiere
     *           mover una columna hacia la izquierda; un +1, indica que quiere moverse hacia la
     *           derecha; un 0, que no cambiará de columna.
     * @return El resultado del intento de paso ({@link Const#PASO_OK}, {@link Const#PASO_FIN_NIVEL}
     *         o {@link Const#PASO_IMPOSIBLE})
     */
    public int intentaPasar(int df, int dc) {
        if(contenido == null) {
            return Const.PASO_OK;
        }
        if(contenido.isCogible()) {
            return contenido.coger();
        }
        if(contenido.isEmpujable()) {
            return contenido.empujar(fila + df, columna + dc);
        }
        return Const.PASO_IMPOSIBLE;
    }

    /**
     * Retorna la imagen perteneciente al objeto contenido en la celda, o <code>null</code> si la
     * celda está vacía.
     * @return la imagen perteneciente al objeto contenido en la celda, o <code>null</code> si la
     * celda está vacía.
     */
    public String getImagen() {
        if(contenido == null) {
            return null;
        }
        return contenido.getImagen();
    }

    /**
     * Returna <code>true</code> si la celda está vacía. <code>false</code> en caso contrario.
     * @return <code>true</code> si la celda está vacía. <code>false</code> en caso contrario.
     */
    public boolean isVacia() {
        return contenido == null;
    }

    /**
     * Modifica el contenido de la celda.
     * @param contenido Nuevo contenido para la celda.
     */
    public void setContenido(ContenidoCelda contenido) {
        this.contenido = contenido;
    }
}
