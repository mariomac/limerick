package moo.proyecto.entrega2;

/**
 *
 * Esta clase contiene algunas constantes que se utilizarán en el programa. En
 * concreto contiene constantes para:
 * <br>...Indicar el número de filas y de columnas del recinto.
 * <br>...Indicar los nombres relativos de los archivos en los que se encuentran
 * las imágenes de los obstáculos y los elementos que permiten vencerlos.
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
    public static final int NIVEL_COLUMNAS = 16;
    /**
     * Número de filas del recinto
     */
    public static final int NIVEL_FILAS = 14;

    /**
     * Nombre relativo del archivo en el que está la imágen del
     * jugador-personaje
     */
    public static final String ARCHIVO_JUGADOR = "/imagenes/jugador.png";
    /**
     * Nombre relativo del archivo en el que está la imágen de la pared
     */
    public static final String ARCHIVO_PARED = "/imagenes/pared.png";
    /**
     * Nombre relativo del archivo en el que está la imágen de la puerta
     */
    public static final String ARCHIVO_PUERTA = "/imagenes/puerta.png";
    /**
     * Nombre relativo del archivo en el que está la imágen de la llave
     */
    public static final String ARCHIVO_LLAVE = "/imagenes/llave.png";
    /**
     * Nombre relativo del archivo en el que está la imágen del soplete
     */
    public static final String ARCHIVO_SOPLETE = "/imagenes/soplete.png";
    /**
     * Nombre relativo del archivo en el que está la imágen del hielo
     */
    public static final String ARCHIVO_HIELO = "/imagenes/hielo.png";
    /**
     * Nombre relativo del archivo en el que está la imágen del agua
     */
    public static final String ARCHIVO_AGUA = "/imagenes/agua.png";
    /**
     * Nombre relativo del archivo en el que está la imágen del fuego
     */
    public static final String ARCHIVO_FUEGO = "/imagenes/fuego.png";
    /**
     * Nombre relativo del archivo en el que está la imágen de un pico
     */
    public static final String ARCHIVO_PICO = "/imagenes/pico.png";
    /**
     * Nombre relativo del archivo en el que está la imágen de una roca
     */
    public static final String ARCHIVO_ROCA = "/imagenes/roca.png";

    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición está el
     * jugador.
     */
    public static final char CELDA_POSICION_INICIAL_JUGADOR = 'J';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que la celda está vacía.
     */
    public static final char CELDA_NORMAL = ' ';
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
    public static final char CELDA_PUERTA = 'P';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay una
     * llave.
     */
    public static final char CELDA_LLAVE = 'L';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay un
     * soplete.
     */
    public static final char CELDA_SOPLETE = 'S';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay un
     * bloque de hielo.
     */
    public static final char CELDA_HIELO = 'H';
    /**
     * Caracter que en el mapa de caracteres de Nivel (ver
     * {@link  Nivel}) indica que en esa posición hay una
     * llama.
     */
    public static final char CELDA_FUEGO = 'F';
    /**
     * Caracter que indica presencia de pared.
     */
    public static final char CELDA_AGUA = 'A';
    /**
     * Caracter que indica presencia de pico.
     */
    public static final char CELDA_PICO = 'T';
    /**
     * Caracter que indica presencia de roca.
     */
    public static final char CELDA_ROCA = 'R';
}
