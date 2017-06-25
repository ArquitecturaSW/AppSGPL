package arquitectura.proyecto.android.appsgpl.POJOS;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Views.PersonalND;

/**
 * Created by Jair Barzola on 19-Jun-17.
 */

public class ResponsePersonal {
    private int estado;
    private List<Personal> personalList = new ArrayList<Personal>();

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }
}
