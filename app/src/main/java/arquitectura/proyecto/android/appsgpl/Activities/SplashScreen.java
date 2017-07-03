package arquitectura.proyecto.android.appsgpl.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseProyecto;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;
import static arquitectura.proyecto.android.appsgpl.R.drawable.tipo;

public class SplashScreen extends AppCompatActivity {
    private  PreferencesManager prefManager;
    Proyecto proyecto;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefManager = new PreferencesManager(this);
        final SharedPreferences prefs = getSharedPreferences("estado_intro", Context.MODE_PRIVATE);
        Thread mythread = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();

        Log.i("ID","inicio");
        if (prefManager.isPrimeraEjecucion()) {
            prefManager.setPrimeraEjecucion(false);
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        } else {
            id = prefs.getString("idProyecto","-1");
            Log.i("ID",id);
            if (id.equals("0")) {
                Log.i("ID","b");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                Log.i("ID","a");
                getProyectos();
            }

        }
    }

    private void getProyectos() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseProyecto> proyectoCall = service.getProject(id);
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
                    Intent intent = new Intent(getApplicationContext(), DetalleProyecto.class);
                    intent.putExtra("OOO",3);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onFailure(Call<ResponseProyecto> call, Throwable t) {
                Log.i("ID","4");
                Intent intent = new Intent(getApplicationContext(), DetalleProyecto.class);
                intent.putExtra("OOO",4);
                startActivity(intent);
                finish();
            }
        });


    }

}
