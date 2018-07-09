package com.aperam.kryslan.praticaspadrao.ExpandableGroup;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class DiaViewHolder extends ChildViewHolder implements View.OnClickListener {

    private TextView diaName;
    private RelativeLayout relativeLayout;

    public DiaViewHolder(View itemView) {
        super(itemView);
        diaName = itemView.findViewById(R.id.tituloListaExpandableDia);
        relativeLayout = itemView.findViewById(R.id.relativeLayoutExpandableDia);
        relativeLayout.setOnClickListener(this);  //Adicionando evento de click em cada dia da lista. (IMPLEMENTAR FUTURAMENTE LIST NA MAINACTIVITY TAMBÉM).
    }

    public void setDia(String dia) {
        diaName.setText(dia);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), diaName.getText(),
                Toast.LENGTH_SHORT).show();
    }
}