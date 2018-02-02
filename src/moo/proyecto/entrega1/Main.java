package moo.proyecto.entrega1;

public class Main {

    /**
     * Método que da inicio al programa.
     * <br><br>Crear un nuevo objeto ControlJuego.
     * <br>Cargar los niveles.
     * <br>Empezar partida.
     * @param args the command line arguments
     * @throws InterruptedException lanza una excepción de esta clase si se
     * produce alguna situación anómala.
     */
    public static void main(String[] args) throws InterruptedException {
        ControlJuego cj = new ControlJuego();

        cj.cargaNiveles();
        cj.partida();

        System.exit(0);
    }

}
