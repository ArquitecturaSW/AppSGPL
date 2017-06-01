package arquitectura.proyecto.android.appsgpl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Actividad;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.id.a;
import static arquitectura.proyecto.android.appsgpl.R.layout.cardview_proyectos;
import static arquitectura.proyecto.android.appsgpl.R.layout.list_item_actividad;

/**
 * Created by Jair Barzola on 31-May-17.
 */

public class RecyclerAdapterActividad  extends  RecyclerView.Adapter<RecyclerAdapterActividad.ViewHolder>{

    List<Actividad> actividadList = new ArrayList<>();
    Context context;
     int itemLayout;
    int color;

    public RecyclerAdapterActividad(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListActividad( List<Actividad> actividadList ) {
        this.actividadList = actividadList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(list_item_actividad,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actividad actividad = actividadList.get(position);

        holder.imageView.setImageResource(actividad.getImageResource());
        holder.descripcion.setText(actividad.getDescripcion());
        holder.date.setText(actividad.getDate());

    }

    @Override
    public int getItemCount() {
        return actividadList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView descripcion;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView= (ImageView) itemView.findViewById(R.id.image_d);
            descripcion= (TextView) itemView.findViewById(R.id.descricion_actividad);
            date= (TextView) itemView.findViewById(R.id.fecha_proyecto_d);
        }
    }
}
