package com.aperam.kryslan.praticaspadrao.interfaces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.BancoDeDados.AreaEmitenteBD;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.adapters.CardTelaInicialAdapter;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.fragments.AreaEmitenteFrag;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.graphics.Color.WHITE;

public class PraticasActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {
    private Toolbar mToolbar;
    private TelaInicialCards telaInicialCards;
    CollapsingToolbarLayout mCollapsingToolbarLayout;

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


        Picasso.get().load(telaInicialCards.getFotoUrl())  //Pega a imagem da internet e coloca no ImageView.
                .resize(1280, 720)
                .centerCrop()
                .into(ivCar);

//        ivCar.setController( dc );

        //HEADER
        DrawerCreator drawerClass = new DrawerCreator();
        AccountHeader headerDrawer = drawerClass.DraweHeaderBuilder(this, savedInstanceState);

        //NAVIGATIONDRAWER
        ViewPager viewPager = null;
        drawerClass.DrawerBodyBuilder(this, savedInstanceState, mToolbar, viewPager, this, headerDrawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //Ela faz com que na AppBar, no canto esquerdo fique uma seta de voltar, e não as 3 linhas de abrir o Drawer.
        //TRATAR A FUNÇÃO DE VOLTAR.

        //LISTA DAS PPAs
        RecyclerView recyclerView = findViewById(R.id.rv_list_praticas);
        recyclerView.setHasFixedSize(true);  //Para o recyclerView não mude de tamanho.

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

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, this));

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);  //Define que o layout da lista será na vertical.
        recyclerView.setLayoutManager(lm);

        List<ListaPraticas> mList = AreaEmitenteBD.GetAreaEmitenteBdLista();
        ListaPraticasAdapter adapter = new ListaPraticasAdapter(this, mList);
        recyclerView.setAdapter(adapter);

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PraticasActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_car_activity, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_searchable_activity);

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ){
            searchView = (SearchView) item.getActionView();
        }
        else{
            searchView = (SearchView) MenuItemCompat.getActionView( item );
        }

        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint( getResources().getString(R.string.search_hint) );
*/
        return true;
    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongPressClickListener(View view, int position) {

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
            return false;
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
}
