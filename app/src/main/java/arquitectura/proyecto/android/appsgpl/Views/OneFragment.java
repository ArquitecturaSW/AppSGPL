package arquitectura.proyecto.android.appsgpl.Views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.FragmentToFragment;
import arquitectura.proyecto.android.appsgpl.POJOS.Entregable;
import arquitectura.proyecto.android.appsgpl.Registros.RegistrarEntregable;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterEntregables;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentView;
import arquitectura.proyecto.android.appsgpl.Presenters.OneFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;

public class OneFragment extends Fragment implements OneFragmentView {
    private static final int PERMISSION_REQUEST_CODE = 1;
    FragmentToFragment mCallback;
    RecyclerView recyclerView;
    RecyclerAdapterEntregables adapter;
    TextView emptyO;
    ProgressBar progressBarO;
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
        emptyO = (TextView) rootView.findViewById(R.id.emptyOne);
        progressBarO = (ProgressBar) rootView.findViewById(R.id.progressOne);
        presenter = new OneFragmentPresenterImpl(this);
        // Inflate the layout for this fragment

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fabEntregable);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), RegistrarEntregable.class);
                startActivityForResult(intent,1000);

            }
        });
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewOne);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapterEntregables(getContext(), R.layout.item_documento);
        recyclerView.setAdapter(adapter);
        presenter.loadListDocumento();
        return  rootView;
    }

    @Override
    public void initRecycler(List<Entregable> entregableList) {
        adapter.setListDocumentos(entregableList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        emptyO.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyO.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBarO.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarO.setVisibility(View.GONE);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000){
            if(resultCode== Activity.RESULT_OK){
                Toast.makeText(getContext(), "Entregable registrado satisfactoriamente", Toast.LENGTH_SHORT).show();
                presenter.loadListDocumento();
                mCallback.communicateToFragment1();
            }
        }
    }


}
