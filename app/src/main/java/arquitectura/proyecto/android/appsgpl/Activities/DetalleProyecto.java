package arquitectura.proyecto.android.appsgpl.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.FragmentToFragment;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Views.OneFragment;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.ThreeFragment;
import arquitectura.proyecto.android.appsgpl.Views.TwoFragment;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;

import static android.R.attr.button;
import static android.R.attr.id;
import static arquitectura.proyecto.android.appsgpl.R.id.b;
import static arquitectura.proyecto.android.appsgpl.R.id.d;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class DetalleProyecto extends AppCompatActivity implements FragmentToFragment{
    DetalleProyecto.ViewPagerAdapter adapter;
    Proyecto proyecto;
    Fragment fr3;
    Bundle args3;
    TextView nombre;
    TextView codigo;
    TextView estado;
    TextView fechai;
    TextView fechaf;
    TextView monto;
    TextView descripcion;
    TextView tipoProyecto;
    TabLayout tabLayout;
    Toolbar toolbar;
    public static  int idProyecto;
    public static boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        proyecto = (Proyecto) getIntent().getSerializableExtra("proyecto");
        setTitle(proyecto.getNombreProyecto());
        idProyecto=proyecto.getIdProyecto();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }


        setContentView(R.layout.activity_detalle_proyecto);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setBackgroundColor(bundle.getInt("color"));
        tabLayout.setBackgroundColor(bundle.getInt("color"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(proyecto.getIdEstado()>=4){
            state=true;
        }else{
            state=false;
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            Log.i("PERMISSIONS","OK");
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
        if(requestCode == 1 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            Log.i("PERMISSIONS","OK");
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }
    }
    private void setupViewPager(ViewPager viewPager) {

        fr3 = new ThreeFragment();
        args3 = new Bundle();
        args3.putParcelable("pjt",proyecto);
        fr3.setArguments(args3);

        adapter= new DetalleProyecto.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fr3, "ACTIVIDAD");
        adapter.addFragment(new TwoFragment(), "EQUIPO");
        adapter.addFragment(new OneFragment(), "ENTREGABLES");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_OK);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            PreferencesManager prefManager = new PreferencesManager(this);
            prefManager.setPrimeraEjecucion(true);
            prefManager.setIdEmpresaSP();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
            return true;
        }else {
            if (id == R.id.action_show) {
               showInformation().show();
                return true;
            }
            if(id == android.R.id.home){
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public AlertDialog showInformation() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        String st;
        View v = inflater.inflate(R.layout.decripcion_proyecto, null);
        nombre = (TextView) v.findViewById(R.id.nombre_proyecto_fr3);
        codigo = (TextView) v.findViewById(R.id.codigo_proyecto_fr3);
        descripcion = (TextView) v.findViewById(R.id.descripcion_proyecto_fr3);
        estado=(TextView) v.findViewById(R.id.estado_proyecto_fr3);
        fechaf=(TextView)v.findViewById(R.id.fechaf_proyecto_fr3);
        fechai=(TextView)v.findViewById(R.id.fechai_proyecto_fr3);
        monto = (TextView) v.findViewById(R.id.monto_proyecto_fr3);
        tipoProyecto= (TextView) v.findViewById(R.id.tipo_proyecto_fr3);
        builder.setTitle("Descripción del Proyecto");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nombre.setText(proyecto.getNombreProyecto());
        codigo.setText(proyecto.getCodigoProyecto());

        if(proyecto.getIdCategoriaP()==1){
            tipoProyecto.setText("BIENES");
        }else{
            if(proyecto.getIdCategoriaP()==2){
                tipoProyecto.setText("SERVICIOS");
            }else{
                tipoProyecto.setText("OBRAS");
            }
        }


        descripcion.setText(proyecto.getDescripcionProyecto());
        if(proyecto.getIdEstado()==1){
            st="En espera";
        }else{
            if(proyecto.getIdEstado()==2){
                st="Ganado";

            }else{
                if(proyecto.getIdEstado()==3){
                    st="Perdido";
                }else{
                    if(proyecto.getIdEstado()==4){
                        st="Inconcluso";
                    }else{
                        st="Finalizado";
                    }
                }
            }
        }
        estado.setText(st);
        fechaf.setText(proyecto.getDateEnd());
        fechai.setText(proyecto.getDateStart());
        monto.setText(nf.format(proyecto.getMonto()));
        builder.setView(v);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });



        return builder.create();
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }
    @Override
    public void communicateToFragment1() {
        ThreeFragment fragment = (ThreeFragment) adapter.getItem(0);
        if (fragment != null) {
            fragment.refresh();
        } else {
            Log.i(LOG_TAG, "Fragment 1 is not initialized");
        }
    }

    @Override
    public void communicateToFragment2() {
        TwoFragment fragment = (TwoFragment) adapter.getItem(1);
        if (fragment != null) {
            fragment.refreshTwo();
            fragment.fb2();
        } else {
            Log.i(LOG_TAG, "Fragment 2 is not initialized");
        }
    }

    @Override
    public void setColorActivityI() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorInconcluso));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorInconcluso));
    }

    @Override
    public void setColorActivityG() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorGanado));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorGanado));
    }

    @Override
    public void setColorActivityF() {
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorFinalizado));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorFinalizado));
    }

    @Override
    public void updateState(String state) {
        estado.setText(state);
    }


}

