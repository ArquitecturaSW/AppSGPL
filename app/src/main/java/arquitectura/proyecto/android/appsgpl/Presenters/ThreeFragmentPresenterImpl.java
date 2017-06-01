package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interactors.ThreeFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interactors.TwoFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Actividad;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Views.ThreeFragment;

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
    public void initRecycler(List<Actividad> actividadList) {
        view.initRecycler(actividadList);
    }

    @Override
    public void loadListActividad() {
        interactor.initRecycler();

    }
}
