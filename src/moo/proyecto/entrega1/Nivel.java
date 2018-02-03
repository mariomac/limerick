package moo.proyecto.entrega1;

/**
 * Cada objeto de la clase Nivel contiene toda la información correspondiente a
 * uno de los niveles del juego. Cada nivel está formado por el tablero y el
 * controlCabeza. En las celdas del tablero se distribuyen los objetos y los items,
 * así como el controlCabeza.
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
     * Número de filas del tablero
     */
    private int filas;
    /**
     * Número de columnas del tablero
     */
    private int columnas;
    /**
     * Vector bidimensional de caracteres. Cada posición contiene un caracter.
     * Cada posición del vector indica el contenido de la celda del tablero
     * cuyas fila y columna coínciden con la de la posición en cuestión. El
     * valor de cada carácter del vector bidimensional indica el contenido de la
     * celda correspondiente del tablero.
     */
    private char[][] datosMapa;

    /**
     * Construye el recinto correspondiente a un nivel. Este nivel tendrá un recinto
     * con el número de filas y columnas indicadas en los correspondientes argumentos.
     * En el interior del recinto se distribuirán los obstáculos y los items
     * según los contenidos de la matriz de caracteres pasada como tercer argumento.
     * Para una relación de la correspondencia entre los valores de caractéres y los
     * obstáculos/items a los que darán lugar, ver {@link moo.proyecto.entrega1}
     * Este método debe crear el nuevo recinto como una matriz de celdas. Posteriormente
     * debe añadir en las celdas los obstáculos/items que corresponda. Para ello recorrerá
     * la matriz de caracteres pasada como tercer argumento. El método crea
     * una nueva celda por cada posición de la matriz del recinto del juego. En cada
     * celda hay que depositar el item/obstáculo indicado por el carácter presente en la
     * misma posición del mapaInicial de caracteres (si hay un carácter distinto del espacio
     * en blanco, que indica ausencia de obstáculos/items).
     *
     * @param filas       número de filas del recinto
     * @param columnas    numero de columnas del recinto
     * @param datosMapa matriz de caracteres (con dimensiones iguales a las indicadas
     *                    por los dos anteriores parámentros) cuyos contenidos indican los objetos
     *                    (items/obstáculos) que deben aparecer en la posición correspondiente del
     *                    recinto.
     */
    public Nivel(int filas, int columnas, char[][] datosMapa) {
        this.datosMapa = datosMapa;
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
    }

    public void inicializar() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                ContenidoCelda contenido = null;
                if (datosMapa[f][c] == Const.CELDA_CABEZA) {
                    controlCabeza = new ControlCabeza(this, f, c);
                    contenido = new ContenidoCelda(datosMapa[f][c], this, controlCabeza);
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

    public int mueveCabeza(int df, int dc) {
        if (df < 0 && controlCabeza.isLimiteAltura()) {
            return Const.PASO_IMPOSIBLE;
        }

        int fila = controlCabeza.getFila() + df;
        int columna = controlCabeza.getColumna() + dc;
        int paso = celdas[fila][columna].intentaPasar(df, dc);
        if (paso != Const.PASO_IMPOSIBLE) {
            controlCabeza.actualizaPosicion(df, dc);
        }
        return paso;
    }

}
