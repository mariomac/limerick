package moo.proyecto.entrega2;

/**
 *
 * @author mmacias
 */

import moo.proyecto.entrega2.Celda;
import moo.proyecto.entrega2.Item;

/**
 * Clase para gestionar al personaje que se moverá por el tablero.
 * 
 */
public class Jugador {

    /**
     * La celda en la que el jugador se encuentra en un cierto instante del juego
     */
    private Celda celda;

    /**
    Item que ha recogido del suelo. Si no ha recogido item alguno su valor es null
    */
    private Item item;

    /**
     * Constructor para Jugador. Lo crea posicionándolo en una determindada celda
     * del tablero
     * 
     * @param c celda del tablero en la que queda depositado el jugador
     */
    public Jugador(Celda c) {
        celda = c;
    }
    
    /**
     * Devuelve el número de fila de la celda que ocupa el jugador.
     * @return el número de fila de la celda
     */
    public int getPosFila() {
        return celda.getFila();
    }
    
    /**
     * Devuelve el número de columna de la celda que ocupa el jugador.
     * @return el número de columna de la celda
     */
    public int getPosCol() {
        return celda.getColumna();
    }
 
    /**
     * Devuelve el item que tiene el jugador.
     * @return referencia al item que obra en poder del jugador; null si no 
     * tiene ningún item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Asigna valor al atributo item. Con este método se simula que el jugador 
     * coja un item del suelo.
     * @param item referencia al item que el jugador recoge del suelo.
     */
    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * Desplaza al jugador a la celda pasada como argumento.
     * @param c celda en la que se deposita el jugador.
     */
    public void setCelda(Celda c) {
        celda = c;
    }
}
