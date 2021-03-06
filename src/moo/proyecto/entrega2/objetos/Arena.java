package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Arena extends Arrastrable {
    public Arena(Nivel nivel, int fila, int columna) {
        super(nivel, fila, columna);
    }

    @Override
    protected void nuevaPosicion() {
        Celda debajo = nivel.getCelda(fila + 1, columna);
        if ( debajo instanceof Semilla ) {
            nivel.setCelda(fila, columna, null);
            ((Semilla)debajo).setArena(true);
        }
    }

    @Override
    public String getImagen() {
        return Const.ARCHIVO_ARENA;
    }
}
