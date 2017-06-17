package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 16-Jun-17.
 */

public class ResponseEmpresa {
    private int estado;
    private Empresa empresa;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
