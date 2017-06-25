package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interactors.TwoFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class TwoFragmentPresenterImpl implements TwoFragmentPresenter {

    private TwoFragmentInteractor interactor;
    private TwoFragmentView view;
    public TwoFragmentPresenterImpl(TwoFragmentView view){
        this.view=view;
        interactor = new TwoFragmentInteractorImpl(this);
    }
    @Override
    public void initRecycler(List<Equipo> equipoList) {
        view.initRecycler(equipoList);
    }

    @Override
    public void loadListPersonal() {
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
