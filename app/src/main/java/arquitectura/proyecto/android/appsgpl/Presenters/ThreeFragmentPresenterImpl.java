package arquitectura.proyecto.android.appsgpl.Presenters;

import arquitectura.proyecto.android.appsgpl.Interactors.ThreeFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interactors.TwoFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Views.ThreeFragment;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public class ThreeFragmentPresenterImpl implements ThreeFragmentPresenter {

    private ThreeFragmentInteractor interactor;
    private ThreeFragmentView view;
    Proyecto proyecto;


    public ThreeFragmentPresenterImpl(ThreeFragmentView view){
        this.view=view;
        interactor = new ThreeFragmentInteractorImpl(this);
    }

    @Override
    public void sendInteractor(Proyecto proyecto) {
        interactor.obtenerValores(proyecto);
    }

    @Override
    public void sendView(String nombreProyecto, String codigoProyecto, String jefeProyecto, int montoProyecto, String dateStart, String dateEnd, String statusProyecto, String descripcionProyecto) {
        view.showData(nombreProyecto,codigoProyecto,jefeProyecto,montoProyecto,dateStart,dateEnd,statusProyecto,descripcionProyecto);
    }
}
