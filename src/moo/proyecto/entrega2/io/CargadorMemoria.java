package moo.proyecto.entrega2.io;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.DatosNiveles;
import moo.proyecto.entrega1.Nivel;

import java.util.ArrayList;
import java.util.List;

/**
 * CargadorMemoria implementa la interfaz {@link CargadorNiveles}, y carga los
 * niveles a partir de los datos de la clase {@link DatosNiveles}.
 */
public class CargadorMemoria implements CargadorNiveles {
    /**
     * Retorna una lista de niveles a partir de los datos de los niveles en la
     * clase {@link DatosNiveles}.
     * @return una lista de niveles a partir de los datos de los niveles en la
     *         clase {@link DatosNiveles}.
     */
    @Override
    public List<Nivel> carga() {
        List<Nivel> niveles = new ArrayList<>();
        for(char[][] mapa : DatosNiveles.MAPAS) {
            niveles.add(new Nivel(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS, mapa));
        }
        return niveles;
    }
}
