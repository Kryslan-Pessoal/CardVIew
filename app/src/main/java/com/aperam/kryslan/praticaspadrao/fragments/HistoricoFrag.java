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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.interfaces.DocumentoDrive;
import com.aperam.kryslan.praticaspadrao.tools.Utils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

public class HistoricoFrag extends AreaEmitenteFrag implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{

    List<ListaPraticas> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;
    BdLite bd = null;
    RecyclerView mRecyclerView = null;

    private RapidFloatingActionLayout fabView;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    int alturaFab = 0;
    Context c = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        final View view = inflater.inflate(R.layout.fragment_praticas_autor, container, false);  //pegando o fragment.

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
        mList = bd.buscar();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(container.getContext(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        ((DragScrollBar) view.findViewById(R.id.dragScrollBar))
                .setIndicator(new AlphabetIndicator(view.getContext()), true)
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

        //FLOATING ACTION BUTTOM
        fabView = view.findViewById(R.id.fragsLFAB);
        rfaBtn = view.findViewById(R.id.fragsFAB);
        SetFloatingActionButton();

        alturaFab = Utils.AlturaFabCorrigida(c);
        fabView.setY(alturaFab);

        return view;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
        ListaPraticas informacoesDaPratica = mList.get(position);
        intent.putExtra("praticascards", informacoesDaPratica);

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View imagePratica = view.findViewById(R.id.tituloListaPraticas);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                Pair.create(imagePratica, "element1"));

        getActivity().startActivityForResult(intent, 1,options.toBundle());
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        bd.deletar(mList.get(position));  //Deleta do banco de dados o item da lista clicado da tab histórico

        mList.remove(position);  //Remove do List.
        mRecyclerView.getAdapter().notifyItemRemoved(position);  //Notifica ao RecyclerView que ele foi removido para fazer uma animação suave do item sumindo.
    }

    public void SetFloatingActionButton(){
        RapidFloatingActionContentLabelList fabList = new RapidFloatingActionContentLabelList(c);
        fabList.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Limpar histórico")
                .setLabelColor(c.getResources().getColor(R.color.md_red_400))
                .setLabelSizeSp(20)
                .setResId(R.drawable.delete_white)
                .setIconNormalColor(c.getResources().getColor(R.color.primary))
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Tipo de lista")
                .setResId(R.drawable.format_list_white)
                .setIconNormalColor(c.getResources().getColor(R.color.primary))
                .setWrapper(3)
                .setLabelSizeSp(20)
        );
        fabList.setItems(items).setIconShadowColor(0xff888888);
        rfabHelper = new RapidFloatingActionHelper(
                c,
                fabView,
                rfaBtn,
                fabList
        ).build();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if(position == 0){
            DialogDelete();
        }else if(position == 1){
//            DialogTipoLista(c);
        }
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        if(position == 0){
            DialogDelete();
        }
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

                        mList = bd.buscar();
                        ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, mList);
                        mRecyclerView.setAdapter(adapter);

                        rfabHelper.collapseContent();
                        //CONFIGURAR PARA RECOLHER MENU DO FAB NO FINAL DA TAREFA***
                    }
                })
                .show();
    }
}