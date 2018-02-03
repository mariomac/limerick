package moo.proyecto.entrega1;

public class ControlCabeza {

    private int fila;

    private int columna;

    private int altura = 0;

    private Nivel nivel;

    public ControlCabeza(Nivel nivel, int fila, int columna) {
        this.nivel = nivel;
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isLimiteAltura() {
        return altura >= Const.MAX_ALTURA;
    }

    // TODO: meter en un static method para reaprovechar con el objeto caja
    public void actualizaPosicion(int df, int dc) {
        if (df < 0) {
            altura++;
        } else {
            altura = 0;
        }
        nivel.getCelda(fila, columna).setContenido(new ContenidoCelda(Const.CELDA_CUERPO, nivel));
        fila += df;
        columna += dc;
        while (nivel.getCelda(fila + 1, columna).isVacia()) {
            nivel.getCelda(fila, columna).setContenido(new ContenidoCelda(Const.CELDA_CUERPO, nivel));
            fila++;
        }
        nivel.getCelda(fila, columna).setContenido(new ContenidoCelda(nivel, this));
    }
}
