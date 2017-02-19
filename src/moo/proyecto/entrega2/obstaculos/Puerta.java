package moo.proyecto.entrega2.obstaculos;

import moo.proyecto.entrega2.Celda;
import moo.proyecto.entrega2.Const;
import moo.proyecto.entrega2.Jugador;
import moo.proyecto.entrega2.Obstaculo;
import moo.proyecto.entrega2.items.poderes.AbrePuertas;
import moo.proyecto.entrega2.items.poderes.ApagaFuegos;

/**
 * Clase que representa un obstáculo del tipo "Puerta"
 */
public class Puerta extends Obstaculo {
    /**
     * Retorna el nombre del archivo de la imagen de la Puerta
     * @return Const#ARCHIVO_PUERTA
     */
    public String getArchivoImagen() {
        return Const.ARCHIVO_PUERTA;
    }

    /**
     * Indica si el jugador puede pasar por este obstáculo. En concreto, si el jugador
     * posee un item con poder {@link AbrePuertas}
     *
     * @param jugador La instancia de la clase {@link Jugador}
     * @return {@code true} si el jugador puede pasar a través de este obstáculo.
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean puedePasar(Jugador jugador) {
        return jugador.getItem() != null && jugador.getItem().tienePoder(AbrePuertas.INSTANCIA);
    }

    /**
     * Realiza las acciones pertinentes a pasar por una puerta según la descripción definida en
     * {@link Obstaculo#pasa}. Además retornará {@code true} ya que el nivel acaba aquí.
     * @param j el jugador
     * @param c la celda donde el jugador acaba de pasar
     * @return Siempre {@code true}
     */
    @Override
    public boolean pasa(Jugador j, Celda c) {
        super.pasa(j, c);
        return true;
    }
}
