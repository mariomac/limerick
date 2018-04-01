package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Semilla extends Celda {
    public Semilla() {
        super(null, 0, 0);
    }

    @Override
    public String getImagen() {
        return Const.ARCHIVO_SEMILLA;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
