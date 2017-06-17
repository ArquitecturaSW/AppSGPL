package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 16-Jun-17.
 */

public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String rucEmpresa;
    private String correoEmpresa;
    private String usuario;

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}

