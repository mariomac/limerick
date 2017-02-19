package moo.proyecto.entrega2.items.poderes;

import moo.proyecto.entrega2.PoderItem;

/**
 * <p>Clase que "enumera" el poder para romper rocas.</p>
 *
 * <p>Es una clase sin método, ya que la mostramos como elemento de enumeración (p.ej. si el conjunto
 * de poderes de un {@link moo.proyecto.entrega2.Item} contiene una instancia de esta clase, es que
 * tiene dicho poder)</p>
 *
 * <p>Esta clase sigue un patrón llamado "singleton", que asegura que en todo el programa existe una y solo
 * una instancia de este objeto. Esto se consigue haciendo que el constructor de la clase sea privado, así
 * como definiendo una instancia {@code public static final} que será accesible desde cualquier punto del
 * programa a través de la variable de clase {@link Rompedor#INSTANCIA}</p>
 */
public class Rompedor implements PoderItem {
    /**
     * Única instancia de la clase
     */
    public static final Rompedor INSTANCIA = new Rompedor();
    private Rompedor() {
    }
}