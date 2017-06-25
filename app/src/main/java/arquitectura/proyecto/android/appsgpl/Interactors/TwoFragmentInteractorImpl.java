package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseHistorial;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseMostrarEquipo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class TwoFragmentInteractorImpl implements TwoFragmentInteractor {

    private TwoFragmentPresenter presenter;
    public TwoFragmentInteractorImpl ( TwoFragmentPresenter presenter){
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
        Call<ResponseMostrarEquipo> equipoCall = service.getEquipo(DetalleProyecto.idProyecto);
        equipoCall.enqueue(new Callback<ResponseMostrarEquipo>() {
            @Override
            public void onResponse(Call<ResponseMostrarEquipo> call, Response<ResponseMostrarEquipo> response) {
                ResponseMostrarEquipo mostrarEquipo = response.body();
                if(mostrarEquipo.getEstado()==1){
                    List<Equipo> equipoList = new ArrayList<>();
                    equipoList = mostrarEquipo.getEquipoList();
                    presenter.hideProgress();
                    presenter.hideEmpty();
                    presenter.initRecycler(equipoList);

                }else{
                    presenter.hideProgress();
                    presenter.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponseMostrarEquipo> call, Throwable t) {
                presenter.hideProgress();
                presenter.showEmpty();
            }
        });

       /* List<Personal> personalList = new ArrayList<>();
        personalList.add(new Personal("01","Jair Barzola Cuba","Jefe Proyecto"));
        personalList.add(new Personal("02","Richard Inga Aliaga","Operario"));
        personalList.add(new Personal("03","Kevin Chagua Callupe","Operario"));
        personalList.add(new Personal("04","Jhunior Cañari Corpus","Operario"));
        presenter.initRecycler(personalList);*/

    }
}
