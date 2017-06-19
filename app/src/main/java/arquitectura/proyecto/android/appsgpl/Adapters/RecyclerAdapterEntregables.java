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

import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.layout.item_documento;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterEntregables extends RecyclerView.Adapter<RecyclerAdapterEntregables.ViewHolder> {

    List<Entregable> entregableList = new ArrayList<>();
    Context context;
    int itemLayout;

    public RecyclerAdapterEntregables(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListDocumentos( List<Entregable> entregableList) {
        this.entregableList = entregableList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_documento,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nombre_documento.setText(entregableList.get(position).getNombreDoc());
        holder.version_documento.setText(entregableList.get(position).getVersionDoc());
        holder.date_documento.setText(entregableList.get(position).getDateCreated());
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Descargando archivo...",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return entregableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_documento;
        TextView version_documento;
        TextView date_documento;
        ImageView download;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre_documento = (TextView) itemView.findViewById(R.id.nombre_documento);
            version_documento = (TextView) itemView.findViewById(R.id.version_documento);
            date_documento = (TextView) itemView.findViewById(R.id.date_documento);
            download = (ImageView) itemView.findViewById(R.id.download);


        }
    }
}
