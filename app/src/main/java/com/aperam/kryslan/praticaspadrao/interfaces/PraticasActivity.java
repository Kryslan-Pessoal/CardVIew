package com.aperam.kryslan.praticaspadrao.interfaces;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.AlphabetIndicator;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.WHITE;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD.getTituloAreaEmitenteBd;

public class PraticasActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {
    private Context c = this;
    private Toolbar mToolbar;
    private TelaInicialCards telaInicialCards;
    List<ListaPraticas> mList, filterList = new ArrayList<>();
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    Activity activity = this;

    private MaterialSearchView searchView;

    private MaterialDialog mMaterialDialog;
    private TextView tvDescription;
    private ViewGroup mRoot;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TRANSITIONS
        /*if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                *//*Explode trans1 = new Explode();
                trans1.setDuration(3000);
                Fade trans2 = new Fade();
                trans2.setDuration(3000);

                getWindow().setEnterTransition( trans1 );
                getWindow().setReturnTransition( trans2 );*//*

            TransitionInflater inflater = TransitionInflater.from( this );
            Transition transition = inflater.inflateTransition( R.transition.transitions );

            getWindow().setSharedElementEnterTransition(transition);

            Transition transition1 = getWindow().getSharedElementEnterTransition();
            transition1.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    TransitionManager.beginDelayedTransition(mRoot, new Slide());
                    tvDescription.setVisibility( View.VISIBLE );
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }*/

        /*
        if(savedInstanceState != null){
            praticasCards = savedInstanceState.getParcelable("praticascards");
        }
        else {
            if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("praticascards") != null) {
                praticasCards = getIntent().getExtras().getParcelable("praticascards");
            } else {
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praticas);

        telaInicialCards = getIntent().getExtras().getParcelable("praticascards");


        //CONFIGURA O TOOLBAR.
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(telaInicialCards.getTextoPrincipal());
        mCollapsingToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(WHITE));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ColorStateList.valueOf(WHITE));

        mToolbar = findViewById(R.id.tb_main);
        mToolbar.setTitle(telaInicialCards.getTextoPrincipal());
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);*/

        ImageView ivCar = findViewById(R.id.iv_imagem_ilustrativa);
//        SimpleDraweeView ivCar = findViewById(R.id.iv_car);

        String fotoUrl = "";
        int valor = telaInicialCards.getId();  //Pelo Id, vai pegar a imagem que aparecetá na tollbar na tela inicial.
        if(valor == 0) {  //Vazio.
            fotoUrl = telaInicialCards.getFotoUrl();
        }else if(valor == 1){  //Autor
            fotoUrl = "https://drive.google.com/uc?id=1pZxi73hIl5wU4jU8_P2eO8aGZ9QOzhDO";
        }else if(valor == 2){
            fotoUrl = "https://drive.google.com/uc?id=16lpe02o7gj4f1foQ6iSRJU2oBjT0mXlw";
        }else if(valor == 3){
            fotoUrl = "https://drive.google.com/uc?id=10qBgvIQGIl3oGg0fzBo876NKs1VDVKXy";
        }

        Picasso.get().load(fotoUrl)  //Pega a imagem da internet e coloca no ImageView.
                .resize(1280, 720)
                .centerCrop()
                .into(ivCar);
//        ivCar.setController( dc );

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);

        //NAVIGATIONDRAWER
        drawerClass.DrawerBodyBuilder(this, savedInstanceState, mToolbar, null, this, headerDrawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Ela faz com que na AppBar, no canto esquerdo fique uma seta de voltar, e não as 3 linhas de abrir o Drawer.
        //TRATAR A FUNÇÃO DE VOLTAR.

        //LISTA DAS PPAs
        final RecyclerView recyclerView = findViewById(R.id.rv_list_praticas);
        recyclerView.setHasFixedSize(true);  //Para o recyclerView não mude de tamanho.

        //SEARCHVIEW
        searchView = findViewById(R.id.search_view);
        searchView.setHint("Procura");
        searchView.setSuggestions(getTituloAreaEmitenteBd());
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList.clear();
                if(TextUtils.isEmpty(query)){
                    filterList.addAll(mList);
                }else{
                    for(int i = 0; i < mList.size(); i++)
                    {
                        if(mList.get(i).getTitulo().toLowerCase().contains(query)||mList.get(i).getNumero().toLowerCase().contains(query)){
                            filterList.add(mList.get(i));
                        }
                    }
                }
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                recyclerView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    filterList.clear();
                    for(int i = 0; i < mList.size(); i++)
                    {
                        if(mList.get(i).getTitulo().toLowerCase().contains(newText)||mList.get(i).getNumero().toLowerCase().contains(newText)){
                            filterList.add(mList.get(i));
                        }
                    }
                    ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                    recyclerView.setAdapter(adapter);
                }

                return true;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(c, recyclerView, this));

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        recyclerView.setLayoutManager(lm);

        mList = AreaEmitenteBD.GetAreaEmitenteBdLista();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(this, mList);
        recyclerView.setAdapter(adapter);

       /* // FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PraticasActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });*/

        //MATERIAL SCROLLBAR
        ((DragScrollBar) findViewById(R.id.dragScrollBar))
            .setIndicator(new AlphabetIndicator(c), true)
            .setHandleColour(c.getResources().getColor(R.color.colorPrimary));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //SEARCHVIEW
        getMenuInflater().inflate(R.menu.menu_praticas_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(searchItem);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
        ListaPraticas informacoesDaPratica = mList.get(position);
        intent.putExtra("praticascards", informacoesDaPratica);

        startActivityForResult(intent, 1);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        String[] corpoDialog = new String[4];
        corpoDialog[0] = "TÍTULO: " + mList.get(position).getTitulo();
        corpoDialog[1] = "Nº: " + mList.get(position).getNumero();
        corpoDialog[2] = "AUTOR: " + mList.get(position).getAutor();
        corpoDialog[3] = "DATA: " + mList.get(position).getData();
        new MaterialDialog.Builder(this)  //Cria um Dialog com informações da PPA clicada.
                .title(R.string.informacoesDaPpa)
                .items(corpoDialog)
                .show();
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {  //Ao clicar nos itens lança um Listener, para fazer a animação.
        private Context mContext;  //Pega várias informações do app no tempo de execução para usar essas informações.
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvhack) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvhack;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {  //É chamado em onInterceptTouchEvent.
                //--- MotionEvent pode identificar vários tipos de cliques diferentes. ---
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).
                    if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null) {  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(viewSelecionada, rv.getChildAdapterPosition(viewSelecionada));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View viewSelecionada = rv.findChildViewUnder(e.getX(), e.getY());  //Vai identificar a posição do clique (mas retornará o posicionamento abaixo do clicado).

                    if (viewSelecionada != null && mRecyclerViewOnClickListenerHack != null) {  //Só confere se o clique foi real mesmo e se existe a view selecionada para não dar erro.
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

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("praticasCards", praticasCards);
    }

    /*@Override
    public void onBackPressed() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            TransitionManager.beginDelayedTransition(mRoot, new Slide());
            tvDescription.setVisibility( View.INVISIBLE );
        }

        super.onBackPressed();
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode==2) {  //Só será dois, se for fechado pelas activitys Frag, passando o comando de voltar passando informações: startActivityForResult.
            int i = data.getIntExtra("tabSelecionada", 2);  //Retorna o valor do intent como 2, e a tab selecionada.
            Intent intent = new Intent();
            intent.putExtra("tabSelecionada",i);  //Passa a posição do item no drawer para a activity anterior (Main) para essa setar a tab correta.
            activity.setResult(2,intent);  //Manda resultCode 2 para rodar a função no if apenas se ela voltar por este caminho, e não por onCreate normal etc.
            activity.finish();
            /*if (i < 9) {  //Para não dar erro, pois se selecionar configurações no drawer por exemplo, ele vai dar erro pois não existe essa tab.
                viewPager.setCurrentItem(i - 1);
            }else{  //Se for maior do que o número de tabs existentes, ele retorna para a tab um, mas IMPLEMENTAR FUTURAMENTE.
                viewPager.setCurrentItem(0);
            }*/
        }
    }
}
