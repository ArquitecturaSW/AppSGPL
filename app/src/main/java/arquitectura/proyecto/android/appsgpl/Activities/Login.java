package arquitectura.proyecto.android.appsgpl.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseEmpresa;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Registros.CrearCuenta;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Login extends AppCompatActivity implements  Validator.ValidationListener{
    /*variables globales*/
    public static String nombreEmpresa;
    public static String usuarioEmpresa;
    public static String correoEmpresa;
    public static String rucEmpresa;
    public static String id;
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
        Usuario usuario = new Usuario(u, p);
        Call<ResponseLogin> usuarioCall = service.iniciosesion(usuario);

        usuarioCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin responseLogin = response.body();
                if (responseLogin.getEstado() == 1) {
                    id=Integer.toString(responseLogin.getEmpresa().getIdEmpresa());
                    prefManager.saveIdEmpresa(Integer.toString(responseLogin.getEmpresa().getIdEmpresa()));
                    /*nombreEmpresa=responseLogin.getEmpresa().getNombreEmpresa();
                    usuarioEmpresa=responseLogin.getEmpresa().getUsuario();
                    correoEmpresa=responseLogin.getEmpresa().getCorreoEmpresa();
                    rucEmpresa=responseLogin.getEmpresa().getRucEmpresa();*/
                    progress.dismiss();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if(responseLogin.getEstado() == 2) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Usuario y/o Contraseña son incorrectos", Toast.LENGTH_SHORT).show();

                    }else{
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Ingrese datos correctos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),"Tenemos problemas con el servidor\n Intentelo mas tarde", Toast.LENGTH_SHORT).show();
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
