package moo.proyecto.entrega1;

import moo.proyecto.entrega2.objetos.Cuerpo;

/**
 * Clase que gestiona los datos y el comportamiento relativo a la posición de la cabeza.
 */
public class Cabeza extends Celda {

    /**
     * Fila donde la cabeza está situada.
     */
    private int fila;

    /**
     * Columna donde la cabeza está situada.
     */
    private int columna;

    /**
     * Altura de la cabeza: número de casillas consecutivas en las que ha subido.
     */
    private int altura = 0;

    /**
     * Nivel en juego.
     */
    private Nivel nivel;

    /**
     * Constructor.
     * @param nivel Nivel en juego.
     * @param fila  Fila de la posición inicial de la cabeza.
     * @param columna Columna de la posición inicial de la cabeza.
     */
    public Cabeza(Nivel nivel, int fila, int columna) {
        this.nivel = nivel;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Fila donde la cabeza está situada.
     * @return Fila donde la cabeza está situada.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Columna donde está situada la cabeza.
     * @return Columna donde está situada la cabeza.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Retorna <code>true</code> si la cabeza ha subido consecutivamente el máximo permitido de
     * posiciones. <code>false</code> en caso contrario.
     * @return <code>true</code> si la cabeza ha subido consecutivamente el máximo permitido de
     * posiciones. <code>false</code> en caso contrario.
     */
    public boolean isLimiteAltura() {
        return altura >= Const.MAX_ALTURA;
    }

    @Override
    public String getImagen() {
        if(isLimiteAltura()) {
            return Const.ARCHIVO_CABEZA_ROJA;
        } else {
            return Const.ARCHIVO_CABEZA;
        }
    }

    /**
     * <p>Actualiza la posición de la cabeza, dado un vector de dirección <code>(df, dc)</code>
     * que indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia abajo.
     *  Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia la izquierda.</p>
     *
     * <p>El método debe encargarse de contabilizar la altura de la cabeza, así como de la física
     * (caerá hacia abajo si en la posición hacia la que se ha movido no hay suelo).</p>
     *
     * @param df Dirección de la serpiente, en filas. Un -1 indica que se intenta mover una
     *           fila hacia arriba; un 0, indica que la cabeza no cambiará de fila.
     * @param dc Dirección de la serpiente, en columnas. Un -1 indica que la serpiente se quiere
     *           mover una columna hacia la izquierda; un +1, indica que quiere moverse hacia la
     *           derecha; un 0, que no cambiará de columna.
     */
    public void actualizaPosicion(int df, int dc) {
        if (df < 0) {
            altura++;
        } else {
            altura = 0;
        }
        nivel.setCelda(fila, columna, new Cuerpo());
        fila += df;
        columna += dc;
        while (!nivel.hayCelda(fila + 1, columna)) {
            nivel.setCelda(fila, columna, new Cuerpo());
            fila++;
        }
        nivel.setCelda(fila, columna, this);
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
