package com.aperam.kryslan.praticaspadrao.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.View.Telas.RecyclerViewOnClickListenerHack;
import com.turingtechnologies.materialscrollbar.INameableAdapter;

import java.util.List;

public class ListaTelaInicialAdapter extends RecyclerView.Adapter<ListaTelaInicialAdapter.MyViewHolder> implements INameableAdapter {
    private List<TelaInicialCards> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    private Context c;

    public ListaTelaInicialAdapter(Context c, List<TelaInicialCards> lista){
        this.c = c;
        mList = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ListaTelaInicialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {   //Vai inflar o layout de cada item da listas.
        View v = mLayoutInflater.inflate(R.layout.adapter_lista_main_activity, viewGroup, false);

        return new ListaTelaInicialAdapter.MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{  //Responsável por controlar os cardViews, deletando os que não aparecem na tela para preservar memória
        private TextView titulo;


        private MyViewHolder(View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.tituloListaTelaInicial);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListaTelaInicialAdapter.MyViewHolder myViewHolder, int positionList) {  //Vincula nossos dados com a view. (Seta o valor de cada 'mList' com uma view)
        myViewHolder.titulo.setText(mList.get(positionList).getTextoPrincipal());  //Seta o texto da mList em cada Card.
    }

    @Override
    public int getItemCount() {  //Retorna a quantidade da lista.
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;  //Está adicionando o parâmetro de clique de entrada ao hack para ativar o click.
    }

    @Override
    public Character getCharacterForElement(int element) {
        Character c = mList.get(element).getTextoPrincipal().charAt(0);
        if(Character.isDigit(c)) {
            c = '#';
        }
        return c;
    }
}
