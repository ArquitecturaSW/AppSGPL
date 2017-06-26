package arquitectura.proyecto.android.appsgpl.POJOS;

import static arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto.idProyecto;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Personal {

    private int idPersonal;
    private int idEmpresa;
    private int idTipo;
    private String nombrePersonal;
    private String apellidoPersonal;
    private String dniPersonal;
    private int edadPersonal;
    private String correoPersonal;
    private int telefonoPersonal;
    private String direccionPersonal;
    private String ocupacionPersonal;
    private String usuarioPersonal;
    private String usuarioPassword;
    private int estadoPersonal;



    public Personal (int idEmpresa,String nombrePersonal,String apellidoPersonal,String dniPersonal,
                     int edadPersonal,String correoPersonal,int telefonoPersonal,String direccionPersonal,String ocupacionPersonal){
        this.idEmpresa=idEmpresa;
        this.nombrePersonal=nombrePersonal;
        this.apellidoPersonal=apellidoPersonal;
        this.direccionPersonal=direccionPersonal;
        this.telefonoPersonal=telefonoPersonal;
        this.dniPersonal=dniPersonal;
        this.edadPersonal=edadPersonal;
        this.correoPersonal=correoPersonal;
        this.ocupacionPersonal=ocupacionPersonal;
    }


    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getDniPersonal() {
        return dniPersonal;
    }

    public void setDniPersonal(String dniPersonal) {
        this.dniPersonal = dniPersonal;
    }

    public int getEdadPersonal() {
        return edadPersonal;
    }

    public void setEdadPersonal(int edadPersonal) {
        this.edadPersonal = edadPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getDireccionPersonal() {
        return direccionPersonal;
    }

    public void setDireccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
    }

    public String getOcupacionPersonal() {
        return ocupacionPersonal;
    }

    public void setOcupacionPersonal(String ocupacionPersonal) {
        this.ocupacionPersonal = ocupacionPersonal;
    }

    public String getUsuarioPersonal() {
        return usuarioPersonal;
    }

    public void setUsuarioPersonal(String usuarioPersonal) {
        this.usuarioPersonal = usuarioPersonal;
    }



    public int getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(int telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public int getEstadoPersonal() {
        return estadoPersonal;
    }

    public void setEstadoPersonal(int estadoPersonal) {
        this.estadoPersonal = estadoPersonal;
    }

    public String getUsuarioPassword() {
        return usuarioPassword;
    }

    public void setUsuarioPassword(String usuarioPassword) {
        this.usuarioPassword = usuarioPassword;
    }
}
