package arquitectura.proyecto.android.appsgpl.POJOS;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public class Usuario {

    private String nombre;
    private String usuario;
    private String password;

    public Usuario(String nombre,String usuario,String password){
        this.nombre=nombre;
        this.usuario=usuario;
        this.password=password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
