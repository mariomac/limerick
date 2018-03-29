package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;

public class Manzana  extends Celda {

    public Manzana() {
        super(null, 0, 0);
    }

    @Override
    public String getImagen() {
        return Const.ARCHIVO_MANZANA;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_FIN_NIVEL;
    }
}
