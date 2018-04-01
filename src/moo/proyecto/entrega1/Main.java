package moo.proyecto.entrega1;

import moo.proyecto.entrega2.io.CargadorDisco;
import moo.proyecto.entrega2.io.CargadorMemoria;

import java.io.IOException;

public class Main {

    /**
     * Método que da inicio al programa. Se limina a instanciar el Control del juego e invocar
     * a la partida.
     * @see ControlJuego#partida()
     * @param args 
     */
    public static void main(String[] args) {
        ControlJuego cj = new ControlJuego(new CargadorMemoria());
        cj.partida();
//        try {
//            ControlJuego cj = new ControlJuego(new CargadorDisco("./niveles.txt"));
//            cj.partida();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.exit(0);
    }

}
