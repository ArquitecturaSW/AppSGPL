package arquitectura.proyecto.android.appsgpl.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonal;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.Presenters.TwoFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;


public class TwoFragment extends Fragment implements TwoFragmentView {

    RecyclerAdapterPersonal adapter;
    RecyclerView recyclerView;
    TwoFragmentPresenter presenter;
    public TwoFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_two, container, false);
            /*Implementacion de RecyclerView con MVP*/
        presenter = new TwoFragmentPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterPersonal(getContext(),R.layout.item_personal);
        recyclerView.setAdapter(adapter);
        presenter.loadListPersonal();
         /*Implementacion de RecyclerView con MVP*/
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarPersonal().show();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        return rootView;
    }

    @Override
    public void initRecycler(List<Personal> personalList) {
        adapter.setListPersonal(personalList);

    }
    public AlertDialog registrarPersonal() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.registrar_proyecto, null);

        builder.setView(v);

        Button registar = (Button) v.findViewById(R.id.registrar_proyecto);

        registar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear Cuenta..

                    }
                }
        );

        return builder.create();
    }
}