package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface TwoFragmentPresenter {
    void initRecycler(List<Equipo> equipoList);
    void loadListPersonal();
    void showEmpty();
    void hideEmpty();
    void showProgress();
    void hideProgress();
}
