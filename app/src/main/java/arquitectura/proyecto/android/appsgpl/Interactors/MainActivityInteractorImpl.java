package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class MainActivityInteractorImpl implements MainActivityInteractor {

   private MainActivityPresenter presenter;
    public MainActivityInteractorImpl ( MainActivityPresenter presenter){
        this.presenter= presenter;
    }
    @Override
    public void initRecycler() {
        List<Proyecto> proyectoList = new ArrayList<>();

        proyectoList.add(new Proyecto("01","Sistema de Agendamiento de citas medicas","En curso"));
        proyectoList.add(new Proyecto("02","Sistema de Gestio de Proyectos","En curso"));
        proyectoList.add(new Proyecto("03","Sistema de Localizacion Geografica","Perdido"));
        proyectoList.add(new Proyecto("04","Sistema de Biblioteca","Finalizado"));
        proyectoList.add(new Proyecto("05","Sistema de grifos","Perdido"));
        proyectoList.add(new Proyecto("06","My Gym App","En curso"));
        proyectoList.add(new Proyecto("07","Sistema de Egresados","Finalizado"));
        proyectoList.add(new Proyecto("08","Sistema de Venta","Perdido"));
        presenter.initRecycler(proyectoList);

    }
}
