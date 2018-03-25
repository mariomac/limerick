package moo.proyecto.entrega1;

/**
 * Clase para gestionar las operaciones relativas a las diferentes celdas que componen cada
 * {@link Nivel}
 */
public abstract class Celda {

    /**
     * Todo: actualizar
     *
     * Retorna el nombre de archivo de imagen del objeto situado en la celda o <code>null</code>
     * si no hay ningún objeto. Para las celdas del tipo {@link Const#CELDA_CABEZA}, retornará la
     * imagen de la cabeza en rojo si ésta ha llegado al límite de alturas, o de la cabeza normal
     * en caso contrario.
     *
     * @return el archivo de imagen del objeto situado en la celda o "null" si no hay ningún objeto.
     */
    public abstract String getImagen();

    public abstract int intentaPasar(int df, int dc);
}
