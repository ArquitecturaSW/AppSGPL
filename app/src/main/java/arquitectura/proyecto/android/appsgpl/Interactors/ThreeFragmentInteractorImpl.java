package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseHistorial;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        presenter.showProgress();
        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseHistorial> historialCall = service.getHistorial(DetalleProyecto.idProyecto);
        historialCall.enqueue(new Callback<ResponseHistorial>() {
            @Override
            public void onResponse(Call<ResponseHistorial> call, Response<ResponseHistorial> response) {
                ResponseHistorial responseHistorial = response.body();
                if(responseHistorial.getEstado()==1){
                    List<Historial> historialList = new ArrayList<>();
                    historialList = responseHistorial.getHistorialList();
                    presenter.hideProgress();
                    presenter.hideEmpty();
                    presenter.initRecycler(historialList);

                }else{
                    presenter.hideProgress();
                    presenter.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistorial> call, Throwable t) {
                presenter.hideProgress();
                presenter.showEmpty();
            }
        });

    }
}
