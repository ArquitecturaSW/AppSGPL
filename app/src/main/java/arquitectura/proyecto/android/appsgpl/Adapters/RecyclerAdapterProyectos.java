package arquitectura.proyecto.android.appsgpl.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Views.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static arquitectura.proyecto.android.appsgpl.R.layout.cardview_proyectos;


/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterProyectos extends RecyclerView.Adapter<RecyclerAdapterProyectos.ViewHolder> {

    List<Proyecto> proyectoList = new ArrayList<>();
    Context context;
     int itemLayout;
    int color;
    static   int lastPosition=-1;
    public RecyclerAdapterProyectos(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListProyecto( List<Proyecto> proyectoList ) {
        this.proyectoList = proyectoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(cardview_proyectos,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.id_proyecto.setText(proyectoList.get(position).getCodigoProyecto());
        holder.nombre_proyecto.setText(proyectoList.get(position).getNombreProyecto());
        if(proyectoList.get(position).getIdEstado()==1){
            color = ContextCompat.getColor(context,R.color.colorEnEspera);
        }else{
            if(proyectoList.get(position).getIdEstado()==2){
             color = ContextCompat.getColor(context,R.color.colorGanado);
            }else{
                if(proyectoList.get(position).getIdEstado()==3){
                    color = ContextCompat.getColor(context,R.color.colorPerdido);
                }else{
                    if(proyectoList.get(position).getIdEstado()==4){
                        color = ContextCompat.getColor(context,R.color.colorInconcluso);
                    }else{
                        color = ContextCompat.getColor(context,R.color.colorFinalizado);
                    }
                }
            }
        }
        holder.status_proyecto.setBackgroundColor(color);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetalleProyecto.class);
                Proyecto proyecto = proyectoList.get(position);
                intent.putExtra("proyecto", (Parcelable) proyecto);
                if(proyectoList.get(position).getIdEstado()==1){
                    color = ContextCompat.getColor(context,R.color.colorEnEspera);
                }else{
                    if(proyectoList.get(position).getIdEstado()==2){
                        color = ContextCompat.getColor(context,R.color.colorGanado);
                    }else{
                        if(proyectoList.get(position).getIdEstado()==3){
                            color = ContextCompat.getColor(context,R.color.colorPerdido);
                        }else{
                            if(proyectoList.get(position).getIdEstado()==4){
                                color = ContextCompat.getColor(context,R.color.colorInconcluso);
                            }else{
                                color = ContextCompat.getColor(context,R.color.colorFinalizado);
                            }
                        }
                    }
                }
                intent.putExtra("color",color);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return proyectoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id_proyecto;
        TextView nombre_proyecto;
        ImageView status_proyecto;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            id_proyecto =(TextView) itemView.findViewById(R.id.id_proyecto_m);
            nombre_proyecto =(TextView) itemView.findViewById(R.id.nombre_proyecto_m);
            status_proyecto =(ImageView) itemView.findViewById(R.id.color_status_m);
            cardView =(CardView) itemView.findViewById(R.id.cardView);

        }
    }
    private void setAnimation(View viewToAnimate,int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.left);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }

    }
}
