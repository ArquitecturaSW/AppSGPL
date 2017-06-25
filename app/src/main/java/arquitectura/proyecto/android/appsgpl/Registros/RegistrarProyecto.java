package arquitectura.proyecto.android.appsgpl.Registros;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseRegistrarProyecto;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static arquitectura.proyecto.android.appsgpl.R.id.c;
import static arquitectura.proyecto.android.appsgpl.R.id.codigo_personal;
import static arquitectura.proyecto.android.appsgpl.R.id.descripcion_proyecto;

public class RegistrarProyecto extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText nombre;
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,max =5 ,message = "Mínimo 4 caracteres")
    TextInputEditText code;
    @NotEmpty(message = "Debe elegir un fecha i.")
    TextInputEditText dateStart;
    @NotEmpty(message = "Debe elegir un fecha f.")
    TextInputEditText dateEnd;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText descripcion;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText monto;
    Validator validator;
    Spinner spinnerRP;
    int diai,mesi,anoi;
    int diaf,mesf,anof;
    Calendar dateTime;
    ProgressDialog progress;
    APIService service;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_proyecto);
        spinnerRP=(Spinner) findViewById(R.id.spinnerproyecto);
        dateTime = Calendar.getInstance();
        validator = new Validator(this);
        validator.setValidationListener(this);
        Button registar = (Button) findViewById(R.id.registrar_proyecto);
        ImageView s1= (ImageView) findViewById(R.id.seleccionar_fecha_i);
        ImageView s2= (ImageView) findViewById(R.id.seleccionar_fecha_f);
        nombre = (TextInputEditText) findViewById(R.id.nombre_proyecto);
        code= (TextInputEditText) findViewById(R.id.code_proyecto);
        descripcion= (TextInputEditText) findViewById(R.id.descripcion_proyecto);
        dateEnd= (TextInputEditText) findViewById(R.id.fecha_fin);
        dateStart= (TextInputEditText) findViewById(R.id.fecha_inicio);
        monto=(TextInputEditText)findViewById(R.id.monto_proyecto);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateInicio();
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateFin();
            }
        });
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
                        /* Llenado del spinner*/
        List<String> list = new ArrayList<String>();
        list.add("Seleccione el tipo de proyecto:");
        list.add("BIENES");
        list.add("SERVICIOS");
        list.add("OBRAS");

        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRP.setAdapter(dataAdapter);
    }

    private void selectDateFin() {
        new DatePickerDialog(this, f, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void selectDateInicio() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate1(dayOfMonth,monthOfYear,year);
        }
    };
    DatePickerDialog.OnDateSetListener f = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate2(dayOfMonth,monthOfYear,year);
        }
    };

    private void updateDate2(int dayOfMonth, int monthOfYear, int year) {
        diaf=dayOfMonth;mesf=monthOfYear;anof=year;
        int mes=monthOfYear+1;
        dateEnd.setText(year+"-"+mes+"-"+dayOfMonth);
        dateEnd.setEnabled(false);
    }

    private void updateDate1(int dayOfMonth, int monthOfYear, int year) {
        diai=dayOfMonth;mesi=monthOfYear;anoi=year;
        int mes1=monthOfYear+1;
        dateStart.setText(year+"-"+mes1+"-"+dayOfMonth);
        dateStart.setEnabled(false);
    }

    @Override
    public void onValidationSucceeded() {
        String tipo = spinnerRP.getSelectedItem().toString();
        int id = 0;
        boolean t= false;
        if(tipo=="Seleccione el tipo de empresa:"){t=false;
        }else{
            if(tipo=="BIENES"){id=1;t=true;}else{if(tipo=="SERVICIOS"){id=2;t=true;}else{id=3;t=true;}}
            }

        if(t==true) {
            if (anof > anoi) {
                Toast.makeText(this,Integer.parseInt(MainActivity.idEmpresaMain)+""+id+" "+code.getText().toString(),Toast.LENGTH_SHORT).show();
                registrarProyecto(
                        Integer.parseInt(MainActivity.idEmpresaMain),
                        id,
                        nombre.getText().toString(),
                        code.getText().toString(),
                        descripcion.getText().toString(),
                        dateStart.getText().toString(),
                        dateEnd.getText().toString(),
                        Integer.parseInt(monto.getText().toString()));
            } else {
                if (anof == anoi) {
                    if (mesf > mesi) {
                        registrarProyecto(Integer.parseInt(MainActivity.idEmpresaMain),id,nombre.getText().toString(),
                                code.getText().toString(),descripcion.getText().toString(),dateStart.getText().toString()
                                ,dateEnd.getText().toString(),Integer.parseInt(monto.getText().toString()));
                    } else {
                        if(mesf==mesi) {
                            Toast.makeText(this, "Los meses son iguales.", Toast.LENGTH_LONG).show();
                            dateStart.setEnabled(true);
                            dateEnd.setEnabled(true);
                        }else{
                            Toast.makeText(this, "Fecha de inicio y final son incorrectos", Toast.LENGTH_LONG).show();
                            dateStart.setEnabled(true);
                            dateEnd.setEnabled(true);
                        }

                    }
                } else {
                    Toast.makeText(this, "Seleccione el año correctamente.", Toast.LENGTH_LONG).show();
                    dateStart.setEnabled(true);
                    dateEnd.setEnabled(true);
                }
            }
        }else{
            Toast.makeText(this,"Seleccione el tipo de empresa.",Toast.LENGTH_SHORT).show();
        }

    }

    private void registrarProyecto(int idEmpresaMain, int id, String s, String s1, String s2, String s3, String s4,int monto) {
        progress = new ProgressDialog(RegistrarProyecto.this);
        progress.setTitle("Registrando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        final Proyecto proyecto = new Proyecto(idEmpresaMain,id,s,s1,s2,s3,s4,monto);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
        Historial historial = new Historial(DetalleProyecto.idProyecto,"El proyecto ha sido creado");
        Call<PostResponse> responseCall = service.registerHistorial(historial);
        responseCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, retrofit2.Response<PostResponse> response) {
                Log.i("HISTORIAL ","PROYECTO OK");
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.i("HISTORIAL ","PROYECTO FAIL");
            }
        });

        Call<ResponseRegistrarProyecto> callRegistrar = service.registerProyecto(proyecto);
        callRegistrar.enqueue(new Callback<ResponseRegistrarProyecto>() {
            @Override
            public void onResponse(Call<ResponseRegistrarProyecto> call, Response<ResponseRegistrarProyecto> response) {
                ResponseRegistrarProyecto responseRP= response.body();
                if(responseRP.getEstado()==1){
                    progress.dismiss();
                    //limpiar();
                    setResult(Activity.RESULT_OK);
                    finish();
                }else{
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"No se pudo registrar",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistrarProyecto> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),"Tenemos problemas con el servidos \n Intentelo mas tarde",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void limpiar() {
        nombre.setText("");
        code.setText("");
        descripcion.setText("");
        spinnerRP.setAdapter(dataAdapter);
        dateEnd.setText("");
        dateStart.setText("");
        monto.setText("");
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
