package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.ContenidoCelda;

public class Manzana extends ContenidoCelda {
    @Override
    public String getImagen() {
        return Const.ARCHIVO_MANZANA;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_FIN_NIVEL;
    }
}
