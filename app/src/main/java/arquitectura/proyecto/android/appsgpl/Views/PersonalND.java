package arquitectura.proyecto.android.appsgpl.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonal;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDView;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.Presenters.PersonalNDPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.RecyclerItemClickListener;
import arquitectura.proyecto.android.appsgpl.Registros.RegistrarPersonal;

public class PersonalND extends AppCompatActivity  implements PersonalNDView {
    RecyclerView recyclerView;
    Context context;
    TextView codigowsND;
    Activity activity = new Activity();
    TextView empty;
    ProgressBar progressBar;
    List<Personal> personals= new ArrayList<>();
    PersonalNDPresenter presenter;
    RecyclerAdapterPersonal adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_nd);

        // Inflate the layout for this fragment
        progressBar = (ProgressBar) findViewById(R.id.progressP);
        empty= (TextView) findViewById(R.id.emptyP);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        presenter = new PersonalNDPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterPersonal(context,R.layout.item_personal);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutND);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),new OnItemClickListener()));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PersonalND.this, RegistrarPersonal.class),1000);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadListPersonal();
            }
        });

        presenter.loadListPersonal();
         /*Implementacion de RecyclerView con MVP*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
                if(resultCode== Activity.RESULT_OK){
                presenter.loadListPersonal();
                Toast.makeText(this, "Personal registrado satisfactoriamente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void initRecycler(List<Personal> personalList1) {
        adapter.setListPersonal(personalList1);
        personals=personalList1;
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        recyclerView.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private class OnItemClickListener extends RecyclerItemClickListener.SimpleOnItemClickListener {


        @Override
        public void onItemClick(View childView, int position) {
        }

        @Override
        public void onItemLongPress(View childView, int position) {
            //Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
            codigowsND = (TextView) childView.findViewById(R.id.codigows);
            //Toast.makeText(getApplicationContext(),codigowsND.getText().toString(),Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(PersonalND.this);
            LayoutInflater inflater = PersonalND.this.getLayoutInflater();
            View v = inflater.inflate(R.layout.descripcion_personal, null);
            builder.setCancelable(false);
            builder.setView(v);
            final TextView datos = (TextView) v.findViewById(R.id.nombre_personal_d);
            final TextView idproyecto = (TextView) v.findViewById(R.id.tipo_personal);
            final TextView dni = (TextView) v.findViewById(R.id.dni_personal_d);
            final TextView correo = (TextView) v.findViewById(R.id.correo_personal);
            final TextView direccion = (TextView) v.findViewById(R.id.direccion_personal_d);
            final TextView edad = (TextView) v.findViewById(R.id.edad_personal_d);
            final TextView movil = (TextView) v.findViewById(R.id.nro_telefono);
            final TextView ocupacion = (TextView) v.findViewById(R.id.ocupacion_personal_d);
            final TextView usuario = (TextView) v.findViewById(R.id.usuario_personal);
            final TextView password = (TextView) v.findViewById(R.id.password_personal);

            datos.setText(personals.get(position).getNombrePersonal() + " " + personals.get(position).getApellidoPersonal());
            if (personals.get(position).getEstadoPersonal()== 0) {
                idproyecto.setText("Sin ningún proyecto");
            } else {
                idproyecto.setText("Esta asignado a un proyecto");
            }
            dni.setText(personals.get(position).getDniPersonal());
            correo.setText(personals.get(position).getCorreoPersonal());
            movil.setText(String.valueOf(personals.get(position).getTelefonoPersonal()));
            direccion.setText(personals.get(position).getDireccionPersonal());
            edad.setText(String.valueOf(personals.get(position).getEdadPersonal()));
            ocupacion.setText(personals.get(position).getOcupacionPersonal());
            if (personals.get(position).getUsuarioPersonal()==null) {
                usuario.setText("Aún no tiene un usuario");
            } else{
                usuario.setText(personals.get(position).getUsuarioPersonal());
            }
            if (personals.get(position).getUsuarioPassword()==null) {
                password.setText("Aún no tiene una contraseña");
            } else{
                password.setText(personals.get(position).getUsuarioPassword());
            }
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                }
            });

            builder.show();
        }
    }


}
