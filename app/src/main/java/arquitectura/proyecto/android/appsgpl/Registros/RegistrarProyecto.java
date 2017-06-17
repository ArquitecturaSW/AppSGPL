package arquitectura.proyecto.android.appsgpl.Registros;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import arquitectura.proyecto.android.appsgpl.R;

import static android.R.id.message;

public class RegistrarProyecto extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,max=15,message = "Mínimo 4 caracteres")
    TextInputEditText nombre;
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,message = "Mínimo 4 caracteres")
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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
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
        dateEnd.setText(dayOfMonth+"/"+mes+"/"+year);
        dateEnd.setEnabled(false);
    }

    private void updateDate1(int dayOfMonth, int monthOfYear, int year) {
        diai=dayOfMonth;mesi=monthOfYear;anoi=year;
        int mes1=monthOfYear+1;
        dateStart.setText(dayOfMonth+"/"+mes1+"/"+year);
        dateStart.setEnabled(false);
    }

    @Override
    public void onValidationSucceeded() {
        if(anof>anoi){
            Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
        }else{
            if(anof==anoi){
                if(mesf>mesi){
                    Toast.makeText(this, "OK", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Seleccione el mes correctamente.", Toast.LENGTH_LONG).show();
                    dateStart.setEnabled(true);
                    dateEnd.setEnabled(true);
                }
            }
            else{
                Toast.makeText(this,"Seleccione el año correctamente.", Toast.LENGTH_LONG).show();
                dateStart.setEnabled(true);
                dateEnd.setEnabled(true);
            }
        }

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
