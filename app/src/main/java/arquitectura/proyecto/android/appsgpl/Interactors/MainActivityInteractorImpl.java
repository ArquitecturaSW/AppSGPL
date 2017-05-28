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
//String jefeProyecto,int montoProyecto,String dateStart,String dateEnd
        proyectoList.add(new Proyecto(1,"Sistema de Agendamiento de citas medicas","A001","Jair Barzola",15000,"10/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(2,"Sistema de Gestio de Proyectos","A001","Jhunior Cañari",18000,"10/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(3,"Sistema de Localizacion Geografica","B001","Richard Inga",20000,"10/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(4,"Sistema de Biblioteca","C001","Chagua Callupe",16200,"10/05/17","25/06/17","Finalizado","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(5,"Sistema de grifos","F001","Jose Perez",10000,"05/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(6,"My Gym App","T001","David Barzola",15230,"09/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(7,"Sistema de Egresados","Y001","Felix Sanchez",56000,"10/05/17","25/06/17","Finalizado","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(8,"Sistema de Venta","P001","Noelia Zorrilla",89000,"10/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        presenter.initRecycler(proyectoList);

    }
}
