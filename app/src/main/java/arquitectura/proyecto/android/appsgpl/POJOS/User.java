package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 18-Jun-17.
 */

public class User {
    private int idUser;
    private int idEmpresa;
    private String nombreUser;
    private int idProyecto;
    private String identificadorUSer;
    private String correoUser;
    private String usuario;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdentificadorUSer() {
        return identificadorUSer;
    }

    public void setIdentificadorUSer(String identificadorUSer) {
        this.identificadorUSer = identificadorUSer;
    }

    public String getCorreoUser() {
        return correoUser;
    }

    public void setCorreoUser(String correoUser) {
        this.correoUser = correoUser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}