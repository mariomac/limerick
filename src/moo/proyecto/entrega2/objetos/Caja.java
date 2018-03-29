package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Caja extends Celda {


    public Caja(Nivel nivel, int fila, int columna) {
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
        return Const.PASO_OK;
    }

    @Override
    public String getImagen() {
        return Const.ARCHIVO_CAJA;
    }
}
