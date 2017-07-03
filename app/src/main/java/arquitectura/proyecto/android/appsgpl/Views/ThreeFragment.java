package arquitectura.proyecto.android.appsgpl.Views;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Activities.DetalleProyecto;
import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterActividad;
import arquitectura.proyecto.android.appsgpl.Interfaces.APIService;
import arquitectura.proyecto.android.appsgpl.Interfaces.FragmentToFragment;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Historial;
import arquitectura.proyecto.android.appsgpl.POJOS.PostResponse;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Presenters.ThreeFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Registros.RegistrarProyecto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;
import static arquitectura.proyecto.android.appsgpl.R.id.a;


public class ThreeFragment extends Fragment implements ThreeFragmentView{
    private FragmentToFragment mCallback;
    RecyclerView recyclerView;
    RecyclerAdapterActividad adapter;
    FloatingActionButton fb;
    TextView empty;
    ProgressBar progressBar;
    ProgressDialog progress;
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
        fb = (FloatingActionButton) rootView.findViewById(R.id.fabThreeFragment);
        empty= (TextView) rootView.findViewById(R.id.emptyThree);
        progressBar= (ProgressBar) rootView.findViewById(R.id.progressThree);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarEstadoP().show();
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        presenter = new ThreeFragmentPresenterImpl(this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        adapter = new RecyclerAdapterActividad(getContext(),R.layout.list_item_actividad);
        recyclerView.setAdapter(adapter);

        presenter.loadListActividad();
         /*Implementacion de RecyclerView con MVP*/

        if(DetalleProyecto.state==true){
            fb3();
        }
        if(DetalleProyecto.nn==true){
            fb3();
        }
        return rootView;
    }


    @Override
    public void initRecycler(List<Historial> historialList) {
        adapter.setListActividad(historialList);
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
    public void refresh(){
        presenter.loadListActividad();
    }



    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToFragment");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    public AlertDialog cambiarEstadoP() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Desea terminar este proyecto?")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Cambiar estado proyecto
                                //cambiar color
                                //perder opciones de los botones
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                String date = df.format(Calendar.getInstance().getTime());
                                terminarProyecto(DetalleProyecto.idProyecto,date);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        return builder.create();
    }

    private void terminarProyecto(int idProyecto, String date) {
        progress = new ProgressDialog(getContext());
        progress.setTitle("Registrando");
        progress.setMessage("Espere ...");
        progress.show();
        progress.setCanceledOnTouchOutside(false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://proyectos2017.esy.es/HOME-CONTENT/servicios/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        Historial historial = new Historial(DetalleProyecto.idProyecto,"El proyecto ha finalizado");
        Call<PostResponse> responseCall = service.registerHistorial(historial);
        responseCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, retrofit2.Response<PostResponse> response) {
                Log.i("HISTORIAL ","Proyecto OK");
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.i("HISTORIAL ","Proyecto FAIL");
            }
        });

        Proyecto proyecto = new Proyecto(idProyecto,date);
        Call<PostResponse> responsecall = service.finishProyecto(proyecto);
        responsecall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();
                if(result.getEstado()==1){
                    DetalleProyecto.state=true;
                    fb3();
                    progress.dismiss();
                    presenter.loadListActividad();
                    mCallback.communicateToFragment2();
                    mCallback.setColorActivityI();
                    mCallback.updateState(4);
                    getActivity().setResult(Activity.RESULT_OK);
                    Toast.makeText(getContext(),"Operación realizada correctamente",Toast.LENGTH_SHORT).show();

                }else{
                    progress.dismiss();
                    Toast.makeText(getContext(),"Error al realizar esta operación",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getContext(),"Problemas con el servidor",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void fb3(){
        fb.setVisibility(View.GONE);
    }

}