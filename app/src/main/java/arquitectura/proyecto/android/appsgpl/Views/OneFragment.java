package arquitectura.proyecto.android.appsgpl.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Registros.RegistrarEntregable;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterEntregables;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;
import arquitectura.proyecto.android.appsgpl.Presenters.OneFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;

public class OneFragment extends Fragment implements OneFragmentView {
    RecyclerView recyclerView;
    RecyclerAdapterEntregables adapter;
    private OneFragmentPresenter presenter;

    FloatingActionButton floatingActionButton;
    public OneFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_one, container, false);
               /*Implementacion de RecyclerView con MVP*/
        presenter = new OneFragmentPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fabEntregable);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistrarEntregable.class);
                startActivity(intent);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterEntregables(getContext(),R.layout.cardview_proyectos);
        recyclerView.setAdapter(adapter);
        presenter.loadListDocumento();
         /*Implementacion de RecyclerView con MVP*/


        return  rootView;
    }

    @Override
    public void initRecycler(List<Entregable> entregableList) {
        adapter.setListDocumentos(entregableList);
    }

}
