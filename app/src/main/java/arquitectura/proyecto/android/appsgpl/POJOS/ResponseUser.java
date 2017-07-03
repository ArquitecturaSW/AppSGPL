package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 02-Jul-17.
 */

public class ResponseUser {
    private Integer estado;
    private User datoJson;
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

    public User getDatoJson() {
        return datoJson;
    }

    public void setDatoJson(User datoJson) {
        this.datoJson = datoJson;
    }
}
