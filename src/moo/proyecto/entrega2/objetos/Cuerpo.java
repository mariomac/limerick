package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.ContenidoCelda;

public class Cuerpo extends ContenidoCelda {
    @Override
    public String getImagen() {
        return Const.ARCHIVO_CUERPO;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
