package moo.proyecto.entrega2;

import moo.proyecto.gui.InterfazGrafica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private int nivel = 0;

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
                int f = nivelActual.getJugador().getPosFila();
                int c = nivelActual.getJugador().getPosCol();

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
                    nivelAcabado = nivelActual.intentaMoverJugador(f, c);
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
        gui.colocaImagen(Const.ARCHIVO_JUGADOR, nivelActual.getJugador().getPosFila(), nivelActual.getJugador().getPosCol());
        Item item = nivelActual.getJugador().getItem();
        if(item != null) {
            String archivoItem = item.getArchivoImagen();
            gui.colocaImagen(archivoItem,0,Const.NIVEL_COLUMNAS-1);
        }
    }

    /**
     * Carga los niveles definidos en un archivo de texto según el siguiente formato:
     * <li>Las sucesivas líneas del archivo representan los distintos niveles con una estructura similar
     *     a las matrices de chars de la entrega 1</li>
     * <li>Cada línea representa una fila de celdas de cada nivel, y cada caracter representa un tipo de
     *     celda según definido en la clase {@link Const}</li>
     * <li>Cada línea debe tener un mínimo de {@link Const#NIVEL_COLUMNAS} caracteres, que representan las
     *     sucesivas columnas para cada fila. Los carácteres que sobrepasen el número definido por
     *     {@link Const#NIVEL_COLUMNAS}, serán ignorados</li>
     * <li>Cada nivel debe tener un número de filas representado por {@link Const#NIVEL_FILAS}</li>
     * <li>Una línea que empieza con los caracteres '---' indica que ahí acaban los datos de un nivel.
     *     Las siguientes líneas representarán los datos del siguiente nivel</li>
     * <li>Una línea que empieza con los caracteres '//' es una línea a ignorar</li>
     * <li>Una línea que empieza con los caracteres '===' indica que ya se han leído todos los niveles</li>
     * <li>El archivo, pues, deberá acabar con una línea '---' seguida de una línea '==='</li>
     * <p>Mirar como ejemplo el archivo 'niveles.txt'.</p>
     * @param rutaFichero La ruta del fichero del cual cargar los niveles.
     * @throws IOException Cuando haya algún problema de Entrada/Salida (relanzado si las clases
     *         de Java que gestionan la Entrada/Salida
     * @throws ArchivoMalFormateadoException Si el archivo no tiene el formato descrito en la documentación
     *         de este método (por ejemplo si un nivel tiene menos de {@link Const#NIVEL_FILAS} filas o
     *         una fila tiene menos de {@link Const#NIVEL_COLUMNAS} columnas
     */
    public void cargaNiveles(String rutaFichero) throws IOException, ArchivoMalFormateadoException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaFichero));
        List<String> filasNivel = new ArrayList<>();
        int numLineaArchivo = 1;
        String leida = reader.readLine();
        while(leida != null && !leida.startsWith("===")) {
            if(leida.startsWith("//")) {
                // Ignorar
            } else if(leida.startsWith("---")) {
                if(filasNivel.size() != Const.NIVEL_FILAS) {
                    throw new ArchivoMalFormateadoException("Línea " + numLineaArchivo + " indica que un nivel ha acabado" +
                            " pero no se han leido " + Const.NIVEL_FILAS + " filas");
                }
                // Nueva pantalla
                char[][] mapa = new char[filasNivel.size()][];
                int fila = 0;
                for(String filaStr: filasNivel) {
                    mapa[fila] = filaStr.toCharArray();
                    fila++;
                }
                niveles.add(new Nivel(Const.NIVEL_FILAS,Const.NIVEL_COLUMNAS, mapa));
                filasNivel.clear();
            } else {
                // hacemos substring para no pasarnos del máximo de largo
                if(leida.length() < Const.NIVEL_COLUMNAS) {
                    throw new ArchivoMalFormateadoException("Línea " + numLineaArchivo + " más corta de lo esperado: <"
                                        + leida + ">. El tamaño mínimo debe ser " + Const.NIVEL_COLUMNAS);
                }
                filasNivel.add(leida.substring(0,Const.NIVEL_COLUMNAS));
            }
            leida = reader.readLine();
            numLineaArchivo++;
        }

    }
}
