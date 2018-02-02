package moo.proyecto.entrega1;

import java.util.ArrayList;
import java.util.List;

import moo.proyecto.gui.InterfazGrafica;

/**
 * Clase que implementa el control del juego.
 *
 */
public class ControlJuego {

    /**
     * Secuencia de los distintos niveles en los que se jugará
     */
    private List<Nivel> niveles = new ArrayList<>();
    /**
     * Interfaz gráfica.
     */
    InterfazGrafica gui = new InterfazGrafica(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS);

    /**
     * Identificador de nivel. Valor inicial: 0. Conforme se sube de nivel se
     * incrementa su valor hasta un valor máximo.
     */
    private int nivel = 2;

    public void partida() {
        while (nivel < niveles.size()) {
            Nivel nivelActual = niveles.get(nivel);
            nivelActual.inicializar();

            boolean nivelAcabado = false;
            do {
                redibuja(nivelActual);
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
            redibuja(nivelActual);
            nivel++;
        }
    }

    /**
     * Este método redibuja la GUI.
     * <br><br>Para cada celda del recinto del nivel en curso, colocar la imagen correspondiente a
     * lo que haya en ella (obstáculo, item o nada).
     * <br>Colocar la imagen del jugador en la celda que corresponda.
     * <br>Colorar la imagen del item que tenga el jugador en el bolsillo en la
     * celda que ocupa la primera fila y la última columna del recinto de juego.
     * @param nivelActual identificador del nivel en juego.
     */
    private void redibuja(Nivel nivelActual) {
        for (int f = 0; f < Const.NIVEL_FILAS; f++) {
            for (int c = 0; c < Const.NIVEL_COLUMNAS; c++) {
                String archivo = nivelActual.getCelda(f,c).getImagen();
                gui.colocaImagen(archivo, f, c);
            }
        }
    }

    public void cargaNiveles() {
        for(char[][] mapa : DatosNiveles.MAPAS) {
            niveles.add(new Nivel(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS, mapa));
        }
    }
}
