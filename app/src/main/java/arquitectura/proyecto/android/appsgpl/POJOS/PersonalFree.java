package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class PersonalFree {
    private int idPersonal;
    private String nombrePersonal;
    private String apellidoPersonal;
    private String ocupacionPersonal;

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getOcupacionPersonal() {
        return ocupacionPersonal;
    }

    public void setOcupacionPersonal(String ocupacionPersonal) {
        this.ocupacionPersonal = ocupacionPersonal;
    }
    @Override
    public String toString() {
        return getNombrePersonal()+" "+getApellidoPersonal();
    }
}
