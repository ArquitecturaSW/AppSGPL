package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Actividad;


/**
 * Created by Jair Barzola on 23-May-17.
 */

public interface ThreeFragmentPresenter {
     void initRecycler(List<Actividad> actividadList);
     void loadListActividad();

}
