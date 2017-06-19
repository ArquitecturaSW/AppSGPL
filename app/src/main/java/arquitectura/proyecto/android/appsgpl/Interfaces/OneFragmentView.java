package arquitectura.proyecto.android.appsgpl.Interfaces;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public interface OneFragmentView {
    void initRecycler(List<Entregable> entregableList);
    void showEmpty();
    void showProgress();
    void hideProgress();
}
