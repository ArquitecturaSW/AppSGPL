package arquitectura.proyecto.android.appsgpl.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonalND;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDView;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.Presenters.PersonalNDPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.RecyclerItemClickListener;

public class PersonalND extends AppCompatActivity  implements PersonalNDView {
    RecyclerView recyclerView;
    Context context;
    TextView codigowsND;
    Activity activity = new Activity();

    PersonalNDPresenter presenter;
    RecyclerAdapterPersonalND adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_nd);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        presenter = new PersonalNDPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterPersonalND(context,R.layout.item_personal);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),new OnItemClickListener()));
        presenter.loadListPersonal();
         /*Implementacion de RecyclerView con MVP*/
    }


    @Override
    public void initRecycler(List<Personal> personalList1) {
        adapter.setListPersonal(personalList1);
    }

    private class OnItemClickListener extends RecyclerItemClickListener.SimpleOnItemClickListener {


        @Override
        public void onItemClick(View childView, int position) {
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemLongPress(View childView, int position) {
            //Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
            codigowsND = (TextView) childView.findViewById(R.id.codigows);
            Toast.makeText(getApplicationContext(),codigowsND.getText().toString(),Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(PersonalND.this);
            dialog.setTitle("Asignar Jefe");
            dialog.setMessage("¿Seguro deseas asignarle un proyecto");
            dialog.setNegativeButton("Cancelar", null);
            dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    // recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),new OnItemClickListener()));


                }
            });

            dialog.show();

        }
    }

    public AlertDialog preguntar() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setTitle("¿Desea asignarlo jefe?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //asignarJefe().show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
    private Dialog asignarJefe(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());

        LayoutInflater inflater = activity.getLayoutInflater();

        View v = inflater.inflate(R.layout.asignar_jefe_dialogo, null);
        builder.setCancelable(false);
        builder.setView(v);
        TextInputLayout usuario = (TextInputLayout) v.findViewById(R.id.usuario_jefe);
        TextInputLayout password =(TextInputLayout) v.findViewById(R.id.password);
        Button registar = (Button) v.findViewById(R.id.registarjefe);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //personalList.get(i).setCargo("Jefe Proyecto");
                Toast.makeText(context,"Se completo satisfactoriamente.",Toast.LENGTH_SHORT).show();

            }
        });



        return builder.create();
    }
}
