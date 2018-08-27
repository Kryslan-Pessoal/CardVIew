package com.aperam.kryslan.praticaspadrao.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.CardTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.adapters.ListaResumidaTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.adapters.ListaTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.interfaces.PraticasActivity;
import com.aperam.kryslan.praticaspadrao.tools.Utils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BdMainActivity.GetAreasRelacionadasMainActivity;
import static com.aperam.kryslan.praticaspadrao.SQLite.BdLite.buscarSubCategoria;

public class AreasRelacionadasFrag extends AreaEmitenteFrag {
    Context c;
    private List<TelaInicialCards> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;
    RecyclerView mRecyclerView;

    private RapidFloatingActionLayout fabView;
    int alturaFab = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        final View view = inflater.inflate(R.layout.fragment_praticas_main_activity, container, false);  //pegando o fragment.
        c = view.getContext();
        mRecyclerView = view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);  //Vai garantir que o recyclerView não mude de tamanho.

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){  //Quando rola o recyclerView para baixo
                    fabView.animate().translationY(view.getHeight());  //Esconde o Fab.
                }else if (dy < 0) {  //Quando rola o recyclerView para cima
                    fabView.animate().translationY(alturaFab);  //Mostra o Fab.
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));  //CORRIGIR POIS MANDA A INFORMAÇÃO ERRADA AO ABRIR A OUTRA ACTIVITY.

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

        mList = buscarSubCategoria(2);
        AtualizaTipoLista(mList);
        /*CardTelaInicialAdapter adapter = new CardTelaInicialAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);*/

        //FLOATING SEARCHVIEW
        mSearchView = view.findViewById(R.id.floating_search_view);
        mSearchView.setSearchHint("Pesquisa de Áreas Relacionadas...");
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
                AtualizaTipoLista(filterList);
                //mSearchView.swapSuggestions(newSuggestions); Futuras implementações com Approximate string matching.
                mSearchView.hideProgress();
            }
        });

        //FLOATING ACTION BUTTOM
        fabView = view.findViewById(R.id.fragsLFAB);

        alturaFab = Utils.AlturaFabCorrigida(c);
        fabView.setY(alturaFab);
        RapidFloatingActionButton rfab = view.findViewById(R.id.fragsFAB);
        rfab.setOnRapidFloatingButtonSeparateListener(this);  //Inicia o Listener de clice no FAB.

        return view;
    }

    @Override
    public void onRFABClick() {
        DialogTipoLista(c);
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
        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View imagePratica = view.findViewById(R.id.imagem_ilustrativa);  //Primeiro tenta pegar o imageView do estilo Card...
        if(imagePratica == null){  //Se não achar o ImageView Card...
            imagePratica = view.findViewById(R.id.ivTelaInicialResumida);  //procura pelo ImageView da lista resumida.
        }
        if(imagePratica != null) {  //caso a lista não tenha imagem...
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    Pair.create(imagePratica, "element1"));
            getActivity().startActivityForResult(intent, 1,options.toBundle());
        }else{  //cancela a animação para não dar erro.
            getActivity().startActivityForResult(intent, 1);
        }
    }

    protected void DialogTipoLista(Context contextLocal){
        new MaterialDialog.Builder(contextLocal)
                .title(R.string.selecioneTipoLista)
                .items(R.array.tipoLista)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        BdLite.atualizaTipoLista(1, which);
                        AtualizaTipoLista(mList);
                    }
                })
                .show();
    }

    private void AtualizaTipoLista(List<TelaInicialCards> mList){
        /*Tipos de lista:
        0 = Lista com imgaens grandes.
        1 = Lista com imagens, mas resumida
        2 = Lista resumida sem imagens.*/
//        int tipoLista = BdLite.buscaTipoLista(1);
        int tipoLista=0;
        if(tipoLista == 0){
            CardTelaInicialAdapter adapter = new CardTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }else if(tipoLista == 1){
            ListaResumidaTelaInicialAdapter adapter = new ListaResumidaTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }else{
            ListaTelaInicialAdapter adapter = new ListaTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }
    }
}
