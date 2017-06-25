package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;


import arquitectura.proyecto.android.appsgpl.Interactors.OneFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class OneFragmentPresenterImpl implements OneFragmentPresenter {

    private OneFragmentInteractor interactor;
    private OneFragmentView view;
    public OneFragmentPresenterImpl(OneFragmentView view){
        this.view=view;
        interactor = new OneFragmentInteractorImpl(this);
    }
    @Override
    public void initRecycler(List<Entregable> entregableList) {
        view.initRecycler(entregableList);
    }

    @Override
    public void loadListDocumento() {
        interactor.initRecycler();
    }

    @Override
    public void showEmpty() {
        view.showEmpty();
    }

    @Override
    public void hideEmpty() {
        view.hideEmpty();
    }

    @Override
    public void hideProgress() {
        view.hideProgress();
    }

    @Override
    public void showProgress() {
        view.showProgress();
    }
}
