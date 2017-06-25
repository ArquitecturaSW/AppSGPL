package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 24-Jun-17.
 */

public class ResponseHistorial {
    private int estado;
    private List<Historial> historialList = new ArrayList<Historial>();

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }
}
