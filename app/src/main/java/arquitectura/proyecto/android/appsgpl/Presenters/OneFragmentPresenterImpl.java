package arquitectura.proyecto.android.appsgpl.Presenters;

import java.util.List;


import arquitectura.proyecto.android.appsgpl.Interactors.MainActivityInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interactors.OneFragmentInteractorImpl;
import arquitectura.proyecto.android.appsgpl.Interfaces.MainActivityView;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.Interfaces.OneFragmentView;
import arquitectura.proyecto.android.appsgpl.POJOS.Documento;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class OneFragmentPresenterImpl implements OneFragmentPresenter {

    private OneFragmentInteractor interactor;
    private OneFragmentView view;
    public OneFragmentPresenterImpl(OneFragmentView view){
        this.view=view;
        interactor = new OneFragmentInteractorImpl(this);
    }
    @Override
    public void initRecycler(List<Documento> documentoList) {
        view.initRecycler(documentoList);
    }

    @Override
    public void loadListDocumento() {
        interactor.initRecycler();
    }
}
