package arquitectura.proyecto.android.appsgpl.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Activities.Login;
import arquitectura.proyecto.android.appsgpl.Activities.Reporte;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterProyectos;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityView;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Presenters.MainActivityPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.RecyclerItemClickListener;
import arquitectura.proyecto.android.appsgpl.Registros.RegistrarProyecto;
import arquitectura.proyecto.android.appsgpl.ViewTargets;
import arquitectura.proyecto.android.appsgpl.util.PreferencesManager;

import static android.R.attr.id;
import static arquitectura.proyecto.android.appsgpl.R.id.fab;
import static arquitectura.proyecto.android.appsgpl.R.id.user;
import static arquitectura.proyecto.android.appsgpl.R.id.usuario_empresa;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,MainActivityView {
    RecyclerView recyclerView;
    RecyclerAdapterProyectos adapter;
    TextView empty;
    TextView nombre_empresa;
    ProgressBar progressBar;
    TextView usuario_empresa;
    TextView ruc_empresa;
    public static String idEmpresaMain;
    TextView correo_empresa;
    int color;
    private MainActivityPresenter presenter;
    List<Proyecto> proyectoLista= new ArrayList<Proyecto>();
    ShowcaseView showcaseView;
    private Target t1;
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        empty = (TextView) findViewById(R.id.empty);

        /*SharedPreferences*/
        SharedPreferences prefs = getSharedPreferences("estado_intro",Context.MODE_PRIVATE);
        String idE = prefs.getString("idEmpresa","XD");
        idEmpresaMain=idE;
        /*Implementacion de RecyclerView con MVP*/
        presenter = new MainActivityPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterProyectos(getApplicationContext(),R.layout.cardview_proyectos);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),new OnItemClickListener()));
        recyclerView.setAdapter(adapter);
        presenter.loadListProyecto();
         /*Implementacion de RecyclerView con MVP*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(getResources().getColor(R.color.colorFAB));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarProyecto.class);
                startActivityForResult(intent,2000);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        /*setear los valor del nav_header*/
        View hView =  navigationView.getHeaderView(0);
        nombre_empresa = (TextView) hView.findViewById(R.id.nombre_empresa_menu);
        usuario_empresa= (TextView) hView.findViewById(R.id.usuario_empresa_menu);
        ruc_empresa= (TextView)hView.findViewById(R.id.ruc_empresa_menu);
        correo_empresa= (TextView)hView.findViewById(R.id.correo_empresa_menu);
        nombre_empresa.setText(prefs.getString("nombre","XD"));
        usuario_empresa.setText("Cuenta: "+prefs.getString("usuario","XD"));
        correo_empresa.setText("Correo: "+prefs.getString("correo","XD"));
        ruc_empresa.setText("Identificador: "+prefs.getString("identificador","XD"));


/*
        try {
            ViewTarget navigationButtonViewTarget = ViewTargets.navigationButtonViewTarget(toolbar);
            new ShowcaseView.Builder(this)
                    .withMaterialShowcase()
                    .setTarget(navigationButtonViewTarget)
                    .setContentTitle("Here's how to highlight items on a toolbar")
                    .singleShot(4000)

                    .build()
                    .show();
        } catch (ViewTargets.MissingViewException e) {
            e.printStackTrace();
        }
*/
    }



    @Override
    public void onBackPressed() {
        presenter.loadListProyecto();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            PreferencesManager prefManager = new PreferencesManager(this);
            prefManager.setPrimeraEjecucion(true);
            prefManager.setIdEmpresaSP();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_proyectos) {
            // Handle the camera action
        } else if (id == R.id.nav_personal) {
            Intent intent = new Intent(getApplicationContext(), PersonalND.class);
            startActivity(intent);

        } else if (id == R.id.nav_reportes) {

            Intent intent = new Intent(getApplicationContext(), Reporte.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (contador){
            case 0:
                showcaseView.setShowcase(t1,true);
                showcaseView.setContentTitle("Menu");
                showcaseView.setAlpha(1.0f);
                showcaseView.setContentText("Pulse es boton");
            break;
            case 1:
                showcaseView.hide();
                break;

        }
        contador++;
    }


    private class OnItemClickListener extends RecyclerItemClickListener.SimpleOnItemClickListener {


        @Override
        public void onItemClick(View childView, int position) {
           Intent intent = new Intent(getApplicationContext(),DetalleProyecto.class);
            Proyecto proyecto = proyectoLista.get(position);
            intent.putExtra("proyecto", (Parcelable) proyecto);
            if(proyectoLista.get(position).getIdEstado()==1){
                color = ContextCompat.getColor(getApplicationContext(),R.color.colorEnEspera);
            }else{
                if(proyectoLista.get(position).getIdEstado()==2){
                    color = ContextCompat.getColor(getApplicationContext(),R.color.colorGanado);
                }else{
                    if(proyectoLista.get(position).getIdEstado()==3){
                        color = ContextCompat.getColor(getApplicationContext(),R.color.colorPerdido);
                    }else{
                        if(proyectoLista.get(position).getIdEstado()==4){
                            color = ContextCompat.getColor(getApplicationContext(),R.color.colorInconcluso);
                        }else{
                            color = ContextCompat.getColor(getApplicationContext(),R.color.colorFinalizado);
                        }
                    }
                }
            }
            intent.putExtra("color",color);
            intent.putExtra("OOO",2);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent,3000);

        }

        @Override
        public void onItemLongPress(View childView, int position) {


        }
        }

    @Override
    public void initRecycler(List<Proyecto> proyectoList) {
        adapter.setListProyecto(proyectoList);
        proyectoLista=proyectoList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        empty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2000 || requestCode == 3000 ) {
            if (resultCode == Activity.RESULT_OK) {
                //Toast.makeText(this, "Proyecto registrado satisfactoriamente", Toast.LENGTH_SHORT).show();
                presenter.loadListProyecto();
            }
        }
    }


}
