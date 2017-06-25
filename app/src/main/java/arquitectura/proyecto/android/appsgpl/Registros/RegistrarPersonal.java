package arquitectura.proyecto.android.appsgpl.Registros;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponsePersonal;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;

public class RegistrarPersonal extends AppCompatActivity implements Validator.ValidationListener{
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText nombreP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText apellidoP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText dniP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText edadP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText correoP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText direccionP;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText ocupacionP;
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min = 9,max = 9,message = "9 dígitos")
    TextInputEditText movilP;
    Button registrar ;
    ProgressDialog progress;
    APIService service;
    Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_personal);
        validator = new Validator(this);
        validator.setValidationListener(this);
        nombreP = (TextInputEditText) findViewById(R.id.nombrepersonal);
        apellidoP= (TextInputEditText) findViewById(R.id.apellidopersonal);
        dniP= (TextInputEditText)findViewById(R.id.dnipersonal);
        edadP = (TextInputEditText)findViewById(R.id.edadpersonal);
        correoP = (TextInputEditText) findViewById(R.id.correpersonal);
        movilP= (TextInputEditText) findViewById(R.id.movilpersonal);
        direccionP=(TextInputEditText)findViewById(R.id.direccionpersonal);
        ocupacionP=(TextInputEditText)findViewById(R.id.ocupacionpersonal);
        registrar= (Button)findViewById(R.id.registrarpersonal);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

    }


    @Override
    public void onValidationSucceeded() {
        progress = new ProgressDialog(this);
        progress.setTitle("Registrando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);

        Personal personal = new Personal(Integer.parseInt(MainActivity.idEmpresaMain),nombreP.getText().toString()
                                ,apellidoP.getText().toString(),dniP.getText().toString(),Integer.parseInt(edadP.getText().toString())
                                ,correoP.getText().toString(),Integer.parseInt(movilP.getText().toString()),
                                    direccionP.getText().toString(),ocupacionP.getText().toString());
        Call<PostResponse> responseCall = service.registerPersonal(personal);
        responseCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();
                if(result.getEstado()==1){
                    progress.dismiss();
                    setResult(Activity.RESULT_OK);
                    finish();
                }else{
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"No se pudo registrar",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });

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
