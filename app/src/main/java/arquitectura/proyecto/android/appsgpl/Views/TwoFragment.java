package arquitectura.proyecto.android.appsgpl.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonal;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.Presenters.TwoFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;


public class TwoFragment extends Fragment implements TwoFragmentView ,Validator.ValidationListener{

    //Dialogo
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,max=15,message = "Mínimo 4 caracteres")
    TextInputEditText nombre;
    @NotEmpty(message = "No deje vacío este campo.")
    @Length(min=4,message = "Mínimo 4 caracteres")
    TextInputEditText code;
    @NotEmpty(message = "Debe elegir un fecha i.")
    TextInputEditText dateStart;
    @NotEmpty(message = "Debe elegir un fecha f.")
    TextInputEditText dateEnd;
    @NotEmpty(message = "No deje vacío este campo.")
    TextInputEditText descripcion;

    Validator validator;
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
        validator = new Validator(this);
        validator.setValidationListener(this);
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
               // registrarPersonal().show();
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
   /* public AlertDialog registrarPersonal() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.registrar_proyecto, null);

        builder.setView(v);

        Button registar = (Button) v.findViewById(R.id.registrar_proyecto);
        ImageView s1= (ImageView) v.findViewById(R.id.seleccionar_fecha_i);
        ImageView s2= (ImageView) v.findViewById(R.id.seleccionar_fecha_f);
        nombre = (TextInputEditText) v.findViewById(R.id.nombre_proyecto);
        code= (TextInputEditText) v.findViewById(R.id.code_proyecto);
        descripcion= (TextInputEditText) v.findViewById(R.id.descripcion_proyecto);
        dateEnd= (TextInputEditText) v.findViewById(R.id.fecha_fin);
        dateStart= (TextInputEditText) v.findViewById(R.id.fecha_inicio);



        registar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear Cuenta..
                        validator.validate();

                    }
                }
        );

        return builder.create();
    }*/

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof TextInputEditText ) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}