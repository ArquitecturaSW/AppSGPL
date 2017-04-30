package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Proyecto {
    private int idProyecto;
    private String nombreProyecto;
    private String codigoProyecto;
    private String descripcionProyecto;
    private String statusProyecto;

    public Proyecto (String codigoProyecto,String nombreProyecto,String statusProyecto){
        this.codigoProyecto=codigoProyecto;
        this.nombreProyecto=nombreProyecto;
        this.statusProyecto=statusProyecto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getStatusProyecto() {
        return statusProyecto;
    }

    public void setStatusProyecto(String statusProyecto) {
        this.statusProyecto = statusProyecto;
    }
}
