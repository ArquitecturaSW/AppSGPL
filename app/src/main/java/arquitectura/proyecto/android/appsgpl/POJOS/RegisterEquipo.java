package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class RegisterEquipo {
    private int idProyecto;
    private int idPersonal;

    public RegisterEquipo(int idProyecto,int idPersonal){
        this.idProyecto=idProyecto;
        this.idPersonal=idPersonal;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }
}
