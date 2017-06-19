package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 18-Jun-17.
 */

public class ResponseMostrarEntregable {
    private int estado;
    private List<Entregable> entregableList = new ArrayList<Entregable>();


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    public List<Entregable> getEntregableList() {
        return entregableList;
    }

    public void setEntregableList(List<Entregable> entregableList) {
        this.entregableList = entregableList;
    }
}
