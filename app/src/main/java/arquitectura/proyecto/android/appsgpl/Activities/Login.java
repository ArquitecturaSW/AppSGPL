package arquitectura.proyecto.android.appsgpl.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Cuenta;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseUser;
import arquitectura.proyecto.android.appsgpl.POJOS.User;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Registros.CrearCuenta;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;


public class Login extends AppCompatActivity implements  Validator.ValidationListener{
    /*variables globales*/
    /*variables globales*/
    Button iniciarSesion;
    Button crearCuenta;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText usuario;
    @NotEmpty(message = "No deje vacío este campo.")
    @Password(min = 4, scheme = Password.Scheme.ALPHA,message = "Longitud mínima 4.")
    TextInputEditText password;
    APIService service;
    private Validator validator;
    String u;
    String p;
    int s;
    String m;
    ProgressDialog progress;
    Proyecto proyecto;
    private PreferencesManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validator = new Validator(this);
        validator.setValidationListener(this);
        iniciarSesion = (Button) findViewById(R.id.iniciarsesion);
        crearCuenta = (Button) findViewById(R.id.crearcuentaLogin);
        usuario = (TextInputEditText) findViewById(R.id.user);
        password = (TextInputEditText) findViewById(R.id.password);
        prefManager = new PreferencesManager(this);

        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();

            }
        });
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CrearCuenta.class);
                startActivity(intent);
            }
        });

    }

    private void inicioSesion(String u, String p) {
        progress = new ProgressDialog(Login.this);
        progress.setTitle("Ingresando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        Cuenta cuenta = new Cuenta(u, p);
        Call<ResponseUser> usuarioCall = service.signIn(cuenta);

        usuarioCall.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                ResponseUser userResponse = response.body();

                Log.i("INF",userResponse.getEstado().toString());
               if (userResponse.getEstado()==1) {
                   if(userResponse.getDatoJson().getIdProyecto() == 0){
                    int id =userResponse.getDatoJson().getIdUser();
                    prefManager.saveIdEmpresa(Integer.toString(id));
                    prefManager.saveIdProyecto(Integer.toString(userResponse.getDatoJson().getIdProyecto()));
                    prefManager.saveDataUser(userResponse.getDatoJson().getIdUser(),userResponse.getDatoJson().getNombreUser(),
                            userResponse.getDatoJson().getUsuario(),
                            userResponse.getDatoJson().getCorreoUser(),
                            userResponse.getDatoJson().getIdentificadorUSer());
                    progress.dismiss();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    }else {
                        //if(userResponse.getDatoJson().get)
                       prefManager.saveIdEmpresa(Integer.toString(userResponse.getDatoJson().getIdEmpresa()));
                       prefManager.saveIdProyecto(Integer.toString(userResponse.getDatoJson().getIdProyecto()));
                       prefManager.saveIdProyecto2(Integer.toString(userResponse.getDatoJson().getIdProyecto()));
                       prefManager.saveDataUser(
                               userResponse.getDatoJson().getIdUser(),
                               userResponse.getDatoJson().getNombreUser(),
                               userResponse.getDatoJson().getUsuario(),
                               userResponse.getDatoJson().getCorreoUser(),
                               userResponse.getDatoJson().getIdentificadorUSer());
                       progress.dismiss();
                       //call.cancel();
                       consulta2(userResponse.getDatoJson().getIdProyecto());
                       /*Intent intent = new Intent(Login.this, DetalleProyecto.class);
                       intent.putExtra("OOO",3);
                       startActivity(intent);
                       finish();**/
                    }
                } else {
                    if(userResponse.getEstado()== 2) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Cuenta y/o Contraseña son incorrectos", Toast.LENGTH_SHORT).show();

                    }else{
                        if(userResponse.getEstado()==3) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), "Ingrese datos correctos", Toast.LENGTH_SHORT).show();
                        }else{
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), "No puede entrar al sistema comuniquese con la empresa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),"Tenemos problemas con el servidor\n Intentelo mas tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void consulta2(int idProyecto) {
        Call<ResponseProyecto> proyectoCall = service.getProject(String.valueOf(idProyecto));
        Log.i("ID","primero");
        proyectoCall.enqueue(new Callback<ResponseProyecto>() {
            @Override
            public void onResponse(Call<ResponseProyecto> call, Response<ResponseProyecto> response) {
                ResponseProyecto responseProyecto = response.body();
                if(responseProyecto.getEstado()==1){
                    List<Proyecto> proyectoList = new ArrayList<Proyecto>();
                    proyectoList=responseProyecto.getProyectoList();
                    proyecto = proyectoList.get(0);
                    Intent intent = new Intent(getApplicationContext(), DetalleProyecto.class);
                    intent.putExtra("proyecto", (Parcelable) proyecto);
                    intent.putExtra("OOO",0);
                    startActivity(intent);
                    Log.i("ID","2");
                    finish();
                }else{
                    proyecto= new Proyecto(0,4,"PRUEBA","A005","prueba de proyecto","10-05-16","10-05-17",152000);
                    Intent intent = new Intent(getApplicationContext(), DetalleProyecto.class);
                    intent.putExtra("proyecto", (Parcelable) proyecto);
                    intent.putExtra("OOO",0);
                    startActivity(intent);
                    Log.i("ID","3");
                    finish();
                }
            }
            @Override
            public void onFailure(Call<ResponseProyecto> call, Throwable t) {
                Log.i("ID","4");
                proyecto= new Proyecto(0,4,"PRUEBA","A005","prueba de proyecto","10-05-16","10-05-17",152000);
                Intent intent = new Intent(getApplicationContext(), DetalleProyecto.class);
                intent.putExtra("proyecto", (Parcelable) proyecto);
                intent.putExtra("OOO",0);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        inicioSesion(usuario.getText().toString(),password.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
