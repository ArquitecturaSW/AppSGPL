package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface TwoFragmentView {
    void initRecycler(List<Equipo> equipoList);
    void showEmpty();
    void hideEmpty();
    void showProgress();
    void hideProgress();
}
