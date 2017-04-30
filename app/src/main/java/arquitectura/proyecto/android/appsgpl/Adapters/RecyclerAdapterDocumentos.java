package arquitectura.proyecto.android.appsgpl.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.POJOS.Documento;
import arquitectura.proyecto.android.appsgpl.R;

import static arquitectura.proyecto.android.appsgpl.R.layout.item_documento;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class RecyclerAdapterDocumentos extends RecyclerView.Adapter<RecyclerAdapterDocumentos.ViewHolder> {

    List<Documento> documentoList = new ArrayList<>();
    Context context;
    int itemLayout;

    public RecyclerAdapterDocumentos(Context context, int itemLayout) {
        this.itemLayout = itemLayout;
        this.context=context;
    }
    public void setListDocumentos( List<Documento> documentoList ) {
        this.documentoList = documentoList;
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

        holder.nombre_documento.setText(documentoList.get(position).getNombre_documento());
        holder.version_documento.setText(documentoList.get(position).getVersion_documento());
        holder.date_documento.setText(documentoList.get(position).getDateCreated());



    }

    @Override
    public int getItemCount() {
        return documentoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre_documento;
        TextView version_documento;
        TextView date_documento;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre_documento = (TextView) itemView.findViewById(R.id.nombre_documento);
            version_documento = (TextView) itemView.findViewById(R.id.version_documento);
            date_documento = (TextView) itemView.findViewById(R.id.date_documento);


        }
    }
}
