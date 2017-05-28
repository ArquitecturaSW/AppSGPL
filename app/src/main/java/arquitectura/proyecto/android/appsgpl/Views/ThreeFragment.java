package arquitectura.proyecto.android.appsgpl.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.ThreeFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Proyecto;
import arquitectura.proyecto.android.appsgpl.Presenters.ThreeFragmentPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class ThreeFragment extends Fragment implements ThreeFragmentView{

    TextView nombre;
    TextView codigo;
    TextView jefe;
    TextView estado;
    TextView fechai;
    TextView fechaf;
    TextView monto;
    TextView descripcion;
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
        nombre = (TextView) rootView.findViewById(R.id.nombre_proyecto_fr3);
        codigo = (TextView) rootView.findViewById(R.id.codigo_proyecto_fr3);
        jefe= (TextView) rootView.findViewById(R.id.jefe_proyecto_fr3);
        descripcion = (TextView) rootView.findViewById(R.id.descripcion_proyecto_fr3);
        estado=(TextView) rootView.findViewById(R.id.estado_proyecto_fr3);
        fechaf=(TextView)rootView.findViewById(R.id.fechaf_proyecto_fr3);
        fechai=(TextView)rootView.findViewById(R.id.fechai_proyecto_fr3);
        monto = (TextView) rootView.findViewById(R.id.monto_proyecto_fr3);


        Proyecto proyecto = (Proyecto) getArguments().getSerializable("pjt");
        presenter = new ThreeFragmentPresenterImpl(this);
        presenter.sendInteractor(proyecto);
        return rootView;
    }

    @Override
    public void showData(String nombreProyecto, String codigoProyecto,
                         String jefeProyecto, int montoProyecto,
                         String dateStart, String dateEnd, String statusProyecto, String descripcionProyecto) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nombre.setText(nombreProyecto);
        codigo.setText(codigoProyecto);
        jefe.setText(jefeProyecto);
        descripcion.setText(descripcionProyecto);
        estado.setText(statusProyecto);
        fechaf.setText(dateEnd);
        fechai.setText(dateStart);
        monto.setText(nf.format(montoProyecto));
    }
}