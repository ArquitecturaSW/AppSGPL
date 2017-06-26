package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 25-Jun-17.
 */

public class Jefe {
    private int idPersonal;
    private String usuarioPersonal;
    private String passwordPersonal;
    private int idProyecto;

    public Jefe(int idPersonal,String usuarioPersonal,String passwordPersonal,int idProyecto){
        this.idPersonal=idPersonal;
        this.usuarioPersonal=usuarioPersonal;
        this.passwordPersonal=passwordPersonal;
        this.idProyecto=idProyecto;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getUsuarioPersonal() {
        return usuarioPersonal;
    }

    public void setUsuarioPersonal(String usuarioPersonal) {
        this.usuarioPersonal = usuarioPersonal;
    }

    public String getUsuarioPassword() {
        return passwordPersonal;
    }

    public void setUsuarioPassword(String passwordPersonal) {
        this.passwordPersonal = passwordPersonal;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
