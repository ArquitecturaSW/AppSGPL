package arquitectura.proyecto.android.appsgpl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RecyclerAdapterPersonal extends RecyclerView.Adapter<RecyclerAdapterPersonal.ViewHolder>{


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
//holder.nombre_documento.setText(entregableList.get(position).getNombre_documento());
        holder.codigo_personal.setText(personalList.get(position).getCodigo());
        holder.nombre_personal.setText(personalList.get(position).getNombre());
        holder.cargo_personal.setText(personalList.get(position).getCargo());
        holder.info_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"More info",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return personalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView codigo_personal;
        TextView nombre_personal;
        TextView cargo_personal;
        ImageView info_personal;

        public ViewHolder(View itemView) {
            super(itemView);
            codigo_personal = (TextView) itemView.findViewById(R.id.codigo_personal);
            nombre_personal = (TextView) itemView.findViewById(R.id.nombre_personal);
            cargo_personal = (TextView) itemView.findViewById(R.id.cargo_personal);
            info_personal = (ImageView) itemView.findViewById(R.id.info_personal);


        }
    }
}

