package moo.proyecto.entrega1;

/**
 * Define constantes que se usarán en el programa.
 */
public class Const {

    /**
     * Número de cabezas de altura que la serpiente puede subir sobre sí misma.
     */
    public static final int MAX_ALTURA = 3;

    /**
     * Número de columnas del recinto de Celdas contenidas en un {@link Nivel}.
     */
    public static final int NIVEL_COLUMNAS = 19;

    /**
     * Número de filas del recinto de Celdas contenidas en un {@link Nivel}.
     */
    public static final int NIVEL_FILAS = 15;

    /**
     * Nombre del archivo en el que está la imagen de la cabeza de la serpiente
     */
    public static final String ARCHIVO_CABEZA = "/imagenes/cabeza.png";

    /**
     * Nombre del archivo en el que está la imagen de la cabeza roja de la serpiente
     */
    public static final String ARCHIVO_CABEZA_ROJA = "/imagenes/cabezaroja.png";

    /**
     * Nombre del archivo en el que está la imagen del cuerpo de la serpiente
     */
    public static final String ARCHIVO_CUERPO = "/imagenes/cuerpo.png";

    /**
     * Nombre del archivo en el que está la imagen de la caja
     */
    public static final String ARCHIVO_CAJA = "/imagenes/caja.png";

    /**
     * Nombre del archivo en el que está la imagen de la manzana
     */
    public static final String ARCHIVO_MANZANA = "/imagenes/manzana.png";

    /**
     * Nombre del archivo en el que está la imagen de la pared
     */
    public static final String ARCHIVO_PARED = "/imagenes/pared.png";

    /**
     * Caracter que en el mapa de caracteres de {@link Nivel} (ver {@link DatosNiveles}) indica
     * la situación de la cabeza de la serpiente.
     * Este valor también se usa para definir el tipo de celda para un {@link ContenidoCelda} dado.
     */
    public static final char CELDA_CABEZA = 'O';

    /**
     * Caracter que define un {@link ContenidoCelda} como parte del cuerpo de la serpiente.
     */
    public static final char CELDA_CUERPO = 'X';

    /**
     * Caracter que en el mapa de caracteres de {@link Nivel} (ver {@link DatosNiveles}) indica
     * la situación de una celda vacía.
     */
    public static final char CELDA_VACIA = ' ';

    /**
     * Caracter que en el mapa de caracteres de {@link Nivel} (ver {@link DatosNiveles}) indica
     * la situación de una pared.
     * Este valor también se usa para definir el tipo de celda para un {@link ContenidoCelda} dado.
     */
    public static final char CELDA_PARED = '#';

    /**
     * Caracter que en el mapa de caracteres de {@link Nivel} (ver {@link DatosNiveles}) indica
     * la situación de la manzana.
     * Este valor también se usa para definir el tipo de celda para un {@link ContenidoCelda} dado.
     */
    public static final char CELDA_MANZANA = 'M';

    /**
     * Caracter que en el mapa de caracteres de {@link Nivel} (ver {@link DatosNiveles}) indica
     * la situación de una caja.
     * Este valor también se usa para definir el tipo de celda para un {@link ContenidoCelda} dado.
     */
    public static final char CELDA_CAJA = 'K';

    /**
     * Valor que define como exitoso el intento de paso hacia una casilla (porque no habían
     * obstáculos, o éstos se han podido empujar hacia la casilla contigua).
     */
    public static final int PASO_OK = 0;

    /**
     * Valor que define como fallido el intento de paso hacia una casilla (porque habían obstáculos
     * que no han podido moverse)
     */
    public static final int PASO_IMPOSIBLE = 1;

    /**
     * Valor que informa de que el intento de paso hacia una casilla ha resultado ser el fin del
     * nivel (la cabeza ha cogido la manzana).
     */
    public static final int PASO_FIN_NIVEL = 2;
}
