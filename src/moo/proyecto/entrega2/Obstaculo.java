package moo.proyecto.entrega2;

/**
 * Superclase que representa cualquiera de los obstáculos que hay en el juego,
 * por ejemplo una pared, la puerta, fuego, hielo o roca...
 */
public abstract class Obstaculo implements ObjetoEnEscena {
    /**
     * Realiza las acciones pertinentes cuando la instancia del {@link Jugador} pasado por parámetro
     * pasa a través de la {@link Celda} que contiene este item. Generalmente, eliminará el Item del jugador
     * y eliminará este obstáculo de la Celda.
     * @param j el jugador
     * @param c la celda donde el jugador acaba de pasar
     * @return Siempre {@code false}, como comportamiento general de los obstáculos (ya se redefinirá
     *         este método en la clase {@link moo.proyecto.entrega2.obstaculos.Puerta} cuando toque
     *         retornar {@code true})
     */
    @Override
    public boolean pasa(Jugador j, Celda c) {
        j.setItem(null);
        c.eliminaObjeto();
        return false;
    }
}
