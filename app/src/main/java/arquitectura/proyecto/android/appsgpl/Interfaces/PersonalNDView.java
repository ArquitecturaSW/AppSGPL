package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Brian on 01/06/2017.
 */

public interface PersonalNDView {
    void initRecycler(List<Personal> personalList);
    void showEmpty();
    void hideEmpty();
    void showProgress();
    void hideProgress();
}
