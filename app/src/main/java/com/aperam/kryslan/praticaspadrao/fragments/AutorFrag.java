package com.aperam.kryslan.praticaspadrao.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.ListaTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.interfaces.PraticasActivity;
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

import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BdMainActivity.GetAutorMainActivity;

public class AutorFrag extends AreaEmitenteFrag implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener{
    List<TelaInicialCards> mList, filterList = new ArrayList<>();
    private FloatingSearchView mSearchView;

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;
    Context c = null;

//    protected FloatingActionMenu fab;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saverdInstanceState){
        View view = inflater.inflate(R.layout.fragment_praticas_autor, container, false);  //pegando o fragment.

        c = view.getContext();
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
                /*if (dy > 0){
                    fab.hideMenu(false);
                }else if (dy < 0)
                    fab.showMenu(true);*/
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        mRecyclerView.setLayoutManager(lm);

        mList = GetAutorMainActivity();
        ListaTelaInicialAdapter adapter = new ListaTelaInicialAdapter(container.getContext(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);  //Pega o parâmetro passado em PraticasAdapter para o clique na lista.
        mRecyclerView.setAdapter(adapter);

        ((DragScrollBar) view.findViewById(R.id.dragScrollBar))
            .setIndicator(new AlphabetIndicator(view.getContext()), true)
            .setHandleColour(getResources().getColor(R.color.colorPrimary));  //CONFIGURAR O SAVEDINSTANCESTATE.

        //FLOATING SEARCHVIEW
        mSearchView = view.findViewById(R.id.floating_search_view);
        mSearchView.setSearchHint("Pesquisa de Autor...");
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
                mSearchView.hideProgress();
            }
        });

        //FLOATING ACTION BUTTOM
        rfaLayout = view.findViewById(R.id.autorLFAB);
        rfaBtn = view.findViewById(R.id.autorFAB);
        SetFloatingActionButton();

        return view;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClickListener(View view, int position) {  //Aqui define o que acontece ao clicar em cada card.
        Intent intent = new Intent(getActivity(), PraticasActivity.class);
        TelaInicialCards telaInicialCards = mList.get(position);
        intent.putExtra("praticascards", telaInicialCards);

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        View textoAutor = view.findViewById(R.id.tituloListaTelaInicial);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                Pair.create(textoAutor, "element2"));

        getActivity().startActivityForResult(intent, 1,options.toBundle());
    }

    public void SetFloatingActionButton(){

        RapidFloatingActionContentLabelList fabList = new RapidFloatingActionContentLabelList(c);
        fabList.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Github: wangjiegulu")
                .setResId(R.drawable.autor)
                .setIconNormalColor(c.getResources().getColor(R.color.primary))
                .setIconPressedColor(c.getResources().getColor(R.color.primary_dark))
                .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("tiantian.china.2@gmail.com")
                .setResId(R.drawable.autor)
                .setIconNormalColor(0xff4e342e)
                .setIconPressedColor(0xff3e2723)
                .setLabelColor(Color.WHITE)
                .setLabelSizeSp(14)
//                .setLabelBackgroundDrawable(ABShape.generateCornerShapeDrawable(0xaa000000, ABTextUtil.dip2px(c, 4)))
                .setWrapper(1)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("WangJie")
                .setResId(R.drawable.autor)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Compose")
                .setResId(R.drawable.autor_selected)
                .setIconNormalColor(0xff283593)
                .setIconPressedColor(0xff1a237e)
                .setLabelColor(0xff283593)
                .setWrapper(3)
        );
        fabList
                .setItems(items)
//                .setIconShadowRadius(ABTextUtil.dip2px(c, 5))
                .setIconShadowColor(0xff888888)
//                .setIconShadowDy(ABTextUtil.dip2px(c, 5))
        ;
        rfabHelper = new RapidFloatingActionHelper(
                c,
                rfaLayout,
                rfaBtn,
                fabList
        ).build();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        Toast.makeText(getContext(), "clicked label: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        Toast.makeText(getContext(), "clicked icon: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }
    /*public void SetFloatingActionButton(View view){
        fab = view.findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                Toast.makeText(getActivity(), "Is menu opened? "+(b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) getActivity().findViewById(R.id.fab3);
        FloatingActionButton fab4 = (FloatingActionButton) getActivity().findViewById(R.id.fab4);
        FloatingActionButton fab5 = (FloatingActionButton) getActivity().findViewById(R.id.fab5);
        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);
        FloatingActionButton fab4 = (FloatingActionButton) view.findViewById(R.id.fab4);
        FloatingActionButton fab5 = (FloatingActionButton) view.findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);


//        int hideResId = getResourceId(R.styleable.FloatingActionMenu_menu_fab_hide_animation, R.anim.fab_scale_down);
//        fab.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(getContext(), hideResId));

        fab = (ActionButton) getActivity().findViewById(R.id.fab);

        fab.setButtonColor(getActivity().getResources().getColor(R.color.colorFAB));
        fab.setButtonColorPressed(getActivity().getResources().getColor(R.color.colorFABPressed));

        fab.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fab.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);

        fab.setImageResource( R.drawable.ic_plus );

        float scale = getActivity().getResources().getDisplayMetrics().density;
        int shadow = (int)(3 * scale + 0.5);
        fab.setShadowRadius(shadow);

        fab.setOnClickListener(this);
        fab.playShowAnimation();


        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {

            }
            @Override
            public void onScrollUp() {

            }
        }, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                //GridLayoutManager llm = (GridLayoutManager) mRecyclerView.getLayoutManager();
                StaggeredGridLayoutManager llm = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
                int[] aux = llm.findLastCompletelyVisibleItemPositions(null);
                int max = -1;
                for(int i = 0; i < aux.length; i++){
                    max = aux[i] > max ? aux[i] : max;
                }

                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //if (mList.size() == max + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String aux = "";

        switch( v.getId() ){
            case R.id.fab1:
                aux = "Fab 1 clicked";
                break;
            case R.id.fab2:
                aux = "Fab 2 clicked";
                break;
            case R.id.fab3:
                aux = "Fab 3 clicked";
                break;
            case R.id.fab4:
                aux = "Fab 4 clicked";
                break;
            case R.id.fab5:
                aux = "Fab 5 clicked";
                break;
        }

        Toast.makeText(getActivity(), aux, Toast.LENGTH_SHORT).show();
    }*/

}
