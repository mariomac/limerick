package moo.proyecto.entrega2.items;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Item;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;
import moo.proyecto.entrega2.items.poderes.Fundidor;

/**
 * Clase que representa un Item del tipo "Soplete"
 */
public class Soplete extends Item {

    /**
     * Constructor de la clase. Debe añadir el poder de {@link Fundidor} a la lista de poderes
     * ({@link Item#poderes})
     */
    public Soplete() {
        poderes.add(Fundidor.INSTANCIA);
    }

    /**
     * Retorna el nombre del archivo de la imagen del soplete
     * @return {@link Const#ARCHIVO_SOPLETE}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_SOPLETE;
    }

}
