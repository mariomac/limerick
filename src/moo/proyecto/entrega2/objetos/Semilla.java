package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Semilla extends Celda {

    private boolean arena = false;
    private boolean agua = false;

    public Semilla(Nivel nivel, int fila, int columna) {
        super(nivel, fila, columna);
    }

    public void setArena(boolean arena) {
        this.arena = arena;
        germina();
    }

    public void setAgua(boolean agua) {
        this.agua = agua;
        germina();
    }

    // Germina asume que el agua o la arena se han eliminado de encima suya
    private void germina() {
        if (!arena || !agua) {
            return;
        }
        // Alza el tronco
        int f;
        for(f = fila ; nivel.getCelda(f - 1, columna) == null ; f--) {
            nivel.setCelda(f, columna, new Pared(Const.ARCHIVO_TRONCO));
        }
        nivel.setCelda(f, columna, new Pared(Const.ARCHIVO_RAMA));
        // Rama izquierda
        int c;
        for(c = columna - 1 ; nivel.getCelda(f, c) == null ; c--) {
            nivel.setCelda(f, c, new Pared(Const.ARCHIVO_RAMA));
        }
        nivel.setCelda(f+1,c+1, new Manzana());
        // Rama derecha
        for(c = columna + 1 ; nivel.getCelda(f, c) == null ; c++) {
            nivel.setCelda(f, c, new Pared(Const.ARCHIVO_RAMA));
        }
        nivel.setCelda(f+1,c-1, new Manzana());

    }

    @Override
    public String getImagen() {
        if (arena) {
            return Const.ARCHIVO_SEMILLA_ARENA;
        }
        if (agua) {
            return Const.ARCHIVO_SEMILLA_AGUA;
        }
        return Const.ARCHIVO_SEMILLA;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
