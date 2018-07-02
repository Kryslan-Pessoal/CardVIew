package com.aperam.kryslan.praticaspadrao.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.interfaces.DocumentoDrive;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;
import java.util.List;

public class HistoricoFrag extends AreaEmitenteFrag {

    List<ListaPraticas> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        View view = inflater.inflate(R.layout.fragment_praticas_autor, container, false);  //pegando o fragment.

        final RecyclerView mRecyclerView = view.findViewById(R.id.rv_list_autor);
        mRecyclerView.setHasFixedSize(true);  //Vai garantir que o recyclerView não mude de tamanho.

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

        mList = AreaEmitenteBD.GetAreaEmitenteBdLista();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(container.getContext(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        ((DragScrollBar) view.findViewById(R.id.dragScrollBar))
                .setIndicator(new AlphabetIndicator(view.getContext()), true)
                .setHandleColour(getResources().getColor(R.color.colorPrimary));  //CONFIGURAR O SAVEDINSTANCESTATE.

        //FLOATING SEARCHVIEW
        mSearchView = view.findViewById(R.id.floating_search_view);
        mSearchView.setSearchHint("Pesquisa de Histórico...");
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                mSearchView.showProgress();
                filterList.clear();
                if(TextUtils.isEmpty(newQuery)){
                    filterList.addAll(mList);
                }else{
                    for(int i = 0; i < mList.size(); i++)
                    {
                        if(mList.get(i).getTitulo().toLowerCase().contains(newQuery)||mList.get(i).getNumero().toLowerCase().contains(newQuery)){
                            filterList.add(mList.get(i));
                        }
                    }
                }
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(getActivity(), filterList);
                mRecyclerView.setAdapter(adapter);
                //mSearchView.swapSuggestions(newSuggestions); Futuras implementações com Approximate string matching.
                mSearchView.hideProgress();
            }
        });

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
        ListaPraticas informacoesDaPratica = mList.get(position);
        intent.putExtra("praticascards", informacoesDaPratica);

        startActivityForResult(intent, 1);
    }

}