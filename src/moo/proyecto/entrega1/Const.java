package moo.proyecto.entrega1;

/**
 *
 * Esta clase contiene algunas constantes que se utilizarán en el programa. En
 * concreto contiene constantes para:
 * <br>...Indicar el número de filas y de columnas del recinto.
 * <br>...Indicar los nombres relativos de los archivos en los que se encuentran
 * las imagenes de los obstáculos y los elementos que permiten vencerlos.
 * <br>...Caracteres que permiten crear, vía código, los recintos de los
 * diferentes niveles.
 *
 * Para construir el recinto, el diseñador de niveles genera un
 * mapa de caracteres. Se trata de un vector bidireccional (matriz) de idénticas
 * dimensiones a las del recinto del juego. El carácter presente en cada
 * posición de esta matriz indicará el obstáculo, item o asuencia de ambos que
 * el recinto del juego tendrá en la celda que ocupe la posición que tenga los
 * mismos números de fila y columna que la posición del mapa.
 * A partir de ese mapa de caracteres el código construye el recinto con celdas
 * que están libres o contienen obstáculos o items, según los caracteres contenidos
 * en las diferentes posiciones del mapa (ver {@link  Nivel#Nivel}).
 *
 *
 */
public class Const {

    /**
     * Número de columnas del recinto
     */
    public static final int NIVEL_COLUMNAS = 19;
    /**
     * Número de filas del recinto
     */
    public static final int NIVEL_FILAS = 15;

    /**
     * Nombre relativo del archivo en el que está la imagen de la cara de la serpiente
     */
    public static final String ARCHIVO_CABEZA = "/imagenes/cabeza.png";

    /**
     * Nombre relativo del archivo en el que está la imagen de la cara de la serpiente
     */
    public static final String ARCHIVO_CUERPO = "/imagenes/cuerpo.png";

    /**
     * Nombre relativo del archivo en el que está la imagen de la caja
     */
    public static final String ARCHIVO_CAJA = "/imagenes/caja.png";

    /**
     * Nombre relativo del archivo en el que está la imagen de la manzana
     */
    public static final String ARCHIVO_MANZANA = "/imagenes/manzana.png";

    /**
     * Nombre relativo del archivo en el que está la imagen de la pared
     */
    public static final String ARCHIVO_PARED = "/imagenes/pared.png";

    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición está la cabeza de la serpiente.
     */
    public static final char CELDA_CABEZA = 'O';

    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición está el cuerpo de la serpiente.
     */
    public static final char CELDA_CUERPO = 'X';

    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que la celda está vacía.
     */
    public static final char CELDA_VACIA = ' ';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay una
     * pared.
     */
    public static final char CELDA_PARED = '#';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición está la
     * puerta.
     */
    public static final char CELDA_MANZANA = 'M';

    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay una caja.
     */
    public static final char CELDA_CAJA = 'K';

}
