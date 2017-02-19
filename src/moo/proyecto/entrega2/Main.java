package moo.proyecto.entrega2;

import java.io.IOException;

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

        try {
            // Quizás aquí sería buena idea proporcionarle una abstracción de
            // JFileChooser para que el alumno pueda elegir fichero y tenga más
            // juego con la gestión de la excepción
            cj.cargaNiveles("niveles.txt");
            cj.empiezaPartida();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArchivoMalFormateadoException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
    
}
