package moo.proyecto.entrega1;

public class ContenidoCelda {

    /**
     * Tipo de celda, cuyos valores podran ser {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA},
     * {@link Const#CELDA_VACIA}, {@link Const#CELDA_PARED}, {@link Const#CELDA_CAJA}
     */
    private char tipo;

    // Cuando introduzcamos herencia, se moverá a la subclase pertinente
    private Nivel nivel;

    // Cuando introduzcamos herencia, se moverá a la subclase pertinente
    private ControlCabeza controlCabeza;

    public ContenidoCelda(char tipo, Nivel nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public ContenidoCelda(char tipo, Nivel nivel, ControlCabeza controlCabeza) {
        this.tipo = tipo;
        this.nivel = nivel;
        this.controlCabeza = controlCabeza;
    }

    /**
     * Retorna el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     *
     * @return el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     */
    public String getImagen() {
        switch (tipo) {
            case Const.CELDA_PARED:
                return Const.ARCHIVO_PARED;
            case Const.CELDA_CABEZA:
                if(controlCabeza.isLimiteSubidas()) {
                    return Const.ARCHIVO_CABEZA_ROJA;
                } else {
                    return Const.ARCHIVO_CABEZA;
                }
            case Const.CELDA_CAJA:
                return Const.ARCHIVO_CAJA;
            case Const.CELDA_CUERPO:
                return Const.ARCHIVO_CUERPO;
            case Const.CELDA_MANZANA:
                return Const.ARCHIVO_MANZANA;
            default:
                return null;
        }
    }

    public boolean esEmpujable() {
        return tipo == Const.CELDA_CAJA;
    }

    public boolean esCogible() {
        return tipo == Const.CELDA_MANZANA;
    }

    public int coger() {
        if (tipo == Const.CELDA_MANZANA) {
            return Const.PASO_FIN_NIVEL;
        }
        return Const.PASO_IMPOSIBLE;
    }

    public int empujar(int fila, int columna) {
        if (nivel.getCelda(fila, columna).getContenido() != null) {
            return Const.PASO_IMPOSIBLE;
        }
        if (tipo == Const.CELDA_CAJA) {
            while(nivel.getCelda(fila + 1, columna).getContenido() == null) {
                fila++;
            }
            nivel.getCelda(fila,columna).setContenido(this);
        }
        return Const.PASO_OK;
    }
}
