package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class Equipo {
    private int idPersonal;
    private String nombrePersonal;
    private String apellidoPersonal;
    private int idTipoPersonal;
    private String ocupacionPersonal;
    private int telefonoPersonal;
    private String correoPersonal;

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

    public int getIdTipoPersonal() {
        return idTipoPersonal;
    }

    public void setIdTipoPersonal(int idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
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

    public int getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(int telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }
}
