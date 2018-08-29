package com.aperam.kryslan.praticaspadrao.View.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.Model.BD.BdLite;
import com.aperam.kryslan.praticaspadrao.View.Adapters.CardTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.View.Adapters.WhatsAppTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.View.Adapters.ListaTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.Model.Domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.View.Telas.PraticasActivity;
import com.aperam.kryslan.praticaspadrao.View.Telas.RecyclerViewOnClickListenerHack;
import com.aperam.kryslan.praticaspadrao.Controller.Tools.Utils;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingButtonSeparateListener;

import java.util.ArrayList;
import java.util.List;

import static com.aperam.kryslan.praticaspadrao.Model.BD.BdLite.SelectSubCategoria;
import static com.aperam.kryslan.praticaspadrao.Model.BD.BdLite.SelectTipoLista;

public class AreaEmitenteFrag extends Fragment implements RecyclerViewOnClickListenerHack, OnRapidFloatingButtonSeparateListener {
    private Context c;
    private RecyclerView mRecyclerView;
    private List<TelaInicialCards> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;
    private String queryPesquisa;

    private RapidFloatingActionLayout fabView;
    int alturaFab = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*if (savedInstanceState == null) {
            mList = GetAreaEmitenteBd();  //Se for maior do que a lista, começa a repetir os itens. Mas não da erro.
        } else {
            mList = savedInstanceState.getParcelableArrayList("mList");
        }*/
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_praticas_main_activity, container, false);  //pegando o XML do fragment.

        c = view.getContext();

        mRecyclerView = view.findViewById(R.id.rv_list);  //Pegando o recyclerView.
        mRecyclerView.setHasFixedSize(true);  //Vai garantir que o recyclerView não mude de tamanho.

        //LISTENER SCROLL
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

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));  //Chama o Listener de Click em cada Card, (configuração continua na classe do Adapter).

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

//        if(mList == null){
        mList = SelectSubCategoria("areaEmitente");
//        }

        //DEFINE O TIPO DE LISTA.
        AtualizaTipoLista(mList);

        //FLOATING SEARCHVIEW
        mSearchView = view.findViewById(R.id.floating_search_view);
        mSearchView.setSearchHint("Pesquisa de Área Emitente...");
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {  //Ao mudar o texto na barra de pesquisa.
                queryPesquisa = newQuery;
                filterList.clear();
                if(TextUtils.isEmpty(newQuery)){  //Se o que for digitado for == " ".
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

    @Override
    public void onLongPressClickListener(View view, int position) {  //Aqui define o que acontece ao clicar e segurar em cada card.
//        Toast.makeText(getActivity(), "onLongPressClickListener: " +position, Toast.LENGTH_SHORT).show();

//        CardTelaInicialAdapter adapter = (CardTelaInicialAdapter) mRecyclerView.getAdapter();
//        adapter.removeListItem(position);  //Ao clicar no item, remove ele da lista.
    }


    @Override
    public void onRFABClick() {
        DialogTipoLista(c);
    }

    public static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{  //Ao clicar nos itens lança um Listener, para fazer a animação.
        private Context mContext;  //Pega várias informações do app no tempo de execução para usar essas informações.
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        protected RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvhack){
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvhack;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){  //É chamado em onInterceptTouchEvent.
               //--- MotionEvent pode identificar vários tipos de cliques diferentes. ---
               @Override
               public void onLongPress(MotionEvent e){
                    super.onLongPress(e);

                    View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).

                    if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null){  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(viewSelecionada, rv.getChildAdapterPosition(viewSelecionada));
                    }
               }

               @Override
               public boolean onSingleTapUp(MotionEvent e){
                   View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).

                   if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null){  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
                       mRecyclerViewOnClickListenerHack.onClickListener(viewSelecionada, rv.getChildAdapterPosition(viewSelecionada));  //Aqui chama "onClickListener"
                   }
                   return (true);
               }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);  //Identifica se foi um clique simples ou longPress.
            return false;  //se for true, pega o evento de click disparado do layout root (RelativeLayout no caso).
        }
        @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
        @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putParcelableArrayList("mList", (ArrayList<TelaInicialCards>) mList);
        savedInstanceState.putString("pesquisa", queryPesquisa);
    }

    private void DialogTipoLista(Context contextLocal){
        new MaterialDialog.Builder(contextLocal)
            .title(R.string.selecioneTipoLista)
            .items(R.array.tipoLista)
            .itemsCallback(new MaterialDialog.ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View view, int itemSelecionado, CharSequence text) {
                    BdLite.atualizaTipoLista(itemSelecionado);
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
//        int tipoLista = BdLite.SelectTipoLista(0);
        int tipoLista = SelectTipoLista();
        if(tipoLista == 0){
            CardTelaInicialAdapter adapter = new CardTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }else if(tipoLista == 1){
            WhatsAppTelaInicialAdapter adapter = new WhatsAppTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }else{
            ListaTelaInicialAdapter adapter = new ListaTelaInicialAdapter(getActivity(), mList);
            adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
            mRecyclerView.setAdapter(adapter);
        }
    }
}
