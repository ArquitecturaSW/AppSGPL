package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;


import arquitectura.proyecto.android.appsgpl.Interactors.PersonalNDInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDView;

import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Brian on 01/06/2017.
 */

public class PersonalNDPresenterImpl implements PersonalNDPresenter {
    private PersonalNDInteractor interactor;
    private PersonalNDView view;
    public PersonalNDPresenterImpl(PersonalNDView view){
        this.view=view;
        interactor = new PersonalNDInteractorImpl(this);
    }
    @Override
    public void initRecycler(List<Personal> personalList1) {
        view.initRecycler(personalList1);
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
