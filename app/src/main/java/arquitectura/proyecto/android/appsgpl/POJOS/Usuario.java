package arquitectura.proyecto.android.appsgpl.POJOS;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public class Usuario {

    private String nombre;
    private int tipo_empresa;
    private String correo;
    private String ruc;
    private String direccion;
    private String usuario;
    private String password;

    public Usuario(String nombre,int tipo_empresa,String correo,String ruc,String direccion,String usuario,String password){
        this.nombre=nombre;
        this.tipo_empresa=tipo_empresa;
        this.correo=correo;
        this.ruc=ruc;
        this.direccion=direccion;
        this.usuario=usuario;
        this.password=password;
    }

    public Usuario(String nombre,String usuario,String password){
        this.nombre=nombre;
        this.usuario=usuario;
        this.password=password;
    }
    public Usuario(String usuario,String password){
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

    public int getTipo_empresa() {
        return tipo_empresa;
    }

    public void setTipo_empresa(int tipo_empresa) {
        this.tipo_empresa = tipo_empresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
