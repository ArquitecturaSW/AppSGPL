package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 18-Jun-17.
 */

public class User {
    private int idUser;
    private String nombreUser;
    private String identificadorUser;
    private String correoUser;
    private String usuario;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getIdentificadorUser() {
        return identificadorUser;
    }

    public void setIdentificadorUser(String identificadorUser) {
        this.identificadorUser = identificadorUser;
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
