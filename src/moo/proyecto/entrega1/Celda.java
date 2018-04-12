package moo.proyecto.entrega1;

/**
 * Clase para gestionar las operaciones relativas a las diferentes celdas que componen cada
 * {@link Nivel}
 */
public abstract class Celda {

    /**
     * Fila en la que se encuentra la celda
     */
    protected int fila;
    /**
     * Columna en la que se encuentra la celda
     */
    protected int columna;

    /**
     * Nivel en el que está contenida la celda. Será de utilidad cuando las cajas tengan que
     * indagar en las casillas contínuas para ver si se pueden desplazar hacia la izquierda,
     * o hasta dónde pueden caer.
     */
    protected Nivel nivel;

    public Celda(Nivel nivel, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.nivel = nivel;
    }

    /**
     * Fila donde está situada la Celda.
     * @return Fila donde está situada la Celda.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Columna donde está situada la Celda.
     * @return Columna donde está situada la Celda.
     */
    public int getColumna() {
        return columna;
    }


    /**
     * Las subclases de {@link Celda} deben implementar este método abstracto para retornar
     * el nombre del archivo que contiene la imagen perteneciente al objeto contenido en la celda.
     * @return el nombre del archivo que contiene la imagen perteneciente al objeto contenido en la celda.
     */
    public abstract String getImagen();

    /**
     * <p>Las subclases de {@link Celda} deberán implementar este método abstracto para
     * realizar las acciones pertinentes a cuando la cabeza de la serpiente esté intentando pasar
     * hacia la celda.</p>
     * <p>Los parámetros <code>df, dc</code> indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia abajo.
     *  Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia la izquierda.</p>
     * <p>Por ejemplo:</p>
     * <ul>
     *     <li>Si la celda está vacía, no hará nada especial.</li>
     *     <li>Si en la celda hay una {@link moo.proyecto.entrega2.objetos.Manzana}, realizará
     *     la acción de cogerla.</li>
     *     <li>Si en la celda hay una {@link moo.proyecto.entrega2.objetos.Caja}, realizará la
     *     acción de empujarla hacia la posición dada por <code>(fila+df, columna+dc)</code>.</li>
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
    public abstract int intentaPasar(int df, int dc);
}
