package arquitectura.proyecto.android.appsgpl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.layout.item_personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterEquipo extends RecyclerView.Adapter<RecyclerAdapterEquipo.ViewHolder>{


    List<Equipo> equipoList = new ArrayList<>();
    Context context;
    int itemLayout;

    public RecyclerAdapterEquipo(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListPersonal( List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_personal,parent,false);
        RecyclerAdapterEquipo.ViewHolder viewHolder= new RecyclerAdapterEquipo.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.codigo_personal.setText(String.valueOf(position+1));
        holder.nombre_personal.setText(equipoList.get(position).getNombrePersonal()+" "+equipoList.get(position).getApellidoPersonal());
        if(equipoList.get(position).getIdTipoPersonal()==1){
            holder.cargo_personal.setText("Jefe del Proyecto");}else{
            holder.cargo_personal.setText("Normal");
        }
    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView codigo_personal;
        TextView nombre_personal;
        TextView cargo_personal;

        public ViewHolder(View itemView) {
            super(itemView);
            codigo_personal = (TextView) itemView.findViewById(R.id.codigo_personal);
            nombre_personal = (TextView) itemView.findViewById(R.id.nombre_personal);
            cargo_personal = (TextView) itemView.findViewById(R.id.cargo_personal);


        }
    }
}

