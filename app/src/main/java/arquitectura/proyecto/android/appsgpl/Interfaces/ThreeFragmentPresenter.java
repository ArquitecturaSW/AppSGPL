package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Historial;


/**
 * Created by Jair Barzola on 23-May-17.
 */

public interface ThreeFragmentPresenter {
     void initRecycler(List<Historial> historialList);
     void loadListActividad();
     void showEmpty();
     void hideEmpty();
     void showProgress();
     void hideProgress();

}
