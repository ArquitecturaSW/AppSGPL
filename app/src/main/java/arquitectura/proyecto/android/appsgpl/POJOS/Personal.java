package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Personal {

    private String codigo;
    private String nombre;
    private String cargo;


    public Personal (String codigo,String nombre,String cargo){

        this.codigo=codigo;
        this.nombre=nombre;
        this.cargo=cargo;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


}
