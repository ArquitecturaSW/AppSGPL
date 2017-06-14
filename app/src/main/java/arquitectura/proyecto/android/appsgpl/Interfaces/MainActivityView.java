package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface MainActivityView {
    void initRecycler(List<Proyecto> proyectoList);
    void showEmpty();
    void showProgress();
    void hideProgress();
}
