package arquitectura.proyecto.android.appsgpl.POJOS;

import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class ResponsePersonalFree {
    private Integer estado;
    private List<PersonalFree> perList = new ArrayList<PersonalFree>();

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<PersonalFree> getPerList() {
        return perList;
    }

    public void setPerList(List<PersonalFree> perList) {
        this.perList = perList;
    }
}
