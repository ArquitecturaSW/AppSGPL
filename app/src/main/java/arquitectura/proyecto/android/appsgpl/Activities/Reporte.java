package arquitectura.proyecto.android.appsgpl.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.R;

public class Reporte extends AppCompatActivity {

    int cantidad [] = {5,6,8};
    String estado [] = {"Ganados","Perdidos","Inconclusos"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        setupPieChart();


    }

    private void setupPieChart() {
    //popultaing a list of PieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0;i<cantidad.length;i++){
            pieEntries.add(new PieEntry(cantidad[i],estado[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Reporte de  Proyectos");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);


        //Get the Chart
        PieChart chart = (PieChart) findViewById(R.id.pieChart);
        chart.setData(data);
        chart.setContentDescription("PieChart");
        chart.invalidate();

    }

}
