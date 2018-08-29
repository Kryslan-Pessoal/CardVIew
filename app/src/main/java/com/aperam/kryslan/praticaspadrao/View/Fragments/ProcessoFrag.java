package com.aperam.kryslan.praticaspadrao.View.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.View.Adapters.ListaTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.View.Telas.PraticasActivity;
import com.aperam.kryslan.praticaspadrao.View.Telas.RecyclerViewOnClickListenerHack;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.Model.BD.BdLite.SelectSubCategoria;

public class ProcessoFrag extends AreaEmitenteFrag {
    List<TelaInicialCards> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;
    RecyclerViewOnClickListenerHack thisFrag = this;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        View view = inflater.inflate(R.layout.fragment_praticas_com_scrool_bar, container, false);  //pegando o fragment.

        final RecyclerView mRecyclerView = view.findViewById(R.id.rv_list_processo);  //Pegando o recyclerView.
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

        mList = SelectSubCategoria("processo");
        ListaTelaInicialAdapter adapter = new ListaTelaInicialAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        ((DragScrollBar) view.findViewById(R.id.dragScrollBar))
            .setIndicator(new AlphabetIndicator(view.getContext()), true)
            .setHandleColour(getResources().getColor(R.color.colorPrimary));

        //FLOATING SEARCHVIEW
        mSearchView = view.findViewById(R.id.floating_search_view);
        mSearchView.setSearchHint("Pesquisa de Processo...");
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
                        if(mList.get(i).getTextoPrincipal().toLowerCase().contains(newQuery)){
                            filterList.add(mList.get(i));
                        }
                    }
                }
                ListaTelaInicialAdapter adapter = new ListaTelaInicialAdapter(getActivity(), filterList);
                mRecyclerView.setAdapter(adapter);
                //mSearchView.swapSuggestions(newSuggestions); Futuras implementações com Approximate string matching.
                adapter.setRecyclerViewOnClickListenerHack(thisFrag);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
                mSearchView.hideProgress();
            }
        });

        //FAZ O FAB DESAPARECER NESTA LISTA.
        RapidFloatingActionLayout rfaLayout = view.findViewById(R.id.fragsLFAB);
        rfaLayout.setVisibility(View.GONE);

        return view;
    }
    @SuppressLint("RestrictedApi")
    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(getActivity(), PraticasActivity.class);
        if(!filterList.isEmpty()){  //Se filterList não estiver vazio, quer dizer que está exibindo apenas os itens pesquisados, então pega a posição do filterList, e não do mList.
            intent.putExtra("praticascards", filterList.get(position));
        }else {
            intent.putExtra("praticascards", mList.get(position));
        }

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View textoAutor = view.findViewById(R.id.tituloListaTelaInicial);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                Pair.create(textoAutor, "element2"));

        getActivity().startActivityForResult(intent, 1,options.toBundle());
    }
}
