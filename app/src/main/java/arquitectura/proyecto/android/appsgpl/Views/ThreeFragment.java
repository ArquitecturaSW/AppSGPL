package arquitectura.proyecto.android.appsgpl.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterActividad;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonal;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Actividad;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Presenters.ThreeFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static arquitectura.proyecto.android.appsgpl.R.id.recyclerView;


public class ThreeFragment extends Fragment implements ThreeFragmentView{

    RecyclerView recyclerView;
    RecyclerAdapterActividad adapter;
    ThreeFragmentPresenter presenter;
    public ThreeFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_three, container, false);

        presenter = new ThreeFragmentPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterActividad(getContext(),R.layout.list_item_actividad);
        recyclerView.setAdapter(adapter);
        presenter.loadListActividad();
         /*Implementacion de RecyclerView con MVP*/


        return rootView;
    }


    @Override
    public void initRecycler(List<Actividad> actividadList) {
        adapter.setListActividad(actividadList);
    }
}