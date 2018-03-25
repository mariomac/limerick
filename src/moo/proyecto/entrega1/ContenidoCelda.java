package moo.proyecto.entrega1;

/**
 * Clase que representa los datos y el comportamiento del objeto que pueda estar contenido en
 * una celda.
 */
public abstract class ContenidoCelda {

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
