package moo.proyecto.entrega2.obstaculos;

import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Jugador;
import moo.proyecto.entrega2.Obstaculo;
import moo.proyecto.entrega2.items.poderes.AbrePuertas;
import moo.proyecto.entrega2.items.poderes.Rompedor;

/**
 * Clase que representa un obstáculo del tipo "Roca"
 */
public class Roca extends Obstaculo {
    /**
     * Retorna el nombre del archivo de la imagen de la Roca
     * @return {@link Const#ARCHIVO_ROCA}
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_ROCA;
    }

    /**
     * Indica si el jugador puede pasar por este obstáculo. En concreto, si el jugador
     * posee un item con poder {@link Rompedor}
     *
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} si el jugador puede pasar a través de este obstáculo.
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean puedePasar(Jugador jugador) {
        return jugador.getItem() != null && jugador.getItem().tienePoder(Rompedor.INSTANCIA);
    }

}
