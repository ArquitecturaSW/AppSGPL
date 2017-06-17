package arquitectura.proyecto.android.appsgpl.Interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.Login;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseProyecto;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;

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
        presenter.showProgress();
        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseProyecto> responseProyectoCall = service.getProyectos(MainActivity.idEmpresaMain);
        responseProyectoCall.enqueue(new Callback<ResponseProyecto>() {
            @Override
            public void onResponse(Call<ResponseProyecto> call, Response<ResponseProyecto> response) {
                ResponseProyecto result = response.body();

                if(result.getEstado()==1){
                    List<Proyecto> proyectoList = new ArrayList<>();
                    proyectoList = result.getProyectoList();
                    presenter.hideProgress();
                    presenter.initRecycler(proyectoList);
                }else{
                    presenter.hideProgress();
                    presenter.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponseProyecto> call, Throwable t) {
                presenter.hideProgress();
                presenter.showEmpty();
            }
        });


       /* List<Proyecto> proyectoList = new ArrayList<>();
//String jefeProyecto,int montoProyecto,String dateStart,String dateEnd
        proyectoList.add(new Proyecto(1,"Sistema de Agendamiento de citas medicas","A001","Jair Barzola",15000,"10/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(2,"Sistema de Gestio de Proyectos","A001","Jhunior Cañari",18000,"10/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(3,"Sistema de Localizacion Geografica","B001","Richard Inga",20000,"10/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(4,"Sistema de Biblioteca","C001","Chagua Callupe",16200,"10/05/17","25/06/17","Finalizado","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(5,"Sistema de grifos","F001","Jose Perez",10000,"05/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(6,"My Gym App","T001","David Barzola",15230,"09/05/17","25/06/17","En curso","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(7,"Sistema de Egresados","Y001","Felix Sanchez",56000,"10/05/17","25/06/17","Finalizado","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        proyectoList.add(new Proyecto(8,"Sistema de Venta","P001","Noelia Zorrilla",89000,"10/05/17","25/06/17","Perdido","Este proyecto lo realiza los estudiantes del curso Arquitectura de Software de la base 14."));
        presenter.initRecycler(proyectoList);*/

    }
}
