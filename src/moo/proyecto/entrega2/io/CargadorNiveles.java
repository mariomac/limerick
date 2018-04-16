package moo.proyecto.entrega2.io;

import moo.proyecto.entrega1.Nivel;

import java.util.List;

/**
 * Interfaz que implementarán las clases que construyan la lista de niveles
 * a partir de las fuentes de datos.
 *
 * @see CargadorMemoria
 * @see CargadorDisco
 */
public interface CargadorNiveles {
    /**
     * Retorna una lista de niveles a partir de la fuente de datos.
     *
     * @return una lista de niveles.
     * @throws ArchivoMalFormateadoException si la fuente de datos está mal
     *                                       formateada.
     */
    List<Nivel> carga() throws ArchivoMalFormateadoException;
}
