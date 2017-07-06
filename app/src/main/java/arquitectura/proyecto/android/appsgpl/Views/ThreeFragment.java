package arquitectura.proyecto.android.appsgpl.Views;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
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
import static arquitectura.proyecto.android.appsgpl.R.id.fab_1;
import static arquitectura.proyecto.android.appsgpl.R.id.fab_container;


public class ThreeFragment extends Fragment implements ThreeFragmentView{
    private FragmentToFragment mCallback;
    RecyclerView recyclerView;
    RecyclerAdapterActividad adapter;
    FloatingActionButton fb;
    TextView empty;
    ProgressBar progressBar;
    ProgressDialog progress;
    ThreeFragmentPresenter presenter;
    SwipeRefreshLayout swipeRefreshLayout;

    ImageButton fab;
    private boolean expanded = false;
    /*declaramos como vistas no como imagebutton*/
    private  View fabAction1;
    private  View fabAction2;

    private float offset1;
    private float offset2;
    ViewGroup fabContainer;

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
        //fb = (FloatingActionButton) rootView.findViewById(R.id.fabThreeFragment);
        empty= (TextView) rootView.findViewById(R.id.emptyThree);
        progressBar= (ProgressBar) rootView.findViewById(R.id.progressThree);
        /*fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fb.setVisibility(View.GONE);*/
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        presenter = new ThreeFragmentPresenterImpl(this);
        swipeRefreshLayout =(SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayoutThree);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        adapter = new RecyclerAdapterActividad(getContext(),R.layout.list_item_actividad);
        recyclerView.setAdapter(adapter);
        presenter.loadListActividad();

        fabContainer= (ViewGroup) rootView.findViewById(R.id.fab_container);
        fabAction1 = rootView.findViewById(R.id.fab_1);
        fabAction2 = rootView.findViewById(R.id.fab_2);
        fabAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarEstadoP().show();
            }
        });
        fabAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Power Off",Toast.LENGTH_SHORT).show();
            }
        });

        fab=(ImageButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded= !expanded;
                if(expanded){
                    fabContainer.setBackgroundResource(R.color.background);
                    expandedFab();
                }else {
                    fabContainer.setBackgroundResource(Color.TRANSPARENT);
                    collapseFab();
                }
            }
        });
        // para se se muestren de forma cotinua en el eje y , el que hace la transicion
        fabContainer.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        fabContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                        offset1 = fab.getY()-fabAction1.getY();
                        fabAction1.setTranslationY(offset1);

                        offset2 = fab.getY()-fabAction2.getY();
                        fabAction2.setTranslationY(offset2);
                        return true;
                    }
                }
        );


        if(DetalleProyecto.state==true){
            fb3();
        }
        if(DetalleProyecto.nn==true){
            fb3();
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadListActividad();
            }
        });
        return rootView;
    }


    @Override
    public void initRecycler(List<Historial> historialList) {
        adapter.setListActividad(historialList);
        swipeRefreshLayout.setRefreshing(false);
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
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
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
                                fabContainer.setBackgroundResource(Color.TRANSPARENT);
                                collapseFab();
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
                                fabContainer.setBackgroundResource(Color.TRANSPARENT);
                                collapseFab();
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
        fab.setVisibility(View.GONE);
        fabAction1.setVisibility(View.GONE);
        fabAction2.setVisibility(View.GONE);
    }

    public void expandedFab(){
        fab.setImageResource(R.drawable.animated_minus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createExpandAnimator(fabAction1,offset1),
                createExpandAnimator(fabAction2,offset2));
                //createExpandAnimator(fabAction3,offset3));
        animatorSet.start();
        animateFab();
    }
    public  void  collapseFab(){
        fab.setImageResource(R.drawable.animated_plus);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(createCollapseAnimator(fabAction1,offset1),
                createCollapseAnimator(fabAction2,offset2));
                //createCollapseAnimator(fabAction3,offset3));
        animatorSet.start();
        animateFab();
    }
    private static  final String TRANSLATION_Y= "translationY";


    private Animator createCollapseAnimator(View view, float offset){

        return ObjectAnimator.ofFloat(view,TRANSLATION_Y,0,offset).setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }
    private Animator createExpandAnimator(View view,float offset){
        return  ObjectAnimator.ofFloat(view,TRANSLATION_Y,offset,0).setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }
    /*genera la animacion*/
    private void animateFab(){
        Drawable drawable = fab.getDrawable();
        if(drawable instanceof Animatable){
            ((Animatable)drawable).start();
        }
    }

}