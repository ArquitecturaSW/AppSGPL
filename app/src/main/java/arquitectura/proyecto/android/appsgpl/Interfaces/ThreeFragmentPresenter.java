package arquitectura.proyecto.android.appsgpl.Interfaces;

import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public interface ThreeFragmentPresenter {
    void sendInteractor(Proyecto proyecto);
    void sendView(String nombreProyecto,String codigoProyecto,String jefeProyecto,int montoProyecto,
                  String dateStart,String dateEnd,String statusProyecto,String descripcionProyecto);
}
