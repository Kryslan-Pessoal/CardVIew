package com.aperam.kryslan.praticaspadrao.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aperam.kryslan.praticaspadrao.View.Fragments.AreaEmitenteFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.AreasRelacionadasFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.AutorFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.DataDeVigenciaFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.HistoricoFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.NivelFrag;
import com.aperam.kryslan.praticaspadrao.View.Fragments.ProcessoFrag;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private String[] tabTitles;

    public ViewPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new AreaEmitenteFrag();
            case 1:
                return new AreasRelacionadasFrag();
            case 2:
                return new AutorFrag();
            case 3:
                return new DataDeVigenciaFrag();
            case 4:
                return new NivelFrag();
            case 5:
                return new ProcessoFrag();
//            case 6:
//                return new RestritoFrag();
//            case 7:
//                return new FavoritosFrag();
            case 6:
                return new HistoricoFrag();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
