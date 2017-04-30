package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;


import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Documento;


/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class OneFragmentInteractorImpl implements OneFragmentInteractor {
    private OneFragmentPresenter presenter;
    public OneFragmentInteractorImpl ( OneFragmentPresenter presenter){
        this.presenter= presenter;
    }
    @Override
    public void initRecycler() {
        List<Documento> documentoList = new ArrayList<>();

        documentoList.add(new Documento("Presentacion de equipo","v 3.0","12/05/17","url"));
        documentoList.add(new Documento("Introduccion","v 3.0","12/02/17","url"));
        documentoList.add(new Documento("Presupuesto del proyecto","v 3.0","12/09/17","url"));
        documentoList.add(new Documento("Equipo del proyecto","v 3.5","12/09/17","url"));
        documentoList.add(new Documento("Presentacion de equipo","v 2","12/07/17","url"));
        documentoList.add(new Documento("Presentacion de equipo","v 1.6","12/03/17","url"));
        documentoList.add(new Documento("Presentacion de equipo","v 5","10/05/17","url"));
        presenter.initRecycler(documentoList);
    }
}
