package moo.proyecto.entrega2.io;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.DatosNiveles;
import moo.proyecto.entrega1.Nivel;

import java.util.ArrayList;
import java.util.List;

public class CargadorMemoria implements CargadorNiveles {
    @Override
    public List<Nivel> carga() throws ArchivoMalFormateadoException {
        List<Nivel> niveles = new ArrayList<>();
        for(char[][] mapa : DatosNiveles.MAPAS) {
            niveles.add(new Nivel(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS, mapa));
        }
        return niveles;
    }
}
