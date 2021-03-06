package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Caja extends Arrastrable {
    public Caja(Nivel nivel, int fila, int columna) {
        super(nivel, fila, columna);
    }

    @Override
    protected void nuevaPosicion() {
        // No hace nada
    }

    @Override
    public String getImagen() {
        return Const.ARCHIVO_CAJA;
    }
}
