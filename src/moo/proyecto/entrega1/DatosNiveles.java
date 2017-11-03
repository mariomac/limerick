/*
 * Copyright 2017 Midokura SARL
 */

package moo.proyecto.entrega1;

import java.util.List;

public class DatosNiveles {

    /**
     * Vector de 3 dimensiones MAPAS[n][f][c] donde:
     * <ul>
     * <li><pre>n</pre>: número de nivel</li>
     * <li><pre>f</pre>: fila para el nivel n</li>
     * <li><pre>c</pre>: columna para el nivel n</li>
     * </ul>
     */
    public static final char[][][] MAPAS = new char[][][]{
            {
                    "###################".toCharArray(),
                    "#    #            #".toCharArray(),
                    "#   #             #".toCharArray(),
                    "#  ############   #".toCharArray(),
                    "#   #         #   #".toCharArray(),
                    "#    #        #   #".toCharArray(),
                    "#             #   #".toCharArray(),
                    "#             #   #".toCharArray(),
                    "#     ########    #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#  M              #".toCharArray(),
                    "############### ###".toCharArray(),
                    "######O       K  K#".toCharArray(),
                    "###################".toCharArray()
            },
            {
                    "###################".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "# O             M #".toCharArray(),
                    "######         ####".toCharArray(),
                    "## ###            #".toCharArray(),
                    "## ###            #".toCharArray(),
                    "## ###            #".toCharArray(),
                    "## ###            #".toCharArray(),
                    " # # #     ########".toCharArray(),
                    "#   ##            #".toCharArray(),
                    "## ###            #".toCharArray(),
                    "######            #".toCharArray(),
                    "###################".toCharArray()
            },
            {
                    "###################".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "#                 #".toCharArray(),
                    "###################".toCharArray()
            },
    };
}
