package com.aperam.kryslan.praticaspadrao.interfaces;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.aperam.kryslan.praticaspadrao.R;
import com.aperam.kryslan.praticaspadrao.SQLite.BdLite;
import com.aperam.kryslan.praticaspadrao.adapters.DataAdapter;
import com.aperam.kryslan.praticaspadrao.adapters.ListaPraticasAdapter;
import com.aperam.kryslan.praticaspadrao.domain.ListaPraticas;
import com.aperam.kryslan.praticaspadrao.domain.TelaInicialCards;
import com.aperam.kryslan.praticaspadrao.tools.DrawerCreator;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.squareup.picasso.Picasso;
import com.turingtechnologies.materialscrollbar.DragScrollBar;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.WHITE;
import static com.aperam.kryslan.praticaspadrao.BancoDeDados.BdExpandableList.CriaMesExpansivel;

public class PraticasActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {
    private Context c = this;
    private Toolbar mToolbar;
    private TelaInicialCards informacoesSubCategoria;
    List<ListaPraticas> mList, filterList = new ArrayList<>();
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    Activity activity = this;

    private MaterialSearchView searchView;
    RecyclerView recyclerView = null;

    String queryDePesquisaSubmit = "";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TRANSIÇÃO ANTIGA.
        /*Explode transition1 = new Explode();
        transition1.setDuration(600);

        Fade transition2 = new Fade();
        transition2.setDuration(600);

        getWindow().setEnterTransition(transition1);  //transição recebida de MainActivity.
        getWindow().setReturnTransition(transition2);  //transição na volta.*/

        // TRANSITIONS, CRIANDO ANIMAÇÃO.
        TransitionInflater inflater = TransitionInflater.from(this);  //Recebe a animação.
        Transition transition = inflater.inflateTransition(R.transition.transitions);

        getWindow().setSharedElementExitTransition(transition);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praticas);

        informacoesSubCategoria = getIntent().getExtras().getParcelable("praticascards");

        //CONFIGURA O TOOLBAR.
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setExpandedTitleTextColor(ColorStateList.valueOf(WHITE));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ColorStateList.valueOf(WHITE));

        mToolbar = findViewById(R.id.tb_main);
        mToolbar.setTitle(informacoesSubCategoria.getTextoPrincipal());
        setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        ImageView ivCar = findViewById(R.id.iv_imagem_ilustrativa);
//        SimpleDraweeView ivCar = findViewById(R.id.iv_car);

        String fotoUrl;
        try{
            fotoUrl = informacoesSubCategoria.getFotoUrl();
            if(fotoUrl.equals(""))  //Vazio.
                fotoUrl = "https://drive.google.com/uc?id=10W-D9UAY5v9mp0fbysn9cVpAkE51NFzD";
        }catch (Exception e){
            fotoUrl = "https://drive.google.com/uc?id=10W-D9UAY5v9mp0fbysn9cVpAkE51NFzD";  //Se vier como null, coloca uma imagem qualquer.
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
        recyclerView = findViewById(R.id.rv_list_praticas);
        recyclerView.setHasFixedSize(true);  //Para o recyclerView não mude de tamanho.

        //SEARCHVIEW
        searchView = findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);  //Para o título aparecer e não ser tampado pela caixa do searchView, ele fica oculto por padrão.
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);  //Mas ao abrir ele, ele volta a aparecer.
            }
            @Override
            public void onSearchViewClosed() {
                searchView.setVisibility(View.GONE);  //E ao fechar ele some novamente.
            }
        });
        searchView.setHint("Procura");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query)){
                    queryDePesquisaSubmit = query;
                }
                return false;  //Define se o teclado irá fechar ou não (false fecha).
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(!queryDePesquisaSubmit.isEmpty()){
                    newText = queryDePesquisaSubmit;
                }
                if (!TextUtils.isEmpty(newText)) {
                    filterList.clear();
                    for(int i = 0; i < mList.size(); i++)
                    {
                        if(mList.get(i).getNome().toLowerCase().contains(newText)||mList.get(i).getNumero().toLowerCase().contains(newText)){
                            filterList.add(mList.get(i));
                        }
                    }

                }else{
                    filterList.addAll(mList);
                }
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                recyclerView.setAdapter(adapter);
                queryDePesquisaSubmit = "";
                return true;
            }
        });
        searchView.setOnClickListener(new MaterialSearchView.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList.clear();
                filterList.addAll(mList);
                ListaPraticasAdapter adapter = new ListaPraticasAdapter(c, filterList);
                recyclerView.setAdapter(adapter);
            }
        });  //TESTAR E FAZER FUNCIONAR.


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


        //DEFINE O TIPO DE LISTA QUE SERÁ MONTADO.
//        mList = BdPraticasActivity.GetAreaEmitentePraticasActivity();
        TipoDeLista();

        if(!informacoesSubCategoria.getGrupo().equals("Data de Vigência")) {  //Data de vigência irá usar uma lista composta.
            ListaPraticasAdapter adapter = new ListaPraticasAdapter(this, mList);
            recyclerView.setAdapter(adapter);
        }else{
            DataAdapter adapter = new DataAdapter(CriaMesExpansivel());
            recyclerView.setAdapter(adapter);
            /*Mes = Genre
            Artist = Dia*/
        }

//        // FAB
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(PraticasActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

        //MATERIAL SCROLLBAR
        if(!informacoesSubCategoria.getGrupo().equals("Data de Vigência")){  //ScrollBar não funciona com ExpandableListView.
            ((DragScrollBar) findViewById(R.id.dragScrollBarActivityPraticas))
                    .setHandleColour(c.getResources().getColor(R.color.colorPrimary));
        }
    }

    private void TipoDeLista(){  //Define o tipo de lista que terá na tela de práticas.
        int idCategoria = informacoesSubCategoria.getId();
        if (informacoesSubCategoria.getGrupo().equals("areaEmitente")) {
            mList = BdLite.SelectListPraticas("id_areaEmitente", idCategoria);
        }else if(informacoesSubCategoria.getGrupo().equals("areasRelacionadas")) {
            mList = BdLite.SelectListPraticas("id_areasRelacionadas", idCategoria);
        }else if(informacoesSubCategoria.getGrupo().equals("autor")) {
            mList = BdLite.SelectListPraticas("id_autor", idCategoria);
        }else if(informacoesSubCategoria.getGrupo().equals("dataDeVigencia")) {
        }else if(informacoesSubCategoria.getGrupo().equals("nivel")) {
            mList = BdLite.SelectListPraticas("id_nivel", idCategoria);
        }else if(informacoesSubCategoria.getGrupo().equals("processo")) {
            mList = BdLite.SelectListPraticas("id_processo", idCategoria);
        }
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
        if(!informacoesSubCategoria.getGrupo().equals("Data de Vigência")) { //Quando é ExpandableListView, o OnClickListener, não dever ser chamado aqui.
            //CHAMANDO PRÓXIMA ACTIVITY (DocumentoDrive).
            Intent intent = new Intent(view.getContext(), DocumentoDrive.class);
            if(!filterList.isEmpty()){  //Se filterList não estiver vazio, quer dizer que está exibindo apenas os itens pesquisados, então pega a posição do filterList, e não do mList.
                intent.putExtra("praticascards", filterList.get(position));
            }else {
                intent.putExtra("praticascards", mList.get(position));
            }

            // TRANSITIONS, CRIANDO ANIMAÇÃO.
            View textoAutor = view.findViewById(R.id.tituloListaPraticas);

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, Pair.create(textoAutor, "element2"));  //Não vai achar o element2
            //propositalmente para causar uma animação suave.

            activity.startActivityForResult(intent, 1, options.toBundle());

            //SALVANDO NO BANCO DE DADOS ESSE ITEM PARA EXIBI-LO NO HISTÓRICO.
            BdLite bd = new BdLite(activity);
            bd.InsertHistorico(mList.get(position).getId());
        }
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        String[] corpoDialog = new String[8];
        corpoDialog[0] = "TÍTULO: " + mList.get(position).getNome();
        corpoDialog[1] = "Nº: " + mList.get(position).getNumero();
        corpoDialog[2] = "AUTOR: " + mList.get(position).getAutor();
        corpoDialog[3] = "DATA: " + mList.get(position).getDataVersao();
        corpoDialog[4] = "FASE DO DOC: " + mList.get(position).getFaseDocumento();
        corpoDialog[5] = "REVISOR SST MA: " + mList.get(position).getRevisorSstMa();
        corpoDialog[6] = "REVISOR TÉCNICO: " + mList.get(position).getRevisorTecnico();
        corpoDialog[7] = "APROVADOR: " + mList.get(position).getAprovador();
        new MaterialDialog.Builder(this)  //Cria um Dialog com informações da PPA clicada.
                .title(R.string.informacoesDaPpa)
                .items(corpoDialog)
                .show();
    }

    protected static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {  //Ao clicar nos itens lança um Listener, para fazer a animação.
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
        if(id == R.id.home){
            Toast.makeText(c, "pesquisa", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable("praticasCards", praticasCards);
    }

//    @Override
//    public void onBackPressed() {
//        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
//            TransitionManager.beginDelayedTransition(mRoot, new Slide());
//            tvDescription.setVisibility( View.INVISIBLE );
//        }
//
//        super.onBackPressed();
//    }

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
