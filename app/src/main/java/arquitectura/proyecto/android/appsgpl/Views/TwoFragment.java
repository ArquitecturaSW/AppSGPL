package arquitectura.proyecto.android.appsgpl.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterEquipo;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.FragmentToFragment;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Equipo;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.PersonalFree;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.RegisterEquipo;
import arquitectura.proyecto.android.appsgpl.POJOS.ResponsePersonalFree;
import arquitectura.proyecto.android.appsgpl.Presenters.TwoFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TwoFragment extends Fragment implements TwoFragmentView{
    private FragmentToFragment mCallback;
    List<PersonalFree> perList = new ArrayList<>();
    String data;
    int idPersonal;
    TextView empty;
    ProgressBar progressBar;
    RecyclerAdapterEquipo adapter;
    RecyclerView recyclerView;
    TwoFragmentPresenter presenter;
    ProgressDialog progress;
    APIService service;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         service = retrofit.create(APIService.class);
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_two, container, false);
            /*Implementacion de RecyclerView con MVP*/
        presenter = new TwoFragmentPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        empty= (TextView) rootView.findViewById(R.id.emptyTwo);
        progressBar= (ProgressBar) rootView.findViewById(R.id.progressTwo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterEquipo(getContext(),R.layout.item_personal);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),new OnItemClickListener()));
        presenter.loadListPersonal();
         /*Implementacion de RecyclerView con MVP*/
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // registrarPersonal().show();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                progress = new ProgressDialog(getContext());
                progress.setTitle("Consultando");
                progress.setMessage("Espere ...");
                progress.show();
                progress.setCanceledOnTouchOutside(false);

                Call<ResponsePersonalFree> personalFreeCall = service.getPersonalFree(MainActivity.idEmpresaMain);
                personalFreeCall.enqueue(new Callback<ResponsePersonalFree>() {
                    @Override
                    public void onResponse(Call<ResponsePersonalFree> call, Response<ResponsePersonalFree> response) {
                        ResponsePersonalFree personalFree = response.body();
                        if(personalFree.getEstado()==1){
                            List<PersonalFree> perList = new ArrayList<>();
                            perList= personalFree.getPerList();
                            mostrarPersonalFree(perList);
                            progress.dismiss();
                        }
                        else {
                            progress.dismiss();
                            Toast.makeText(getContext(), "Registre Personal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePersonalFree> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(getContext(), "Tenemos problemas con el servidor", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        return rootView;
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

    private void mostrarPersonalFree(List<PersonalFree> perList) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        builderSingle.setTitle("Selecciona un miembro:");

        final ArrayAdapter<PersonalFree> arrayAdapter = new ArrayAdapter<PersonalFree>(getContext(), android.R.layout.select_dialog_singlechoice, perList);
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                data =arrayAdapter.getItem(position).getNombrePersonal()+" "+arrayAdapter.getItem(position).getApellidoPersonal();
               idPersonal=arrayAdapter.getItem(position).getIdPersonal();
                //String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                builderInner.setMessage(data);
                builderInner.setTitle("Has seleccionado a:");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                        registrarEquipo(idPersonal,data);
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }

    private void registrarEquipo(int idPersonal,String namePersonal) {
        progress = new ProgressDialog(getContext());
        progress.setTitle("Consultando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);

        Historial historial = new Historial(DetalleProyecto.idProyecto,"Se agrego a "+namePersonal+" al equipo");
        Call<PostResponse> responseCall = service.registerHistorial(historial);
        responseCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, retrofit2.Response<PostResponse> response) {
                Log.i("HISTORIAL ","ENTREGABlE OK");
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.i("HISTORIAL ","ENTREGABlE FAIL");
            }
        });

        RegisterEquipo equipo = new RegisterEquipo(DetalleProyecto.idProyecto,idPersonal);
        Call<PostResponse> postCall = service.registerEquipo(equipo);
        postCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postres= response.body();
                if(postres.getEstado()==1){
                    progress.dismiss();
                    Toast.makeText(getContext(),"Registrado exitosamente",Toast.LENGTH_SHORT).show();
                    mCallback.communicateToFragment1();
                }
                else{
                    progress.dismiss();
                    Toast.makeText(getContext(),"No se logro registrar",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getContext(),"Tenemos problemas con el servidor",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private class OnItemClickListener extends RecyclerItemClickListener.SimpleOnItemClickListener {


        @Override
        public void onItemClick(View childView, int position) {
        }

        @Override
        public void onItemLongPress(View childView, int position) {
            preguntar().show();

        }

        }

    @Override
    public void initRecycler(List<Equipo> equipoList) {
        adapter.setListPersonal(equipoList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        recyclerView.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private AlertDialog asignarJefe(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.asignar_jefe_dialogo, null);
        builder.setCancelable(false);
        builder.setView(v);
        final TextInputEditText usuario = (TextInputEditText) v.findViewById(R.id.usuario_jefe);
        final TextInputEditText password =(TextInputEditText) v.findViewById(R.id.password);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = usuario.getText().toString();
                String pass =password.getText().toString();

                if(user.equals("")|| pass.equals("")){
                    usuario.setError("Llene este campo");
                    password.setError("Llene este campo");
                }else{
                    dialog.dismiss();
                    Toast.makeText(getContext(),"OK",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return builder.create();
    }
    public AlertDialog preguntar() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("¿Desea asignarlo jefe?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                asignarJefe().show();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }


}