package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;

public class Cuerpo extends Celda {
    @Override
    public String getImagen() {
        return Const.ARCHIVO_CUERPO;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
