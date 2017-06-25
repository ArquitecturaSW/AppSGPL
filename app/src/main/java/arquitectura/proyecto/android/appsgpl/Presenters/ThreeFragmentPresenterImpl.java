package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interactors.ThreeFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public class ThreeFragmentPresenterImpl implements ThreeFragmentPresenter {

    private ThreeFragmentInteractor interactor;
    private ThreeFragmentView view;

    public ThreeFragmentPresenterImpl(ThreeFragmentView view){
        this.view=view;
        interactor = new ThreeFragmentInteractorImpl(this);
    }


    @Override
    public void initRecycler(List<Historial> historialList) {
        view.initRecycler(historialList);
    }

    @Override
    public void loadListActividad() {
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
