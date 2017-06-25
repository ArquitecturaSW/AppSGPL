package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class ResponseMostrarEquipo {
    private int estado;
    private List<Equipo> equipoList = new ArrayList<Equipo>();

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
}
