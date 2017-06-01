package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 31-May-17.
 */

public class Actividad {


    private int imageResource;
    private String descripcion;
    private String date;

    public Actividad(int imageResource,String descripcion,String date){
        this.imageResource=imageResource;
        this.descripcion=descripcion;
        this.date=date;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
