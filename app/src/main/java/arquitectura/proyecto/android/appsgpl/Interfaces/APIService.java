package arquitectura.proyecto.android.appsgpl.Interfaces;

import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.PruebaLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public interface APIService {

    @POST("login.php")
    Call<PruebaLogin> iniciosesion(@Body Usuario usuario);

    @POST("registrarEmpresa.php")
    Call<PostResponse> registerEmpresa(@Body Usuario usuario);
}
