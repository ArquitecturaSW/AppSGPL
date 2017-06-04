package arquitectura.proyecto.android.appsgpl.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.PruebaLogin;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static arquitectura.proyecto.android.appsgpl.R.id.login;

public class Login extends AppCompatActivity implements  Validator.ValidationListener{
    Button iniciarSesion;
    Button crearCuenta;
    @NotEmpty(message = "Debe llenar este campo.")
    @Length(min=5,max=15)
    TextInputEditText usuario;
    @NotEmpty
    @Password(min = 4, scheme = Password.Scheme.ALPHA)
    TextInputEditText password;
    APIService service;
    private Validator validator;
    String u;
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validator = new Validator(this);
        validator.setValidationListener(this);
        iniciarSesion = (Button) findViewById(R.id.iniciarsesion);
        crearCuenta = (Button) findViewById(R.id.crearcuenta);
        usuario = (TextInputEditText) findViewById(R.id.user);
        password = (TextInputEditText) findViewById(R.id.password);

        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sw14200042.esy.es/sesiones/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();

               /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();*/
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
        Usuario usuario = new Usuario(u, p);

        Call<PruebaLogin> usuarioCall = service.iniciosesion(usuario);

        usuarioCall.enqueue(new Callback<PruebaLogin>() {
            @Override
            public void onResponse(Call<PruebaLogin> call, Response<PruebaLogin> response) {
                PruebaLogin pruebaLogin = response.body();

                if (pruebaLogin.getEstado() == 1) {
                    Toast.makeText(getApplicationContext(), pruebaLogin.getMensaje().toString() + " 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), pruebaLogin.getMensaje().toString() + " 2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PruebaLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
