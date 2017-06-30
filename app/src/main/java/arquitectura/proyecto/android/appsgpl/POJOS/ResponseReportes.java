package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 30-Jun-17.
 */

public class ResponseReportes {

    private List<Cantidad> cantidad = new ArrayList<Cantidad>();

    public List<Cantidad> getCantidad() {
            return cantidad;
    }
    public void setCantidad(List<Cantidad> cantidad) {
            this.cantidad = cantidad;
    }
}
