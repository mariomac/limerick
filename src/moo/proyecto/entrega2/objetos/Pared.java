package moo.proyecto.entrega2.objetos;

import moo.proyecto.entrega1.Celda;
import moo.proyecto.entrega1.Const;
import moo.proyecto.entrega1.Nivel;

public class Pared  extends Celda {

    private String imagen = Const.ARCHIVO_PARED;

    public Pared() {
        super(null, 0, 0);
    }

    public Pared(String imagen) {
        this();
        this.imagen = imagen;
    }

    @Override
    public String getImagen() {
        return imagen;
    }

    @Override
    public int intentaPasar(int df, int dc) {
        return Const.PASO_IMPOSIBLE;
    }
}
