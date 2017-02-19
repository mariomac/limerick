package moo.proyecto.entrega2;

/**
 * Created by mmacias on 23/7/16.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Clase abstracta que describe las funcionalidades comunes de cualquier item (llave, soplete, agua, pico...)
 */
public abstract class Item implements ObjetoEnEscena {

    /**
     * Conjunto de poderes que tiene un Item. A partir de esta entrega, cada item
     * podrá tener más de un poder.
     */
    protected Set<PoderItem> poderes = new HashSet<>();

    /**
     * Consulta si un Item posee un poder determinado.
     * @param poder el tipo de {@link PoderItem} a consultar
     * @return {@code true} si el item posee el poder pasado por parámetro. False en caso contrario
     */
    public boolean tienePoder(PoderItem poder) {
        return poderes.contains(poder);
    }

    /**
     * Consulta si un jugador puede pasar a través de la Celda que contenga este Item. Generalmente
     * retornará {@code true} cuando el Jugador no posea ningún otro Item.
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} cuando el Jugador pueda pasar (porque no posea ningún otro Item),
     *         {@code false} en caso contrario.
     */
    public boolean puedePasar(Jugador jugador) {
        return jugador.getItem() == null;
    }

    /**
     * Realiza las acciones pertinentes cuando la instancia del {@link Jugador} pasado por parámetro
     * pasa a través de la {@link Celda} que contiene este item. Generalmente, asignará el Item al jugador
     * y lo eliminará de la Celda.
     * @param j la instancia del jugador
     * @param c la celda donde el jugador acaba de pasar
     * @return siempre {@code false}
     */
    public boolean pasa(Jugador j, Celda c) {
        j.setItem(this);
        c.eliminaObjeto();
        return false;
    }
}
