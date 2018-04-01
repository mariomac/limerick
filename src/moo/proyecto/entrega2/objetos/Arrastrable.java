package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public abstract class Arrastrable extends Celda {


    public Arrastrable(Nivel nivel, int fila, int columna) {
        super(nivel, fila, columna);
    }

    @Override
    public int intentaPasar(int df, int dc) {
        int haciaFila = fila + df;
        int haciaColumna = columna + dc;
        if (nivel.hayCelda(haciaFila, haciaColumna)) {
            return Const.PASO_IMPOSIBLE;
        }
        while(!nivel.hayCelda(haciaFila + 1, haciaColumna)) {
            haciaFila++;
        }
        nivel.setCelda(haciaFila,haciaColumna, this);
        this.fila = haciaFila;
        this.columna = haciaColumna;
        nuevaPosicion();
        return Const.PASO_OK;
    }

    protected abstract void nuevaPosicion();


}
