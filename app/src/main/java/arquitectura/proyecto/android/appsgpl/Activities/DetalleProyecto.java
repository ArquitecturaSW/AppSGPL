package arquitectura.proyecto.android.appsgpl.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Views.OneFragment;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.ThreeFragment;
import arquitectura.proyecto.android.appsgpl.Views.TwoFragment;

import static android.R.attr.button;
import static arquitectura.proyecto.android.appsgpl.R.id.b;
import static arquitectura.proyecto.android.appsgpl.R.id.d;

public class DetalleProyecto extends AppCompatActivity {
    Proyecto proyecto;
    Fragment fr3;
    Bundle args3;
    TextView nombre;
    TextView codigo;
    TextView jefe;
    TextView estado;
    TextView fechai;
    TextView fechaf;
    TextView monto;
    TextView descripcion;
    TextView tipoProyecto;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        proyecto = (Proyecto) getIntent().getSerializableExtra("proyecto");
        setTitle(proyecto.getNombreProyecto());



        setContentView(R.layout.activity_detalle_proyecto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setBackgroundColor(bundle.getInt("color"));
        tabLayout.setBackgroundColor(bundle.getInt("color"));

    }
    private void setupViewPager(ViewPager viewPager) {

        fr3 = new ThreeFragment();
        args3 = new Bundle();
        args3.putParcelable("pjt",proyecto);
        fr3.setArguments(args3);

        DetalleProyecto.ViewPagerAdapter adapter = new DetalleProyecto.ViewPagerAdapter(getSupportFragmentManager());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
            return true;
        }else {
            if (id == R.id.action_show) {
               showInformation().show();
                return true;
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
        jefe= (TextView) v.findViewById(R.id.jefe_proyecto_fr3);
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
            jefe.setText("Sin jefe");
        }else{
            if(proyecto.getIdEstado()==2){
                st="Ganado";
                jefe.setText("Jair Barzola Cuba");
            }else{
                if(proyecto.getIdEstado()==3){
                    st="Perdido";
                    jefe.setText("Jair Barzola Cuba");
                }else{
                    if(proyecto.getIdEstado()==4){
                        st="Inconcluso";
                        jefe.setText("Jair Barzola Cuba");
                    }else{
                        st="Finalizado";
                        jefe.setText("Jair Barzola Cuba");
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




}

