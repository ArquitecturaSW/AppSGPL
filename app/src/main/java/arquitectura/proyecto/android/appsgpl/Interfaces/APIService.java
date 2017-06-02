package arquitectura.proyecto.android.appsgpl.Interfaces;

import arquitectura.proyecto.android.appsgpl.POJOS.PruebaLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public interface APIService {

    @POST("insertar_usuarioJson.php")
    Call<Usuario> insertarEmpresa(@Body Usuario usuario);

    @POST("loginJson.php")
    Call<PruebaLogin> iniciosesion(@Body Usuario usuario);
}
