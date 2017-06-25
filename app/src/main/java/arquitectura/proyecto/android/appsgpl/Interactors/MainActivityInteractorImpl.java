package arquitectura.proyecto.android.appsgpl.Interactors;



import java.util.ArrayList;
import java.util.List;

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
                    presenter.hideEmpty();
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

    }
}
