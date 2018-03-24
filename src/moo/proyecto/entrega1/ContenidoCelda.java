package moo.proyecto.entrega1;

/**
 * Clase que representa los datos y el comportamiento del objeto que pueda estar contenido en
 * una celda.
 */
public class ContenidoCelda {

    /**
     * Tipo de celda, cuyos valores podrán ser {@link Const#CELDA_CABEZA},
     * {@link Const#CELDA_CUERPO}, {@link Const#CELDA_MANZANA}, {@link Const#CELDA_PARED},
     * o {@link Const#CELDA_CAJA}
     */
    private char tipo;

    /**
     * Nivel en el que está contenido el objeto. Será de utilidad cuando las cajas tengan que
     * indagar en las casillas contínuas para ver si se pueden desplazar hacia la izquierda,
     * o hasta dónde pueden caer.
     */
    private Nivel nivel; // Nota: Cuando introduzcamos herencia, se moverá a la subclase pertinente

    /**
     * Referencia a la cabeza. Será de utilidad cuando, para una casilla del tipo cabeza, queramos
     * devolver la imagen normal de la cabeza, o la imagen de la cabeza roja, cuando la serpiente
     * haya alcanzado su máxima altura.
     */
    private ControlCabeza controlCabeza;  // Nota: Cuando introduzcamos herencia, se moverá a la subclase pertinente

    /**
     * Constructor para el contenido de una celda.
     * @param tipo Tipo de contenido de celda.
     * @param nivel Nivel en el que la celda está contenida.
     */
    public ContenidoCelda(char tipo, Nivel nivel) {
        this.tipo = tipo;
        this.nivel = nivel;
    }

    /**
     * Constructor para el contenido de una celda del tipo {@link Const#CELDA_CABEZA}.
     * @param nivel Nivel en el que la celda está contenida.
     * @param controlCabeza Referencia a la cabeza de la serpiente.
     */
    public ContenidoCelda(Nivel nivel, ControlCabeza controlCabeza) {
        this.tipo = Const.CELDA_CABEZA;
        this.nivel = nivel;
        this.controlCabeza = controlCabeza;
    }

    /**
     * Retorna el nombre de archivo de imagen del objeto situado en la celda o <code>null</code>
     * si no hay ningún objeto. Para las celdas del tipo {@link Const#CELDA_CABEZA}, retornará la
     * imagen de la cabeza en rojo si ésta ha llegado al límite de alturas, o de la cabeza normal
     * en caso contrario.
     *
     * @return el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     */
    public String getImagen() {
        switch (tipo) {
            case Const.CELDA_PARED:
                return Const.ARCHIVO_PARED;
            case Const.CELDA_CABEZA:
                if(controlCabeza.isLimiteAltura()) {
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

    /**
     * Retorna <code>true</code> si el objeto es empujable (es una caja). <code>false</code> en
     * caso contrario.
     * @return <code>true</code> si el objeto es empujable (es una caja). <code>false</code> en
     * caso contrario.
     */
    public boolean isEmpujable() {
        return tipo == Const.CELDA_CAJA;
    }

    /**
     * Retorna <code>true</code> si el objeto es cogible (es una manzana). <code>false</code> en
     * caso contrario.
     * @return <code>true</code> si el objeto es cogible (es una manzana). <code>false</code> en
     * caso contrario.
     */
    public boolean isCogible() {
        return tipo == Const.CELDA_MANZANA;
    }

    /**
     * Realiza la acción resultante de coger el elemento contenido en la celda: retornará
     * {@link Const#PASO_FIN_NIVEL} si se trata de una manzana, o (@link Const#PASO_IMPOSIBLE)
     * si es cualquier otro elemento que no se pueda coger.
     *
     * @return {@link Const#PASO_FIN_NIVEL} si se trata de una manzana, o (@link Const#PASO_IMPOSIBLE)
     * si es cualquier otro elemento que no se pueda coger.
     */
    public int coger() {
        if (tipo == Const.CELDA_MANZANA) {
            return Const.PASO_FIN_NIVEL;
        }
        return Const.PASO_IMPOSIBLE;
    }

    /**
     * Realiza la acción resultante de empujar el elemento contenido en la celda hacia las filas
     * y columnas dadas por los argumentos <code>haciaFila</code> y <code>haciaColumna</code>:
     * <ul>
     *     <li>Si en la posición de destino del elemento empujado (haciaFila, haciaColumna) ya hay
     *     algún otro objeto, retornará {@link Const#PASO_IMPOSIBLE}, ya que la serpiente sólo tiene
     *     fuerza para arrastrar un solo objeto.</li>
     *     <li>Si no hay nada, empujará el objeto hacia la posición (haciaFila, haciaColumna).</li>
     *     <li>Si lo ha empujado y en esa posición no hay suelo, hará descender el objeto hasta que
     *     encuentre un suelo (simulando el efecto de que la caja cae).</li>
     * </ul>
     *
     * Si el objeto se ha podido empujar, retornará {@link Const#PASO_OK}.
     *
     * @param haciaFila fila hacia la que se empuja el elemento
     * @param haciaColumna columna hacia la que se empuja el elemento
     * @return {@link Const#PASO_OK} si se ha podido mover el objeto empujado, o
     * (@link Const#PASO_IMPOSIBLE) en caso contrario.
     */
    public int empujar(int haciaFila, int haciaColumna) {
        if (!nivel.getCelda(haciaFila, haciaColumna).isVacia()) {
            return Const.PASO_IMPOSIBLE;
        }
        while(nivel.getCelda(haciaFila + 1, haciaColumna).isVacia()) {
            haciaFila++;
        }
        nivel.getCelda(haciaFila,haciaColumna).setContenido(this);
        return Const.PASO_OK;
    }
}
