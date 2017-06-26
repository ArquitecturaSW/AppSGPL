package arquitectura.proyecto.android.appsgpl.Registros;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.EntregableP;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseEntregable;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarEntregable extends AppCompatActivity implements  Validator.ValidationListener{
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText nombre;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText version;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText url;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText descripcion;
    Button registar;
    Spinner sp;
    Thread t;
    ImageView image;
    String content_type;
    File f;
    APIService service;
    ProgressDialog progress;
    String uri;
    String file_path;
    int id;

    ArrayAdapter<String> dataAdapter;
    private Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_entregable);

        validator = new Validator(this);
        validator.setValidationListener(this);
        nombre = (TextInputEditText) findViewById(R.id.nombre_entregable);
        version = (TextInputEditText) findViewById(R.id.version_entregable);
        descripcion = (TextInputEditText) findViewById(R.id.descrip_entregable);
        url = (TextInputEditText) findViewById(R.id.url_entregable);
        image = (ImageView) findViewById(R.id.upload);
        registar= (Button) findViewById(R.id.registrarentregable);
        sp= (Spinner) findViewById(R.id.spinnerentregable);


                /* Llenado del spinner*/
        List<String> list = new ArrayList<String>();
        list.add("Seleccione el tipo de entregable:");
        list.add("DOCUMENTOS");
        list.add("PLANOS");
        list.add("PDF");

        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);


        image_button();
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();


            }
        });
    }

    private void image_button() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(RegistrarEntregable.this)
                        .withRequestCode(10)
                        .start();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK){


                    f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                     content_type = getMimeType(f.getPath());
                     file_path = f.getAbsolutePath();
            setEditText(DetalleProyecto.idProyecto+file_path.substring(file_path.lastIndexOf("/")+1));

        }
    }

    private void setEditText(String substring) {
        url.setText("servicios/archivos/"+substring);
        uri="http://proyectos2017.esy.es/HOME-CONTENT/servicios/archivos/"+substring;
        url.setEnabled(false);
    }
    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    @Override
    public void onValidationSucceeded() {
        String cad = url.getText().toString();
        int result= cad.indexOf("servicios/archivos/");
        String tipo = sp.getSelectedItem().toString();
        boolean t= false;
        if(tipo=="Seleccione el tipo de entregable:"){t=false;
        }else{
            if(tipo=="DOCUMENTOS"){id=1;t=true;}else{if(tipo=="PLANOS"){id=2;t=true;}else {
                if(tipo=="PDF"){id=3;t=true;}else {if(tipo=="S.A.C"){id=4;t=true;}else{id=5;t=true;}}
            }}}

        if(t==false) {
            if (cad.equals("") || result == -1) {
                Toast.makeText(this, "Seleccione un documento y tipo de entregable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Seleccione un tipo de entregable", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (cad.equals("") || result == -1) {
                Toast.makeText(this, "Seleccione un documento.", Toast.LENGTH_SHORT).show();
            }else{
                registrarEntregable();
            }
        }
    }

    private void registrarEntregable() {
        UploadFile upload = new UploadFile();
        upload.execute();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText ) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }



    private class UploadFile extends AsyncTask<Void,Boolean,Boolean>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(RegistrarEntregable.this);
            progress.setTitle("Registrando");
            progress.setMessage("Espere ...");
            progress.show();
            progress.setCanceledOnTouchOutside(false);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean state;
            OkHttpClient client = new OkHttpClient();
            RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);

            RequestBody request_body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", content_type)
                    .addFormDataPart("uploaded_file",DetalleProyecto.idProyecto+file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                    .build();
            Request request = new Request.Builder()
                    .url("http://proyectos2017.esy.es/HOME-CONTENT/servicios/subidaAndroid.php")
                    .post(request_body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                if(!response.isSuccessful()){
                    Log.i("TAG","fail "+response);
                    state=false;
                    progress.dismiss();
                }else{
                    Log.i("TAG","OK "+response);
                    state =true;
                    Log.i("TAG ", String.valueOf(state));
                    progress.dismiss();
                }
                Log.i("TAG 2 ", String.valueOf(state));

            } catch (IOException e) {
                e.printStackTrace();
                state =false;
            }
            Log.i("TAG 3 ", String.valueOf(state));
            return state;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result==true){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(APIService.class);
                Historial historial = new Historial(DetalleProyecto.idProyecto,"Nuevo Entregable");
                Call<PostResponse> responseCall = service.registerHistorial(historial);
                responseCall.enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, retrofit2.Response<PostResponse> response) {
                        Log.i("HISTORIAL ","ENTREGABlE OK");
                    }

                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.i("HISTORIAL ","ENTREGABlE FAIL");
                    }
                });


                EntregableP entregableP = new EntregableP(id, DetalleProyecto.idProyecto,nombre.getText().toString(),version.getText().toString()
                ,descripcion.getText().toString(),uri);

                Call<ResponseEntregable> entregableCall = service.registerEntregable(entregableP);
                entregableCall.enqueue(new Callback<ResponseEntregable>() {
                    @Override
                    public void onResponse(Call<ResponseEntregable> call, retrofit2.Response<ResponseEntregable> response) {
                        ResponseEntregable resEntregable = response.body();
                        if(resEntregable.getEstado()==1){
                            progress.dismiss();
                            //limpiar();
                            setResult(Activity.RESULT_OK);
                            finish();

                        }else{
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), "Anda algo mal \n Vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntregable> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "Problemas con el servidor", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Error al subir el documento.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void limpiar() {
        nombre.setText("");
        version.setText("");
        descripcion.setText("");
        url.setText("");
        sp.setAdapter(dataAdapter);
    }
}
