package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 23-Jun-17.
 */

public class Historial {
    private int idHistorial;
    private int idProyecto;
    private String descripcion;
    private String fecha;

    public Historial(int idProyecto,String descripcion){
        this.idProyecto=idProyecto;
        this.descripcion=descripcion;
    }
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDate() {
        return fecha;
    }

    public void setDate(String fecha) {
        this.fecha = fecha;
    }
}

