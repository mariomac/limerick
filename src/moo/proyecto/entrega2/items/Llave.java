package moo.proyecto.entrega2.items;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Item;
import moo.proyecto.entrega2.items.poderes.AbrePuertas;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;

/**
 * Clase que representa un Item del tipo "Llave"
 */
public class Llave extends Item {
    /**
     * Constructor de la clase. Debe añadir el poder de {@link AbrePuertas} a la lista de poderes
     * ({@link Item#poderes})
     */

    public Llave() {
        poderes.add(AbrePuertas.INSTANCIA);
    }

    /**
     * Retorna el nombre del archivo de la imagen de la llave
     * @return {@link Const#ARCHIVO_LLAVE}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_LLAVE;
    }
}
