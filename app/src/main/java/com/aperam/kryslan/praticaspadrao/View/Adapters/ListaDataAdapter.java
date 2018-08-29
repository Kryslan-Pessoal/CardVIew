package com.aperam.kryslan.praticaspadrao.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aperam.kryslan.praticaspadrao.Controller.ExpandableGroup.DiaViewHolder;
import com.aperam.kryslan.praticaspadrao.Controller.ExpandableGroup.MesViewHolder;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.Domain.DataDeVigenciaDia;
import com.aperam.kryslan.praticaspadrao.Model.Domain.DataDeVigenciaMes;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ListaDataAdapter extends ExpandableRecyclerViewAdapter<MesViewHolder, DiaViewHolder> {

    public ListaDataAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public MesViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expandable_lista, parent, false);
        return new MesViewHolder(view);
    }

    @Override
    public DiaViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expandable_dia_lista, parent, false);
        return new DiaViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(DiaViewHolder holder, int flatPosition, ExpandableGroup group,
                                      int childIndex) {
        final DataDeVigenciaDia dia = ((DataDeVigenciaMes) group).getItems().get(childIndex);
        holder.setDia(dia.getData());
    }

    @Override
    public void onBindGroupViewHolder(MesViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setMesTitle(group);
    }
}