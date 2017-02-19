package moo.proyecto.entrega2;

/**
 * Excepción que se lanza cuando el archivo que contiene los niveles está mal formateado
 */
public class ArchivoMalFormateadoException extends Exception {
    /**
     * Constructor de la clase
     * @param message Un mensaje asociado a la excepción, dando información extra
     */
    public ArchivoMalFormateadoException(String message) {
        super(message);
    }
}
