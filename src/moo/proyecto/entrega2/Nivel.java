package moo.proyecto.entrega2;

import moo.proyecto.entrega2.items.Agua;
import moo.proyecto.entrega2.items.Llave;
import moo.proyecto.entrega2.items.Pico;
import moo.proyecto.entrega2.items.Soplete;
import moo.proyecto.entrega2.obstaculos.*;

/**
 * Cada objeto de la clase Nivel contiene toda la información correspondiente a
 * uno de los niveles del juego. Cada nivel está formado por el tablero y el
 * jugador. En las celdas del tablero se distribuyen los objetos y los items,
 * así como el jugador.
 *
 */
public class Nivel {

    /**
     * Jugador en el tablero
     */
    private Jugador jugador;
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
    private char[][] mapa;

    /**
     * Construye el recinto correspondiente a un nivel. Este nivel tendrá un recinto 
     * con el número de filas y columnas indicadas en los correspondientes argumentos.
     * En el interior del recinto se distribuirán los obstáculos y los items 
     * según los contenidos de la matriz de caracteres pasada como tercer argumento.
     * Para una relación de la correspondencia entre los valores de caractéres y los 
     * obstáculos/items a los que darán lugar, ver {@link moo.proyecto.entrega2}
     * Este método debe crear el nuevo recinto como una matriz de celdas. Posteriormente 
     * debe añadir en las celdas los obstáculos/items que corresponda. Para ello recorrerá 
     * la matriz de caracteres pasada como tercer argumento. El método crea
     * una nueva celda por cada posición de la matriz del recinto del juego. En cada 
     * celda hay que depositar el item/obstáculo indicado por el carácter presente en la 
     * misma posición del mapa de caracteres (si hay un carácter distinto del espacio 
     * en blanco, que indica ausencia de obstáculos/items).
     * @param filas número de filas del recinto
     * @param columnas numero de columnas del recinto
     * @param mapa matriz de caracteres (con dimensiones iguales a las indicadas 
     * por los dos anteriores parámentros) cuyos contenidos indican los objetos 
     * (items/obstáculos) que deben aparecer en la posición correspondiente del 
     * recinto.
     */
    public Nivel(int filas, int columnas, char[][] mapa) {
        this.mapa = mapa;
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                switch (mapa[f][c]) {
                    case Const.CELDA_POSICION_INICIAL_JUGADOR:
                        celdas[f][c] = new Celda(f, c);
                        jugador = new Jugador(celdas[f][c]);
                        break;
                    case Const.CELDA_AGUA:
                        celdas[f][c] = new Celda(f, c, new Agua());
                        break;
                    case Const.CELDA_LLAVE:
                        celdas[f][c] = new Celda(f, c, new Llave());
                        break;
                    case Const.CELDA_PICO:
                        celdas[f][c] = new Celda(f, c, new Pico());
                        break;
                    case Const.CELDA_SOPLETE:
                        celdas[f][c] = new Celda(f, c, new Soplete());
                        break;
                    case Const.CELDA_PARED:
                        celdas[f][c] = new Celda(f, c, new Pared());
                        break;
                    case Const.CELDA_FUEGO:
                        celdas[f][c] = new Celda(f, c, new Fuego());
                        break;
                    case Const.CELDA_HIELO:
                        celdas[f][c] = new Celda(f, c, new Hielo());
                        break;
                    case Const.CELDA_ROCA:
                        celdas[f][c] = new Celda(f, c, new Roca());
                        break;
                    case Const.CELDA_PUERTA:
                        celdas[f][c] = new Celda(f, c, new Puerta());
                        break;
                    case Const.CELDA_NORMAL:
                    default:
                        celdas[f][c] = new Celda(f, c);
                        break;
                }
            }
        }
    }

    /**
     * Devuelve una referencia al jugador
     * @return referencia al jugador
     */
    public Jugador getJugador() {
        return jugador;
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

    /**
     * Intenta mover al jugador a la celda cuya posición se indica en sus argumentos.
     * @param fila número de fila de la celda a la que desea moverse el jugador.
     * @param col número de columna de la celda a la que desea moverse el jugador.
     * @return true si el jugador ha llegado a la puerta de fin de nivel. False en caso contrario
     */
    public boolean intentaMoverJugador(int fila, int col) {
        if (celdas[fila][col].puedePasar(jugador)) {
            jugador.setCelda(celdas[fila][col]);
            return celdas[fila][col].pasa(jugador);
        } else {
            return false;
        }
    }

    /**
     * Crea un nuevo objeto Nivel con el mismo número de filas, el mismo número 
     * de columnas y el mismo mapa de caracteres.
     * 
     * @return el nuevo nivel creado con los mismos contenidos que los de este 
     * nivel.
     */
    public Nivel clonar() {
        return new Nivel(filas, columnas, mapa);

    }

}
