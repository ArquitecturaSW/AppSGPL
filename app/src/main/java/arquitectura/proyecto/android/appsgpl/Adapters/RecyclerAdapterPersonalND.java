package arquitectura.proyecto.android.appsgpl.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.layout.item_personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterPersonalND extends RecyclerView.Adapter<RecyclerAdapterPersonalND.ViewHolder>{

    Activity activity = new Activity();

    List<Personal> personalList1 = new ArrayList<>();
    Context context;
    int itemLayout;

    public RecyclerAdapterPersonalND(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListPersonal( List<Personal> personalList1) {
        this.personalList1 = personalList1;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_personal,parent,false);
        RecyclerAdapterPersonalND.ViewHolder viewHolder= new RecyclerAdapterPersonalND.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//holder.nombre_documento.setText(entregableList.get(position).getNombre_documento());
        holder.codigo_personal.setText(personalList1.get(position).getCodigo());
        holder.nombre_personal.setText(personalList1.get(position).getNombre());
        holder.cargo_personal.setText(personalList1.get(position).getCargo());
        holder.info_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"More info",Toast.LENGTH_SHORT).show();
            }
        });
        holder.ly_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return personalList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        LinearLayout ly_item;
        TextView codigo_personal;
        TextView nombre_personal;
        TextView cargo_personal;
        ImageView info_personal;

        public ViewHolder(View itemView) {
            super(itemView);
            ly_item = (LinearLayout) itemView.findViewById(R.id.ly);
            codigo_personal = (TextView) itemView.findViewById(R.id.codigo_personal);
            nombre_personal = (TextView) itemView.findViewById(R.id.nombre_personal);
            cargo_personal = (TextView) itemView.findViewById(R.id.cargo_personal);
            info_personal = (ImageView) itemView.findViewById(R.id.info_personal);


        }
    }
    public AlertDialog preguntar() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("¿Desea asignarlo jefe?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                asignarJefe().show();
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        View v = inflater.inflate(R.layout.asignar_jefe_dialogo, null);
        builder.setCancelable(false);
        builder.setView(v);
        TextInputLayout usuario = (TextInputLayout) v.findViewById(R.id.usuario_jefe);
        TextInputLayout password =(TextInputLayout) v.findViewById(R.id.password);
        TextInputLayout conpassword =(TextInputLayout) v.findViewById(R.id.confirmar_contrasena);
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

