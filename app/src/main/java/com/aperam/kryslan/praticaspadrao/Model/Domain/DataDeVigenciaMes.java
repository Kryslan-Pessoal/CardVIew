package com.aperam.kryslan.praticaspadrao.Model.Domain;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DataDeVigenciaMes extends ExpandableGroup<DataDeVigenciaDia> {


    public DataDeVigenciaMes(String title, List<DataDeVigenciaDia> items) {
        super(title, items);
    }


    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataDeVigenciaMes)) return false;

        DataDeVigenciaMes genre = (DataDeVigenciaMes) o;

        return getIconResId() == genre.getIconResId();

    }

    @Override
    public int hashCode() {
        return getIconResId();
    }*/
}
