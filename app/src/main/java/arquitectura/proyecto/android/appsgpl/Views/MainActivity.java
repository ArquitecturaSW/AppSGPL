package arquitectura.proyecto.android.appsgpl.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.Login;
import arquitectura.proyecto.android.appsgpl.Activities.Reporte;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterProyectos;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityView;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Presenters.MainActivityPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Registros.RegistrarProyecto;

import static android.R.attr.id;
import static arquitectura.proyecto.android.appsgpl.R.id.user;
import static arquitectura.proyecto.android.appsgpl.R.id.usuario_empresa;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainActivityView {
    RecyclerView recyclerView;
    RecyclerAdapterProyectos adapter;
    TextView empty;
    TextView nombre_empresa;
    ProgressBar progressBar;
    TextView usuario_empresa;
    TextView ruc_empresa;
    TextView correo_empresa;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        empty = (TextView) findViewById(R.id.empty);

        /*Implementacion de RecyclerView con MVP*/
        presenter = new MainActivityPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterProyectos(getApplicationContext(),R.layout.cardview_proyectos);
        recyclerView.setAdapter(adapter);
        presenter.loadListProyecto();
         /*Implementacion de RecyclerView con MVP*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(getResources().getColor(R.color.colorFAB));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarProyecto.class);
                startActivity(intent);
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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
        nombre_empresa.setText(Login.nombreEmpresa);
        usuario_empresa.setText("Usuario: "+Login.usuarioEmpresa);
        correo_empresa.setText("Correo: "+Login.correoEmpresa);
        ruc_empresa.setText("RUC: "+Login.rucEmpresa);
        /**/
    }

    @Override
    public void onBackPressed() {
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
    public void initRecycler(List<Proyecto> proyectoList) {

        adapter.setListProyecto(proyectoList);
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

}
