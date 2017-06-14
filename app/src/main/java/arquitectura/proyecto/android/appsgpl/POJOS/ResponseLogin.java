package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 13-Jun-17.
 */

public class ResponseLogin {
    private int estado;
    private int usuario;
    private String mensaje;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
