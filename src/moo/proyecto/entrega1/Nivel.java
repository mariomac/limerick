package moo.proyecto.entrega1;

import moo.proyecto.entrega2.objetos.*;

/**
 * Cada objeto de la clase Nivel contiene toda la información correspondiente a
 * uno de los niveles del juego. Cada nivel está formado por una matriz de {@link Celda} y el
 * {@link Cabeza}.
 */
public class Nivel {

    /**
     * Cabeza en el tablero
     */
    private Cabeza cabeza;

    /**
     * Vector bidimensional de celdas. Cada posición se corresponde con una
     * celda del tablero de juego.
     */
    private Celda[][] celdas;

    /**
     * Vector bidimensional de caracteres. Cada posición contiene un caracter.
     * Cada posición del vector indica el contenido de la celda del tablero
     * cuyas fila y columna coínciden con la de la posición en cuestión. El
     * valor de cada carácter del vector bidimensional indica el contenido de la
     * celda correspondiente del tablero.
     */
    private char[][] datosMapa;

    /**
     * Constructor.
     *
     * @param filas       número de filas del recinto
     * @param columnas    numero de columnas del recinto
     * @param datosMapa   matriz de caracteres (con dimensiones iguales a las indicadas
     *                    por los dos anteriores parámentros) cuyos contenidos indican los objetos
     *                    que deben aparecer en la posición correspondiente del recinto, cuyos valores
     *                    serán ({@link Const#CELDA_VACIA}, {@link Const#CELDA_PARED},
     *                    {@link Const#CELDA_CAJA}, etc...
     */
    public Nivel(int filas, int columnas, char[][] datosMapa) {
        this.datosMapa = datosMapa;
        celdas = new Celda[filas][columnas];
    }

    /**
     * Dados los datos del mapa que se pasaron en el constructor (chars), inicializa los objetos
     * {@link Celda} correspondientes, que serán los que implementen la
     * lógica del juego.
     */
    public void inicializar() {
        for (int f = 0; f < datosMapa.length; f++) {
            for (int c = 0; c < datosMapa[f].length; c++) {
                Celda contenido;
                switch (datosMapa[f][c]) {
                    case Const.CELDA_CABEZA:
                        cabeza = new Cabeza(this, f, c);
                        contenido = cabeza;
                        break;
                    case Const.CELDA_CAJA:
                        contenido = new Caja(this, f, c);
                        break;
                    case Const.CELDA_PARED:
                        contenido = new Pared();
                        break;
                    case Const.CELDA_MANZANA:
                        contenido = new Manzana();
                        break;
                    case Const.CELDA_ARENA:
                        contenido = new Arena(this, f, c);
                        break;
                    case Const.CELDA_AGUA:
                        contenido = new Agua(this, f, c);
                        break;
                    case Const.CELDA_SEMILLA:
                        contenido = new Semilla(this, f, c);
                        break;
                    default:
                        // no añadir nada
                        contenido = null;
                }
                celdas[f][c] = contenido;
            }
        }
    }


    /**
     * Devuelve una referencia a la celda que ocupa una cierta posición en
     * el recinto de juego, indicada por el número de fila y el número de columna.
     *
     * @param fila número de fila de la celda
     * @param col  número de columna de la celda
     * @return referencia a celda que ouupa la posición indicada por los dos
     * argumentos anteriores.
     */
    public Celda getCelda(int fila, int col) {
        return celdas[fila][col];
    }

    /**
     * Retorna {@code true} si hay una celda en la fila y columna dadas,
     * {@code false} en caso contrario.
     * @param fila Fila donde se pretende verificar si hay una celda.
     * @param col Columna donde se pretende mirar si hay una celda.
     * @return {@code true} si hay una celda en la fila y columna dadas,
     *         {@code false} en caso contrario.
     */
    public boolean hayCelda(int fila, int col) {
        return getCelda(fila, col) != null;
    }

    /**
     * Pone una {@link Celda} en la determinada fila y columna.
     * @param fila Fila donde se pondrá la celda.
     * @param col Columna donde se pondrá la celda.
     * @param contenido Implementación de la {@link Celda} a situar.
     */
    public void setCelda(int fila, int col, Celda contenido) {
        celdas[fila][col] = contenido;
    }

    /**
     * <p>Intenta mover la cabeza por el Nivel, dado un vector de dirección <code>(df, dc)</code>
     * que indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia abajo.
     *  Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia la izquierda.</p>
     * <p>El sistema primero debe comprobar si hay una celda en la posición de la fila yla columna
     * contigua a la cabeza. Si no hay ninguna celda, o se puede pasar a esa celda
     * (según el resultado de la llamada al método {@link Celda#intentaPasar(int, int)}),
     * entonces se hace efectivo el paso.</p>
     * <p>Si el movimiento es hacia arriba, también debe comprobar que no se haya superado el
     * límite de alturas de la cabeza.</p>
     * @param df Dirección de la serpiente, en filas. Un -1 indica que se intenta mover una
     *           fila hacia arriba; un 0, indica que la cabeza no cambiará de fila.
     * @param dc Dirección de la serpiente, en columnas. Un -1 indica que la serpiente se quiere
     *           mover una columna hacia la izquierda; un +1, indica que quiere moverse hacia la
     *           derecha; un 0, que no cambiará de columna.
     * @return El resultado del movimiento, siendo {@link Const#PASO_FIN_NIVEL} el valor que indica
     *         que se ha llegado al final del nivel.
     * @see Cabeza#isLimiteAltura()
     * @see Cabeza#actualizaPosicion(int, int)
     * @see Celda#intentaPasar(int, int)
     */
    public int mueveCabeza(int df, int dc) {
        if (df < 0 && cabeza.isLimiteAltura()) {
            return Const.PASO_IMPOSIBLE;
        }

        int fila = cabeza.getFila() + df;
        int columna = cabeza.getColumna() + dc;
        int paso;
        if (!hayCelda(fila, columna)) {
            paso = Const.PASO_OK;
        } else {
            paso = celdas[fila][columna].intentaPasar(df, dc);
        }
        if (paso != Const.PASO_IMPOSIBLE) {
            cabeza.actualizaPosicion(df, dc);
        }
        return paso;
    }

}
