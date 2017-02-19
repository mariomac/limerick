package moo.proyecto.entrega2.obstaculos;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Jugador;
import moo.proyecto.entrega2.Obstaculo;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;
import moo.proyecto.entrega2.items.poderes.Fundidor;

/**
 * Clase que representa un obstáculo del tipo "Hielo"
 */
public class Hielo extends Obstaculo {
    /**
     * Retorna el nombre del archivo de la imagen del Hielo
     * @return {@link Const#ARCHIVO_HIELO}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_HIELO;
    }

    /**
     * Indica si el jugador puede pasar por este obstáculo. En concreto, si el jugador
     * posee un item con poder {@link Fundidor}
     *
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} si el jugador puede pasar a través de este obstáculo.
     *         {@code false} en caso contrario.
     */

    @Override
    public boolean puedePasar(Jugador jugador) {
        return jugador.getItem() != null && jugador.getItem().tienePoder(Fundidor.INSTANCIA);
    }

}
