package arquitectura.proyecto.android.appsgpl.Activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Usuario;
import arquitectura.proyecto.android.appsgpl.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearCuenta extends AppCompatActivity {

    TextInputEditText nombre_empresa;
    TextInputEditText usuario_empresa;
    TextInputEditText password;
    Button registrar_usuario;
    APIService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        nombre_empresa= (TextInputEditText) findViewById(R.id.nombreempresa);
        usuario_empresa= (TextInputEditText) findViewById(R.id.usuario_empresa);
        password= (TextInputEditText) findViewById(R.id.password);
        registrar_usuario= (Button) findViewById(R.id.crearcuenta);

        nombre_empresa.setText("prueba1");
        usuario_empresa.setText("prueba1");
        password.setText("prueba1");

        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sw14200042.esy.es/sesiones/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre_empresa.getText().toString();
                String user = usuario_empresa.getText().toString();
                String contra = password.getText().toString();

                Usuario usuario = new Usuario(name, user, contra);

                Call<Usuario> usuarioCall = service.insertarEmpresa(usuario);
                usuarioCall.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Toast.makeText(getApplicationContext(),"Codigo "+response.message(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
       
    }

}






