package arquitectura.proyecto.android.appsgpl.Interfaces;

import arquitectura.proyecto.android.appsgpl.POJOS.EntregableP;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.Jefe;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.RegisterEquipo;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseReportes;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseEmpresa;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseEntregable;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseHistorial;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseMostrarEntregable;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseMostrarEquipo;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponsePersonal;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponsePersonalFree;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseRegistrarProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.Cuenta;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by Jair Barzola on 01-May-17.
 */

public interface APIService {

    @POST("login.php")
    Call<ResponseLogin> iniciosesion(@Body Cuenta cuenta);

    @POST("registrarEmpresa.php")
    Call<PostResponse> registerEmpresa(@Body Cuenta cuenta);

    @GET("mostrarProyecto.php")
    Call<ResponseProyecto> getProyectos(@Query("id_empresa") String id);

    @GET("InfoEmpresa.php")
    Call<ResponseEmpresa> getEmpresa(@Query("id_empresa") String id);

    @POST("registrarProyecto.php")
    Call<ResponseRegistrarProyecto> registerProyecto(@Body Proyecto proyecto);

    @POST("registrarDocumento.php")
    Call<ResponseEntregable> registerEntregable(@Body EntregableP entregableP);

    @GET("mostrarDoc.php")
    Call<ResponseMostrarEntregable> getEntregables(@Query("id_proyecto") int id);

    @POST("registrarPersonal.php")
    Call<PostResponse> registerPersonal(@Body Personal personal);

    @GET("mostrarPersonal.php")
    Call<ResponsePersonal> getPersonal(@Query("id_empresa") String id);

    @POST("registrarHistorial.php")
    Call<PostResponse> registerHistorial(@Body Historial historial);

    @GET("mostrarHistorial.php")
    Call<ResponseHistorial> getHistorial(@Query("id_proyecto") int id);

    @GET("mostrarPerLibres.php")
    Call<ResponsePersonalFree> getPersonalFree(@Query("id_empresa") String id);

    @POST("registrarEquipoP.php")
    Call<PostResponse> registerEquipo(@Body RegisterEquipo equipo);

    @GET("mostrarEquipoP.php")
    Call<ResponseMostrarEquipo> getEquipo(@Query("id_proyecto") int id);

    @GET("{entregable}")
    @Streaming
    Call<ResponseBody> downloadFile(@Path("entregable")String entregable);

    @POST("asignarJefe.php")
    Call<PostResponse> asignarJefe(@Body Jefe jefe);

    @POST("proyectoEndFake.php")
    Call<PostResponse> finishProyecto(@Body Proyecto proyecto);

    @POST("ModidTipo.php")
    Call<PostResponse> updateTipo(@Body Jefe jefe);

    @GET("reporteJson.php")
    Call<ResponseReportes> getReportes(@Query("idEmpresa") String id);

    @POST("LoginEmJefe.php")
    Call<ResponseUser> signIn(@Body Cuenta cuenta);

    @GET("ProyectoMostrarID.php")
    Call<ResponseProyecto> getProject(@Query("idProyecto") String id);


}
