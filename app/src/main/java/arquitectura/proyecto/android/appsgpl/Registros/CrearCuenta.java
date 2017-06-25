package arquitectura.proyecto.android.appsgpl.Registros;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Cuenta;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearCuenta extends AppCompatActivity implements  Validator.ValidationListener {

    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,max=15,message = "Mínimo 4 caracteres")
    TextInputEditText nombre_empresa;

    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,max=15,message = "Longitud mínima 4.")
    TextInputEditText usuario_empresa;

    @NotEmpty(message = "No deje vacío este campo.")
    @Password(min = 4, scheme = Password.Scheme.ALPHA,message = "Longitud mínima 4.")
    TextInputEditText password;

    @Email(message = "example@example.com")
    TextInputEditText correo;

    @NotEmpty(message = "No deje vacío este campo.")
    @Length(max=11,message = "Longitud mínima 11")
    TextInputEditText ruc;

    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText direccion;

    Spinner spinner1;
    Button registrar_usuario;
    APIService service;
    ProgressDialog progress;
    boolean st;
    ArrayAdapter<String> dataAdapter;

    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        nombre_empresa= (TextInputEditText) findViewById(R.id.nombreempresa);
        correo= (TextInputEditText) findViewById(R.id.email);
        direccion= (TextInputEditText) findViewById(R.id.dir_empresa);
        ruc= (TextInputEditText) findViewById(R.id.ruc_empresa);
        usuario_empresa= (TextInputEditText) findViewById(R.id.usuario_empresa);
        password= (TextInputEditText) findViewById(R.id.password);
        registrar_usuario= (Button) findViewById(R.id.crearcuentaCC);
        /* Llenado del spinner*/
        List<String> list = new ArrayList<String>();
        list.add("Seleccione el tipo de empresa:");
        list.add("E.I.R.L");
        list.add("S.A");
        list.add("S.A.A");
        list.add("S.A.C");
        list.add("S.R.L");

        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        /*nombre_empresa.setText("prueba1");
        usuario_empresa.setText("prueba1");
        password.setText("prueba1");*/
        validator = new Validator(this);
        validator.setValidationListener(this);

        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

    }
    @Override
    public void onValidationSucceeded() {
        String tipo = spinner1.getSelectedItem().toString();
        int id = 0;
        boolean t= false;
        if(tipo=="Seleccione el tipo de empresa:"){t=false;
        }else{
        if(tipo=="E.I.R.L"){id=1;t=true;}else{if(tipo=="S.A"){id=2;t=true;}else {
            if(tipo=="S.A.A"){id=3;t=true;}else {if(tipo=="S.A.C"){id=4;t=true;}else{id=5;t=true;}}
        }}}
        //Toast.makeText(this,String.valueOf(id),Toast.LENGTH_SHORT).show();
        if (t==true) {
            registrarEmpresa(id,nombre_empresa.getText().toString(),correo.getText().toString(),
                    direccion.getText().toString(),ruc.getText().toString(),
                    usuario_empresa.getText().toString(),password.getText().toString());
        }else{
            Toast.makeText(this,"Seleccione el tipo de empresa.",Toast.LENGTH_SHORT).show();
        }



    }

    private void registrarEmpresa(int id, String s, String s1, String s2, String s3, String s4, String s5) {
        progress = new ProgressDialog(CrearCuenta.this);
        progress.setTitle("Registrando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        Cuenta cuenta = new Cuenta(id,s,s1,s2,s3,s4,s5);
        Call<PostResponse> postResponse = service.registerEmpresa(cuenta);
        postResponse.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();
                if(result.getEstado()==1){
                    Toast.makeText(getApplicationContext(),"Se registro exitosamente",Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    finish();
                }else{
                    if(result.getEstado()==3){
                    Toast.makeText(getApplicationContext(),"El nombre de cuenta ya existe.",Toast.LENGTH_SHORT).show();
                    usuario_empresa.setError("Cuenta existente");progress.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(),"No se pudo registrar.",Toast.LENGTH_SHORT).show();
                    usuario_empresa.setError("Eliga otro cuenta.");
                        progress.dismiss();
                }}
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Problemas con el servidor.",Toast.LENGTH_SHORT).show();
                registrar_usuario.setEnabled(true);
            }
        });
        /*Call<Cuenta> usuarioCall = service.insertarEmpresa(cuenta);
        usuarioCall.enqueue(new Callback<Cuenta>() {
            @Override
            public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {

            }
            @Override
            public void onFailure(Call<Cuenta> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No se logro registrar.Intente nuevamente. "+t.getMessage(),Toast.LENGTH_SHORT).show();
                registrar_usuario.setEnabled(true);
            }
        });*/
    }

    private void limpiarEditext() {
        nombre_empresa.setText("");
        correo.setText("");
        direccion.setText("");
        ruc.setText("");
        password.setText("");
        usuario_empresa.setText("");
        spinner1.setAdapter(dataAdapter);
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






