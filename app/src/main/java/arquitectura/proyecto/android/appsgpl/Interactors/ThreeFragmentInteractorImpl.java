package arquitectura.proyecto.android.appsgpl.Interactors;

import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public class ThreeFragmentInteractorImpl implements ThreeFragmentInteractor{

    private ThreeFragmentPresenter presenter;
    public ThreeFragmentInteractorImpl(ThreeFragmentPresenter presenter){
        this.presenter=presenter;
    }
    @Override
    public void obtenerValores(Proyecto proyecto) {
        presenter.sendView(proyecto.getNombreProyecto(),proyecto.getCodigoProyecto()
        ,proyecto.getJefeProyecto(),proyecto.getMontoProyecto(),proyecto.getDateStart(),
                proyecto.getDateEnd(),proyecto.getStatusProyecto(),proyecto.getDescripcionProyecto()
        );
    }
}
