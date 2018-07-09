package com.aperam.kryslan.praticaspadrao.ExpandableGroup;

import com.aperam.kryslan.praticaspadrao.domain.DataDeVigenciaDia;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Mes extends ExpandableGroup<DataDeVigenciaDia> {

    public Mes(String title, List<DataDeVigenciaDia> items) {
        super(title, items);
    }
}
