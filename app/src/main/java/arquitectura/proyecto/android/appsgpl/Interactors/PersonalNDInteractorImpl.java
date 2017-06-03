package arquitectura.proyecto.android.appsgpl.Interactors;

import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDInteractor;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;

/**
 * Created by Brian on 01/06/2017.
 */

public class PersonalNDInteractorImpl implements PersonalNDInteractor {
    private PersonalNDPresenter presenter;
    public PersonalNDInteractorImpl ( PersonalNDPresenter presenter){
        this.presenter= presenter;
    }
    @Override
    public void initRecycler() {

        List<Personal> personalList1 = new ArrayList<>();
        personalList1.add(new Personal("01","Jair Barzola Cuba","Jefe Operario"));
        personalList1.add(new Personal("02","Richard Inga Aliaga","Operario"));
        personalList1.add(new Personal("03","Kevin Chagua Callupe","Operario"));
        personalList1.add(new Personal("04","Jhunior Cañari Corpus","Operario"));
        presenter.initRecycler(personalList1);

    }
}
