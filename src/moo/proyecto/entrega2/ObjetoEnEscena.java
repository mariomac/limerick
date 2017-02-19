package moo.proyecto.entrega2;

/**
 * Interfaz que representa cualquier elemento dibujable (una pared, un Item, un Obstáculo, el Jugador...)
 */
public interface ObjetoEnEscena {
    /**
     * Retorna {@code true} si el jugador (pasado mediante el parámetro del mismo nombre) puede pasar a través de este
     * objeto en un momento dado, {@code false} en caso contrario.
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} si el jugador puede pasar a través de este
     * objeto en un momento dado, {@code false} en caso contrario.
     */
    public abstract boolean puedePasar(Jugador jugador);

    /**
     * Devuelve un String correspondiente al archivo que contiene la imagen del Item en cuestión, tal y como
     * están definidas en la clase {@link Const}
     * @return un String correspondiente al archivo que contiene la imagen del Item en cuestión
     */
    public abstract String getArchivoImagen();


    /**
     * Método que implementa las acciones a realizar cuando el jugador realmente
     * llega a una celda; este método devuelve true si esta celda tiene el obstáculo puerta
     * y false en caso contrario.
     * <br>Si en la celda hay un item, hacer que el jugador tome ese item. El método
     * puedePasar() solo permite pasar al jugador a una celda con un item si el jugador
     * no tiene ninguno.
     * <br>Si en la celda hay un obstáculo
     * <br>...a. Eliminar el item del jugador. El método puedePasar() solo permite
     * pasar al jugador a una celda con un obstáculo si el jugador tiene el item
     * que permite vencer dicho obstáculo. Al vencer el obstáculo, el item se consume.
     * <br>...b. Si el obstáculo que hay en la celda es la puerta, devolver true. Eso indicará
     * que el juego puede cambiar a un nivel superior.
     * <br>Dejar la celda vacía y devolver false
     * @param j el jugador
     * @param c la celda donde el jugador acaba de pasar
     * @return true si esta celda tiene el obstáculo puerta, false en caso contrario.
     */
    public boolean pasa(Jugador j, Celda c);
}
