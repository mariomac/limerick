package moo.proyecto.entrega2.items;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Item;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;

/**
 * Clase que representa un Item del tipo "Agua"
 */
public class Agua extends Item {

    /**
     * Constructor de la clase. Debe añadir el poder de {@link ApagaFuegos} a la lista de poderes
     * ({@link Item#poderes})
     */
    public Agua() {
        poderes.add(ApagaFuegos.INSTANCIA);
    }

    /**
     * Returna el nombre del archivo de la imagen del agua
     *
     * @return {@link Const#ARCHIVO_AGUA}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_AGUA;
    }


}
