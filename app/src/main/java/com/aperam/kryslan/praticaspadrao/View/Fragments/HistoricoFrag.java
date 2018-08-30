package com.aperam.kryslan.praticaspadrao.View.Fragments;

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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.BD.BdLite;
import com.aperam.kryslan.praticaspadrao.View.Adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.Model.Domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.View.Telas.DocumentoDrive;
import com.aperam.kryslan.praticaspadrao.Controller.Tools.Utils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingButtonSeparateListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricoFrag extends AreaEmitenteFrag implements OnRapidFloatingButtonSeparateListener {

    List<ListaPraticas> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;
    BdLite bd = null;
    RecyclerView mRecyclerView = null;

    private RapidFloatingActionButton fabView;

    int alturaFab = 0;
    Context c = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        final View view = inflater.inflate(R.layout.fragment_autor_historico, container, false);  //pegando o fragment.

        c = view.getContext();
        mRecyclerView = view.findViewById(R.id.rv_list_autor);

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

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical (Obrigatório).
        mRecyclerView.setLayoutManager(lm);

        bd = new BdLite(getContext());
        mList = BdLite.SelectHistorico();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(container.getContext(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        ((DragScrollBar) view.findViewById(R.id.dragScrollBar))
                .setHandleColour(getResources().getColor(R.color.colorPrimary));  //CONFIGURAR O SAVEDINSTANCESTATE***.

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(view.getContext(), mRecyclerView, this));  //Ativa o longPress.

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
                        if(mList.get(i).getNome().toLowerCase().contains(newQuery)||mList.get(i).getNumero().toLowerCase().contains(newQuery)){
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

        //FLOATING ACTION BUTTOM
        fabView = view.findViewById(R.id.fabAutorHistorico);

        //FAZ O FAB DE ORGANIZAR LISTA OCULTO.
        fabView.setVisibility(View.GONE);

        alturaFab = Utils.AlturaFabCorrigida(c);
        fabView.setY(alturaFab);
        fabView.setOnRapidFloatingButtonSeparateListener(this);  //Inicia o Listener de clice no FAB.

        return view;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
        if(!filterList.isEmpty()){  //Se filterList não estiver vazio, quer dizer que está exibindo apenas os itens pesquisados, então pega a posição do filterList, e não do mList.
            intent.putExtra("praticascards", filterList.get(position));
        }else {
            intent.putExtra("praticascards", mList.get(position));
        }

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View imagePratica = view.findViewById(R.id.tituloListaPraticas);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                Pair.create(imagePratica, "element1"));

        getActivity().startActivityForResult(intent, 1,options.toBundle());
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        bd.DeletaPratica(mList.get(position));  //Deleta do banco de dados o item da lista clicado da tab histórico

        mList.remove(position);  //Remove do List.
        mRecyclerView.getAdapter().notifyItemRemoved(position);  //Notifica ao RecyclerView que ele foi removido para fazer uma animação suave do item sumindo.
    }


    @Override
    public void onRFABClick() {
        DialogDelete();
    }

    private void DialogDelete(){  //Cria o Dialog.
        new MaterialDialog.Builder(c)
                .title(R.string.deletarTudo)
                .positiveText(R.string.sim)
                .negativeText(R.string.nao)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        bd.deletarTudo();

                        mList = bd.SelectPratica();
                        ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, mList);
                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .show();
    }
}