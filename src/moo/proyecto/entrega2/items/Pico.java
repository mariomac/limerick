package moo.proyecto.entrega2.items;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Item;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;
import moo.proyecto.entrega2.items.poderes.Rompedor;

/**
 * Clase que representa un Item del tipo "Pico"
 */
public class Pico extends Item {
    /**
     * Constructor de la clase. Debe añadir el poder de {@link Rompedor} a la lista de poderes
     * ({@link Item#poderes})
     */
    public Pico() {
        poderes.add(Rompedor.INSTANCIA);
    }

    /**
     * Retorna el nombre del archivo de la imagen del pico
     * @return {@link Const#ARCHIVO_PICO}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_PICO;
    }
}
