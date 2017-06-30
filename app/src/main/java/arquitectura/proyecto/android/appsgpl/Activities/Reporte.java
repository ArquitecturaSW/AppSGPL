package arquitectura.proyecto.android.appsgpl.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.POJOS.Cantidad;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponseReportes;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reporte extends AppCompatActivity {
    ProgressBar progressBar;
    public int cantidad []={1,1,1,1,1};
    List<Cantidad> cantidadList = new ArrayList<Cantidad>();
    String estado [] = {"En Espera","Ganados","Perdidos","Inconclusos","Finalizados"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        progressBar = (ProgressBar) findViewById(R.id.progressReporte);
        progressBar.setVisibility(View.VISIBLE);
        loadData();

    }

    private void loadData() {
        //Conexion con el webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseReportes> responseCall = service.getReportes(MainActivity.idEmpresaMain);
        responseCall.enqueue(new Callback<ResponseReportes>() {
            @Override
            public void onResponse(Call<ResponseReportes> call, Response<ResponseReportes> response) {
                ResponseReportes responseReportes= response.body();
                cantidadList= responseReportes.getCantidad();
                asignar(cantidadList);
                setupPieChart();
            }
            @Override
            public void onFailure(Call<ResponseReportes> call, Throwable t) {
                Toast.makeText(getApplication(),"Problemas con el servidor",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void asignar(List<Cantidad> cantidadList) {
        Cantidad can = cantidadList.get(0);
        cantidad[0]=can.getEnEspera();
        cantidad[1]=can.getGanado();
        cantidad[2]=can.getInconcluso();
        cantidad[3]=can.getPerdido();
        cantidad[4]=can.getFinalizado();
    }

    private void setupPieChart() {
    //popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0;i<cantidad.length;i++){
            pieEntries.add(new PieEntry(cantidad[i],estado[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Reporte de  Proyectos");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setSliceSpace(4f);
        dataSet.
        PieData data = new PieData(dataSet);


        //Get the Chart
        progressBar.setVisibility(View.GONE);
        PieChart chart = (PieChart) findViewById(R.id.pieChart);
        chart.setVisibility(View.VISIBLE);
        chart.setData(data);
        chart.setContentDescription("PieChart");
        chart.invalidate();

    }

}
