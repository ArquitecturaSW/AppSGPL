package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 13-Jun-17.
 */

public class ResponseProyecto {
    private int estado;
    private List<Proyecto> proyectoList = new ArrayList<Proyecto>();

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }
}
