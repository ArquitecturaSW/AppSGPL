package arquitectura.proyecto.android.appsgpl.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import java.util.ArrayList;
import java.util.List;

import arquitectura.proyecto.android.appsgpl.Views.OneFragment;
import arquitectura.proyecto.android.appsgpl.R;
import arquitectura.proyecto.android.appsgpl.Fragments.ThreeFragment;
import arquitectura.proyecto.android.appsgpl.Views.TwoFragment;

public class DetalleProyecto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("label"));

        setContentView(R.layout.activity_detalle_proyecto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setBackgroundColor(bundle.getInt("color"));
        tabLayout.setBackgroundColor(bundle.getInt("color"));

    }
    private void setupViewPager(ViewPager viewPager) {
        DetalleProyecto.ViewPagerAdapter adapter = new DetalleProyecto.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ThreeFragment(), "PROYECTO");
        adapter.addFragment(new TwoFragment(), "PERSONAL");
        adapter.addFragment(new OneFragment(), "DOCUMENTOS");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




}
