package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;


import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseMostrarEntregable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class OneFragmentInteractorImpl implements OneFragmentInteractor {
    private OneFragmentPresenter presenter;
    public OneFragmentInteractorImpl ( OneFragmentPresenter presenter){
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
        Call<ResponseMostrarEntregable> callEntregable = service.getEntregables(DetalleProyecto.idProyecto);
        callEntregable.enqueue(new Callback<ResponseMostrarEntregable>() {
            @Override
            public void onResponse(Call<ResponseMostrarEntregable> call, Response<ResponseMostrarEntregable> response) {
                ResponseMostrarEntregable responseMostrar = response.body();
                if(responseMostrar.getEstado()==1){
                    List<Entregable> entregableList = new ArrayList<>();
                    entregableList = responseMostrar.getEntregableList();
                    presenter.hideProgress();
                    presenter.hideEmpty();
                    presenter.initRecycler(entregableList);

                }else{
                    presenter.hideProgress();
                    presenter.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponseMostrarEntregable> call, Throwable t) {
                presenter.hideProgress();
                presenter.showEmpty();
            }
        });
    }
}
