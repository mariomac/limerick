package moo.proyecto.entrega2.io;

import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorNiveles {
    /**
     * Carga los niveles definidos en un archivo de texto según el siguiente formato:
     * <li>Las sucesivas líneas del archivo representan los distintos niveles con una estructura similar
     * a las matrices de chars de la entrega 1</li>
     * <li>Cada línea representa una fila de celdas de cada nivel, y cada caracter representa un tipo de
     * celda según definido en la clase {@link Const}</li>
     * <li>Cada línea debe tener un mínimo de {@link Const#NIVEL_COLUMNAS} caracteres, que representan las
     * sucesivas columnas para cada fila. Los carácteres que sobrepasen el número definido por
     * {@link Const#NIVEL_COLUMNAS}, serán ignorados</li>
     * <li>Cada nivel debe tener un número de filas representado por {@link Const#NIVEL_FILAS}</li>
     * <li>Una línea que empieza con los caracteres '//' es una línea a ignorar</li>
     * <li>Una línea que empieza con los caracteres '-- FIN --' indica que ya se han leído todos los niveles,
     * y la lectura de datos acaba ahí.</li>
     * <p>Mirar como ejemplo el archivo 'niveles.txt'.</p>
     *
     * @param rutaFichero La ruta del fichero del cual cargar los niveles.
     * @throws IOException                   Cuando haya algún problema de Entrada/Salida (relanzado si las clases
     *                                       de Java que gestionan la Entrada/Salida
     * @throws ArchivoMalFormateadoException Si el archivo no tiene el formato descrito en la documentación
     *                                       de este método (por ejemplo si un nivel tiene menos de {@link Const#NIVEL_FILAS} filas o
     *                                       una fila tiene menos de {@link Const#NIVEL_COLUMNAS} columnas
     */
    public static List<Nivel> carga(String rutaFichero) throws IOException, ArchivoMalFormateadoException {
        List<Nivel> niveles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(rutaFichero));

        // Inicializamos el array que le pasaremos al nivel
        int fila = 0;
        char[][] mapa = new char[Const.NIVEL_FILAS][];
        while (true) {

            // Leemos la siguiente línea del nivel
            String linea = reader.readLine();

            // Tratamos el fin de archivo
            if (linea == null || linea.startsWith("-- FIN --")) {
                // Error si el archivo ha acabado en mitad de la lectura
                // de un nivel
                if (fila != 0) {
                    throw new ArchivoMalFormateadoException(
                            "El archivo ha acabado sin que el nivel leído estuviera completado");
                }
                return niveles;
            }

            // Ignoramos líneas con comentarios
            if (linea.startsWith("//")) {
                continue;
            }

            // Añadimos una fila al mapa actual
            // Comprobamos que tenga la longitud adecuada
            if (linea.length() < Const.NIVEL_COLUMNAS) {
                throw new ArchivoMalFormateadoException(
                        "Leída línea demasiado corta: '" + linea + "'");
            }
            mapa[fila++] = linea.substring(0, Const.NIVEL_COLUMNAS).toCharArray();

            // Si ya hemos rellenado el mapa de un nivel, lo creamos y lo añadimos
            // a la lista. Y vamos a por el siguiente.
            if (fila >= Const.NIVEL_FILAS) {
                Nivel nivel = new Nivel(Const.NIVEL_FILAS, Const.NIVEL_COLUMNAS, mapa);
                niveles.add(nivel);
                // Y volvemos a empezar
                fila = 0;
                mapa = new char[Const.NIVEL_FILAS][];
            }
        }
    }
}
