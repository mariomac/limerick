package moo.proyecto.entrega2.obstaculos;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Jugador;
import moo.proyecto.entrega2.Obstaculo;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;

/**
 * Clase que representa un obstáculo del tipo "Pared"
 */
public class Pared extends Obstaculo {
    /**
     * Retorna el nombre del archivo de la imagen de la Pared
     * @return {@link Const#ARCHIVO_PARED}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_PARED;
    }

    /**
     * Indica si el jugador puede pasar por este obstáculo. En concreto, el jugador
     * nunca podrá pasar a través de las paredes.
     *
     * @param jugador La instancia de la clase {@link Jugador}
     * @return siempre {@code false}
     */
    @Override
    public boolean puedePasar(Jugador jugador) {
        return false;
    }

}
