package moo.proyecto.entrega1;

import java.util.ArrayList;
import java.util.List;

import moo.proyecto.gui.InterfazGrafica;

/**
 * @author mmacias
 */

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
    private int nivel = 8;

    /**
     * Permite jugar la partida.
     * Acciones:<br>
     * <br>Mientras el identificador del nivel sea menor que el id del máximo nivel
     * <br>&nbsp;&nbsp;&nbsp;Conseguir un clon del nivel correspondiente al identificador de nivel
     * <br>&nbsp;&nbsp;&nbsp;Hacer
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;redibujar la GUI
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;interaccionar con el usuario para que éste pulse una tecla para mover el jugador.
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;decidir la fila y columna a la que se intentará mover el jugador
     * <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;intentar mover el jugador si no se ha acabado el nivel
     * <br> &nbsp;&nbsp;&nbsp;Mientras no se haya acabado el nivel
     * <br>&nbsp;&nbsp;&nbsp;redibujar la GUI
     * <br>&nbsp;&nbsp;&nbsp;incrementar el identificador de nivel
     */
    public void empiezaPartida() {
        while (nivel < niveles.size()) {
            Nivel nivelActual = niveles.get(nivel).clonar();

            boolean nivelAcabado = false;
            do {
                redibuja(nivelActual);
                int tecla = gui.leeTeclaPulsada();
                int f = nivelActual.getCabeza().getFila();
                int c = nivelActual.getCabeza().getColumna();

                switch (tecla) {
                    case InterfazGrafica.TECLA_ABAJO:
                        f++;
                        break;
                    case InterfazGrafica.TECLA_ARRIBA:
                        f--;
                        break;
                    case InterfazGrafica.TECLA_DERECHA:
                        c++;
                        break;
                    case InterfazGrafica.TECLA_IZQUIERDA:
                        c--;
                        break;
                    case InterfazGrafica.TECLA_R:
                        nivel--;
                        nivelAcabado = true;
                        break;

                }
                if (!nivelAcabado) {
                    nivelAcabado = nivelActual.intentaMoverCabeza(f, c);
                }
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
                String archivo = nivelActual.getCelda(f,c).getArchivoImagen();
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
