package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponsePersonal;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brian on 01/06/2017.
 */

public class PersonalNDInteractorImpl implements PersonalNDInteractor {
    private PersonalNDPresenter presenter;
    public PersonalNDInteractorImpl ( PersonalNDPresenter presenter){
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
        final Call<ResponsePersonal> personalCall = service.getPersonal(MainActivity.idEmpresaMain);

        personalCall.enqueue(new Callback<ResponsePersonal>() {
            @Override
            public void onResponse(Call<ResponsePersonal> call, Response<ResponsePersonal> response) {
                ResponsePersonal responsePersonal = response.body();
                if(responsePersonal.getEstado()==1){
                    presenter.hideProgress();
                    List<Personal> personalList = new ArrayList<Personal>();
                    personalList= responsePersonal.getPersonalList();
                    presenter.hideEmpty();
                    presenter.initRecycler(personalList);
                }else{
                    presenter.hideProgress();
                    presenter.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<ResponsePersonal> call, Throwable t) {
                presenter.hideProgress();
                presenter.showEmpty();

            }
        });
    }
}
