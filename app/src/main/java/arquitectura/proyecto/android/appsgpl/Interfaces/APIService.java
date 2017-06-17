package arquitectura.proyecto.android.appsgpl.Interfaces;

import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseEmpresa;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseRegistrarProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public interface APIService {

    @POST("login.php")
    Call<ResponseLogin> iniciosesion(@Body Usuario usuario);

    @POST("registrarEmpresa.php")
    Call<PostResponse> registerEmpresa(@Body Usuario usuario);

    @GET("mostrarProyecto.php")
    Call<ResponseProyecto> getProyectos(@Query("id_empresa") String id);

    @GET("InfoEmpresa.php")
    Call<ResponseEmpresa> getEmpresa(@Query("id_empresa") String id);

    @POST("registrarProyecto.php")
    Call<ResponseRegistrarProyecto> registerProyecto(@Body Proyecto proyecto);

}
