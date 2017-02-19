package moo.proyecto.entrega2.obstaculos;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Jugador;
import moo.proyecto.entrega2.Obstaculo;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;

/**
 * Clase que representa un Obstáculo del tipo "Fuego"
 */
public class Fuego extends Obstaculo {
    /**
     * Retorna el nombre del archivo de la imagen del Fuego
     * @return {@link Const#ARCHIVO_FUEGO}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_FUEGO;
    }

    /**
     * Indica si el jugador puede pasar por este obstáculo. En concreto, si el jugador
     * posee un item con poder {@link ApagaFuegos}
     *
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} si el jugador puede pasar a través de este obstáculo.
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean puedePasar(Jugador jugador) {
        return jugador.getItem() != null && jugador.getItem().tienePoder(ApagaFuegos.INSTANCIA);
    }

}
