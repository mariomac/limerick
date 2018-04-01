package moo.proyecto.entrega2.io;

import moo.proyecto.entrega1.Nivel;

import java.io.IOException;
import java.util.List;

public interface CargadorNiveles {
    List<Nivel> carga() throws ArchivoMalFormateadoException;
}
