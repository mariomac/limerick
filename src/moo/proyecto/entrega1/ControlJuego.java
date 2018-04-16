package moo.proyecto.entrega1;

import moo.proyecto.entrega2.io.ArchivoMalFormateadoException;
import moo.proyecto.entrega2.io.CargadorNiveles;
import moo.proyecto.gui.InterfazGrafica;

import java.util.List;

/**
 * Clase que implementa el control del juego. Se encarga de gestionar la carga de los datos de los
 * niveles, el bucle principal de la partida, así como de recoger la entrada del teclado y
 */
public class ControlJuego {

    private CargadorNiveles cargador;
    /**
     * Secuencia de los distintos niveles en los que se desarrolla el juego.
     */
    private List<Nivel> niveles;

    /**
     * Interfaz gráfica.
     */
    private InterfazGrafica gui = new InterfazGrafica(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS);

    /**
     * Identificador de nivelActual. Valor inicial: 0. Conforme se sube de nivelActual se
     * incrementa su valor hasta un valor máximo.
     */
    private int nivelActual = 0;

    /**
     * Instancia el control del juego
     * @param cargador La clase implementadora de {@link CargadorNiveles} que
     *                 proporcione acceso a la carga de los niveles.
     */
    public ControlJuego(CargadorNiveles cargador) {
        this.cargador = cargador;
    }

    /**
     * Bucle principal del juego, se encargará de cargar los niveles y luego recorrerlos uno a uno,
     * y para cada nivel:
     * <ul>
     *     <li>Lo inicializa.</li>
     *     <li>Mientras la cabeza no llegue a comer la manzana:
     *     <ul>
     *         <li>Redibuja el nivel actual.</li>
     *         <li>Gestiona la entrada de datos del teclado, moviendo la serpiente en la dirección
     *         deseada, o reiniciando el nivel si se ha pulsado la tecla R.</li>
     *     </ul>
     *     </li>
     *     <li>Cuando se haya llegado al final del nivel, se pasa al siguiente nivel, se
     *     inicializa y se vuelve al bucle anterior.</li>
     *     <li>El método finaliza cuando se hayan recorrido todos los niveles</li>
     * </ul>
     */
    public void partida() {
        try {
            cargaNiveles();
        } catch (Exception e) {
            System.err.println("Error cargando archivo de niveles! " + e.getMessage());
            return;
        }

        while (nivelActual < niveles.size()) {
            Nivel nivelActual = niveles.get(this.nivelActual);
            nivelActual.inicializar();

            boolean nivelAcabado;
            do {
                redibujaNivelActual();
                int tecla = gui.leeTeclaPulsada();
                int df = 0;
                int dc = 0;
                switch (tecla) {
                    case InterfazGrafica.TECLA_ARRIBA:
                        df--;
                        break;
                    case InterfazGrafica.TECLA_DERECHA:
                        dc++;
                        break;
                    case InterfazGrafica.TECLA_IZQUIERDA:
                        dc--;
                        break;
                    case InterfazGrafica.TECLA_R:
                        nivelActual.inicializar();
                        break;
                }
                nivelAcabado = nivelActual.mueveCabeza(df, dc) == Const.PASO_FIN_NIVEL;
            } while (!nivelAcabado);
            redibujaNivelActual();
            this.nivelActual++;
        }
    }

    /**
     * Este método redibuja el nivel actual en la {@link InterfazGrafica}.
     * Para cada celda del recinto del nivelActual en curso, colocar la imagen correspondiente a
     * lo que haya en ella, en la posición correspondiente.
     *
     * @see Celda#getImagen()
     * @see InterfazGrafica#colocaImagen(String, int, int)
     */
    public void redibujaNivelActual() {
        for (int f = 0; f < Const.NIVEL_FILAS; f++) {
            for (int c = 0; c < Const.NIVEL_COLUMNAS; c++) {
                if (niveles.get(nivelActual).hayCelda(f, c)) {
                    String archivo = niveles.get(nivelActual).getCelda(f, c).getImagen();
                    gui.colocaImagen(archivo, f, c);
                } else {
                    gui.colocaImagen(null, f, c);
                }
            }
        }
    }

    /**
     * Crea y guarda en memoria los niveles a partir de los datos devueltos por
     * la implementación de {@link CargadorNiveles} pasados en el constructor
     * de esta clase.
     * @see {@link #ControlJuego(CargadorNiveles)}
     */
    public void cargaNiveles() throws ArchivoMalFormateadoException {
        niveles = cargador.carga();
    }
}
