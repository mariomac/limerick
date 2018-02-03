package moo.proyecto.entrega1;

/**
 * Cada objeto de la clase Nivel contiene toda la información correspondiente a
 * uno de los niveles del juego. Cada nivel está formado por una matriz de {@link Celda} y el
 * {@link ControlCabeza}.
 */
public class Nivel {

    /**
     * ControlCabeza en el tablero
     */
    private ControlCabeza controlCabeza;

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
     * {@link Celda} y {@link ContenidoCelda} correspondientes, que serán los que implementen la
     * lógica del juego.
     */
    public void inicializar() {
        for (int f = 0; f < datosMapa.length; f++) {
            for (int c = 0; c < datosMapa[f].length; c++) {
                ContenidoCelda contenido = null;
                if (datosMapa[f][c] == Const.CELDA_CABEZA) {
                    controlCabeza = new ControlCabeza(this, f, c);
                    contenido = new ContenidoCelda(this, controlCabeza);
                } else if(datosMapa[f][c] != Const.CELDA_VACIA) {
                    contenido = new ContenidoCelda(datosMapa[f][c], this);
                }
                celdas[f][c] = new Celda(f, c, contenido);
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
     * <p>Intenta mover la cabeza por el Nivel, dado un vector de dirección <code>(df, dc)</code>
     * que indican la dirección en la que la cabeza de la serpiente
     * se está moviendo, en términos de "filas, columnas". Por ejemplo, si <code>df==1</code> y
     * <code>dc==0</code>, quiere decir que la serpiente se está moviendo una celda hacia la
     * derecha. Si <code>df==0</code> y <code>dc==-1</code>, quiere decir que la serpiente se
     * está moviendo una celda hacia arriba.</p>
     * <p>El sistema primero debe comprobar si se puede pasar a celda de la fila y la columna
     * contigua a la cabeza. Si se puede, entonces se hace efectivo el paso.</p>
     * <p>Si el movimiento es hacia arriba, también debe comprobar que no se haya superado el
     * límite de alturas de la cabeza.</p>
     * @param df Dirección de la serpiente, en columnas. Un -1 indica que se intenta mover una
     *           columna hacia arriba; un 0, indica que la cabeza no cambiará de fila.
     * @param dc Dirección de la serpiente, en columnas. Un -1 indica que la serpiente se quiere
     *           mover una columna hacia la izquierda; un +1, indica que quiere moverse hacia la
     *           derecha; un 0, que no cambiará de columna.
     * @return El resultado del movimiento, siendo {@link Const#PASO_FIN_NIVEL} el valor que indica
     *         que se ha llegado al final del nivel.
     * @see ControlCabeza#isLimiteAltura()
     * @see ControlCabeza#mueve(int, int)
     * @see Celda#intentaPasar(int, int)
     */
    public int mueveCabeza(int df, int dc) {
        if (df < 0 && controlCabeza.isLimiteAltura()) {
            return Const.PASO_IMPOSIBLE;
        }

        int fila = controlCabeza.getFila() + df;
        int columna = controlCabeza.getColumna() + dc;
        int paso = celdas[fila][columna].intentaPasar(df, dc);
        if (paso != Const.PASO_IMPOSIBLE) {
            controlCabeza.mueve(df, dc);
        }
        return paso;
    }

}
