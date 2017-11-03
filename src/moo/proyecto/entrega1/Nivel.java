package moo.proyecto.entrega1;

/**
 * Cada objeto de la clase Nivel contiene toda la información correspondiente a
 * uno de los niveles del juego. Cada nivel está formado por el tablero y el
 * cabeza. En las celdas del tablero se distribuyen los objetos y los items,
 * así como el cabeza.
 *
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
    private char[][] mapaInicial;

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
     * @param filas número de filas del recinto
     * @param columnas numero de columnas del recinto
     * @param mapaInicial matriz de caracteres (con dimensiones iguales a las indicadas
     * por los dos anteriores parámentros) cuyos contenidos indican los objetos
     * (items/obstáculos) que deben aparecer en la posición correspondiente del
     * recinto.
     */
    public Nivel(int filas, int columnas, char[][] mapaInicial) {
        this.mapaInicial = mapaInicial;
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {

                if (mapaInicial[f][c] == Const.CELDA_CABEZA) {
                    cabeza = new Cabeza(f, c);
                }
                celdas[f][c] = new Celda(mapaInicial[f][c]);
            }
        }
    }

    /**
     * Devuelve una referencia al cabeza
     * @return referencia al cabeza
     */
    public Cabeza getCabeza() {
        return cabeza;
    }

    /**
     * Devuelve una referencia a la celda que ocupa una cierta posición en
     * el recinto de juego, indicada por el número de fila y el número de columna.
     * @param fila número de fila de la celda
     * @param col número de columna de la celda
     * @return referencia a celda que ouupa la posición indicada por los dos
     * argumentos anteriores.
     */
    public Celda getCelda(int fila, int col) {
        return celdas[fila][col];
    }

    public boolean intentaMoverCabeza(int fila, int col) {
        Celda izq = null, der = null;
        if (col > 1) {
            izq = celdas[fila][col - 1];
        }
        if (col < celdas[0].length - 1) {
            der = celdas[fila][col + 1];
        }
        if (celdas[fila][col].puedePasar(cabeza, der, izq)) {
            return pasa(fila, col);
        } else {
            return false;
        }
    }

    public boolean pasa(int fila, int columna) {
        switch (celdas[fila][columna].getTipo()) {
            case Const.CELDA_MANZANA:
                return true;
            case Const.CELDA_CAJA:
                if (cabeza.getColumna() < columna) {
                    celdas[fila][columna + 1].setTipo(Const.CELDA_CAJA);
                } else if (cabeza.getColumna() > columna) {
                    celdas[fila][columna - 1].setTipo(Const.CELDA_CAJA);
                }
        }
        return mueveCabeza(fila, columna);
    }

    public boolean mueveCabeza(int fila, int columna) {
        do {
            celdas[cabeza.getFila()][cabeza.getColumna()].setTipo(Const.CELDA_CUERPO);
            cabeza.setPosicion(fila, columna);
            celdas[fila][columna].setTipo(Const.CELDA_CABEZA);
            fila++;
        } while(celdas[fila][columna].getTipo() == Const.CELDA_VACIA);

        // Si cae encima de una manzana, se acaba la fase
        if (celdas[fila][columna].getTipo() == Const.CELDA_MANZANA) {
            return true;
        }
        return false;
    }

    /**
     * Crea un nuevo objeto Nivel con el mismo número de filas, el mismo número
     * de columnas y el mismo mapaInicial de caracteres.
     *
     * @return el nuevo nivel creado con los mismos contenidos que los de este
     * nivel.
     */
    public Nivel clonar() {
        return new Nivel(filas, columnas, mapaInicial);
    }

}
