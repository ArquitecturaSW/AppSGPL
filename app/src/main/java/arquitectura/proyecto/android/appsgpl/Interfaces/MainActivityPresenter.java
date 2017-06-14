package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface MainActivityPresenter {
    void initRecycler(List<Proyecto> proyectoList);
    void loadListProyecto();
    void showEmpty();
    void hideProgress();
    void showProgress();
}
