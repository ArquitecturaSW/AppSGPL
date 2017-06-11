package arquitectura.proyecto.android.appsgpl.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import arquitectura.proyecto.android.appsgpl.Adapters.RecyclerAdapterPersonalND;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDView;
import arquitectura.proyecto.android.appsgpl.Interfaces.PersonalNDPresenter;
import arquitectura.proyecto.android.appsgpl.POJOS.Personal;
import arquitectura.proyecto.android.appsgpl.Presenters.PersonalNDPresenterImpl;
import arquitectura.proyecto.android.appsgpl.R;

public class PersonalND extends AppCompatActivity implements PersonalNDView {
    RecyclerView recyclerView;
    Context context;

    PersonalNDPresenter presenter;
    RecyclerAdapterPersonalND adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_nd);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        presenter = new PersonalNDPresenterImpl(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        adapter = new RecyclerAdapterPersonalND(context,R.layout.item_personal);
        recyclerView.setAdapter(adapter);
        presenter.loadListPersonal();
         /*Implementacion de RecyclerView con MVP*/
    }


    @Override
    public void initRecycler(List<Personal> personalList1) {
        adapter.setListPersonal(personalList1);
    }
}
