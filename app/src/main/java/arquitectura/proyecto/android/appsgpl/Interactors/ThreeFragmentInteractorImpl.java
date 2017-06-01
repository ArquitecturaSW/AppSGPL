package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Actividad;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.R;

/**
 * Created by Jair Barzola on 23-May-17.
 */

public class ThreeFragmentInteractorImpl implements ThreeFragmentInteractor{

    private ThreeFragmentPresenter presenter;
    public ThreeFragmentInteractorImpl(ThreeFragmentPresenter presenter){
        this.presenter=presenter;
    }

    @Override
    public void initRecycler() {
        List<Actividad> actividadList = new ArrayList<>();

        actividadList.add(new Actividad(R.drawable.creado,"El proyecto a sido creado.","31/05/17"));
        actividadList.add(new Actividad(R.drawable.jefe,"El proyecto ya tiene un jefe.","15/06/17"));
        actividadList.add(new Actividad(R.drawable.documento,"Documento nuevo.","18/07/17"));
        actividadList.add(new Actividad(R.drawable.documento,"Documento nuevo.","18/07/17"));
        actividadList.add(new Actividad(R.drawable.documento,"Documento nuevo.","18/07/17"));
        actividadList.add(new Actividad(R.drawable.documento,"Documento nuevo.","18/07/17"));
        actividadList.add(new Actividad(R.drawable.findefault,"El proyecto ha finalizado.","30/07/17"));

        presenter.initRecycler(actividadList);
    }
}
