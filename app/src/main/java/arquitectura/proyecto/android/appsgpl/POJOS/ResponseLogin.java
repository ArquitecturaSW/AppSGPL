package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 13-Jun-17.
 */

public class ResponseLogin {
    private Integer estado;
    private Empresa empresa;
    private String mensaje;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }



    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
