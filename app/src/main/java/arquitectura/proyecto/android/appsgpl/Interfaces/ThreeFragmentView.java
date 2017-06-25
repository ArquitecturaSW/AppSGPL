package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Historial;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public interface ThreeFragmentView {
   void initRecycler(List<Historial> historialList);
   void showEmpty();
   void hideEmpty();
   void showProgress();
   void hideProgress();
}
