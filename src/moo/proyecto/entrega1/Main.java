package moo.proyecto.entrega1;

public class Main {

    /**
     * Método que da inicio al programa. Se limina a instanciar el Control del juego e invocar
     * a la partida.
     * @see ControlJuego#partida()
     * @param args 
     */
    public static void main(String[] args) {
        ControlJuego cj = new ControlJuego();
        cj.partida();
        System.exit(0);
    }

}
