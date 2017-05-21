package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.TwoFragmentPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class TwoFragmentInteractorImpl implements TwoFragmentInteractor {

    private TwoFragmentPresenter presenter;
    public TwoFragmentInteractorImpl ( TwoFragmentPresenter presenter){
        this.presenter= presenter;
    }
    @Override
    public void initRecycler() {

        List<Personal> personalList = new ArrayList<>();
        personalList.add(new Personal("01","Jair Barzola Cuba","Jefe Proyecto"));
        personalList.add(new Personal("02","Richard Inga Aliaga","Operario"));
        personalList.add(new Personal("03","Kevin Chagua Callupe","Operario"));
        personalList.add(new Personal("04","Jhunior Cañari Corpus","Operario"));
        presenter.initRecycler(personalList);

    }
}
