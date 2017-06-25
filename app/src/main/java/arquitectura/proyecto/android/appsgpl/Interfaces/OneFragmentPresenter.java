package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface OneFragmentPresenter {
    void initRecycler(List<Entregable> entregableList);
    void loadListDocumento();
    void showEmpty();
    void hideEmpty();
    void hideProgress();
    void showProgress();
}
