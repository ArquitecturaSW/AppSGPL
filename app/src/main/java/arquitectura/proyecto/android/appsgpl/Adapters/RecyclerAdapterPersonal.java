package arquitectura.proyecto.android.appsgpl.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.layout.item_personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterPersonal extends RecyclerView.Adapter<RecyclerAdapterPersonal.ViewHolder>{

    Activity activity = new Activity();

    List<Personal> personalList = new ArrayList<>();
    Context context;
    int itemLayout;

    public RecyclerAdapterPersonal(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListPersonal( List<Personal> personalList) {
        this.personalList = personalList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_personal,parent,false);
        RecyclerAdapterPersonal.ViewHolder viewHolder= new RecyclerAdapterPersonal.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.codigo_personal.setText(String.valueOf(position+1));
        holder.codigo_ws.setText(String.valueOf(position));
        holder.nombre_personal.setText(personalList.get(position).getNombrePersonal()+" "+personalList.get(position).getApellidoPersonal());
        holder.cargo_personal.setText(personalList.get(position).getOcupacionPersonal());
        /*if(personalList.get(position).getIdTipo()==1){
            holder.cargo_personal.setText("Jefe");}else{
            holder.cargo_personal.setText("Normal");
        }*/


    }

    @Override
    public int getItemCount() {
        return personalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView codigo_personal;
        TextView nombre_personal;
        TextView cargo_personal;
        TextView codigo_ws;

        public ViewHolder(View itemView) {
            super(itemView);
            codigo_personal = (TextView) itemView.findViewById(R.id.codigo_personal);
            nombre_personal = (TextView) itemView.findViewById(R.id.nombre_personal);
            cargo_personal = (TextView) itemView.findViewById(R.id.cargo_personal);
            codigo_ws = (TextView) itemView.findViewById(R.id.codigows);
        }
    }
}

